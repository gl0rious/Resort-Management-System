package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping
    public ResponseEntity<?> getAllReservations(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = (User) userDetails;
        Customer customer = user.getCustomer();
        if (user.getType() == UserType.CLIENT)
            return ResponseEntity.ok(reservationService.getAllReservationsByCustomer(customer));
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PostMapping
    ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        return new ResponseEntity<>(reservationService.createReservation(reservationDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable("id") int id,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = (User) userDetails;
        Customer customer = user.getCustomer();
        if (user.getType() == UserType.CLIENT) {
            ReservationDTO reservation = reservationService.getReservation(id);
            if (reservation.getCustomerID() != customer.getId())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(reservationService.getReservation(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable("id") int id,
            @RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservationDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable("id") int id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok().build();
    }
}