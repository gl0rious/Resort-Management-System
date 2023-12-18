package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.repository.ItemRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    @PostMapping("/items")
    public ResponseEntity<?> addItem(@RequestBody Item item){
        Reservation reservation = reservationRepository.findById(item.getOrder().getId()).orElse(null);
        if (reservation != null) {
            item.setOrder(reservation);
            Item result = itemRepository.save(item);
            return new ResponseEntity <>(result, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("items/{id}")
    public ResponseEntity<?> getItem(@PathVariable int id){
        Item result = itemRepository.findById(id).orElse(null);
        return new ResponseEntity <>(result, HttpStatus.OK);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable int id, @RequestBody Item item){
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            item.setOrder(reservation);
            Item result = itemRepository.save(item);
            return new ResponseEntity <>(result, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/items")
    public ResponseEntity<?> getAllItems(@RequestBody Item item){
        Item result = itemRepository.save(item);
        return new ResponseEntity <>(result, HttpStatus.OK);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable int id){
        itemRepository.deleteById(id);
        return new ResponseEntity <>(HttpStatus.OK);
    }

}
