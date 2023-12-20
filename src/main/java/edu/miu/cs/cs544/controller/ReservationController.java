package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.dto.request.ItemRequest;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationDTO reservationDTO) {
        return new ResponseEntity<>(reservationService.createReservation(reservationDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable("id") int id) {
        return ResponseEntity.ok(reservationService.getReservation(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ReservationResponse> updateReservationStatus(@PathVariable("id") int id, @RequestBody @Valid ReservationStatusRequest request) {
        ReservationResponse response = reservationService.updateReservationStatus(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable("id") int id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/items")
    public ResponseEntity<ReservationResponse> createItemForReservation(@PathVariable("id") int id, @RequestBody @Valid ItemRequest request) {
       return ResponseEntity.ok(reservationService.createItemForReservation(id, request));
    }
}