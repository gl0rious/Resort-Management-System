package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.request.ItemRequest;
import edu.miu.cs.cs544.dto.response.ReservationResponse;
import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.service.ItemService;
import edu.miu.cs.cs544.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ReservationService reservationService;

    @PostMapping()
    public ResponseEntity<?> addItem(@RequestBody ItemRequest request) {
        if (reservationService.exists(request.getReservationId()))
            throw new ResourceNotFoundException(getClass(), request.getReservationId());
        Item item = itemService.addItemToReservation(request);
        ReservationResponse reservation = reservationService.getById(item.getReservationId());
        if (reservation != null) {
            item.setOrder(reservation);
            Item result = itemService.addItem(item);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("items/{id}")
    public ResponseEntity<?> getItem(@PathVariable int id) {
        Item result = itemService.getItem(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable int id, @RequestBody Item item) {
        Reservation reservation = reservationService.getById(item.getOrder().getId()).to();
        if (reservation != null) {
            item.setOrder(reservation);
            Item result = itemService.updateItem(id, item);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllItems(@RequestBody Item item) {
        Item result = itemService.addItem(item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
