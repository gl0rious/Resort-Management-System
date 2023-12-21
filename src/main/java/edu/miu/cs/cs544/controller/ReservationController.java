package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.dto.request.ItemRequest;
import edu.miu.cs.cs544.dto.request.ReservationRequest;
import edu.miu.cs.cs544.dto.request.ReservationStatusRequest;
import edu.miu.cs.cs544.dto.response.ReservationResponse;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping
    public ResponseEntity<?> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = (User) userDetails;
        Customer customer = user.getCustomer();
        if (user.getType() == UserType.CLIENT)
            return ResponseEntity.ok(reservationService.getAllByCustomer(customer));
        return ResponseEntity.ok(reservationService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<ReservationResponse> create(@RequestBody ReservationRequest reservationDTO) {
        return new ResponseEntity<>(reservationService.createReservation(reservationDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable("id") int id,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = (User) userDetails;
        Customer customer = user.getCustomer();
        if (user.getType() == UserType.CLIENT) {
            ReservationResponse reservation = reservationService.getById(id);
            if (reservation.getCustomerId() != customer.getId())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(reservationService.getById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ReservationResponse> update(@PathVariable("id") int id,
            @RequestBody @Valid ReservationStatusRequest request) {
        ReservationResponse response = reservationService.updateStatus(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> cancel(@PathVariable("id") int id) {
        reservationService.cancel(id);
        return ResponseEntity.ok().build();
    }

    // @PostMapping("{id}/items")
    // public ResponseEntity<ReservationResponse>
    // createItemForReservation(@PathVariable("id") int id,
    // @RequestBody @Valid ItemRequest request) {
    // return ResponseEntity.ok(reservationService.createItemForReservation(id,
    // request));
    // }
}