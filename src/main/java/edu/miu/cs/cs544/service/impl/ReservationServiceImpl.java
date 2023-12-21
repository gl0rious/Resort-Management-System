package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.dto.request.ItemRequest;
import edu.miu.cs.cs544.dto.request.ReservationRequest;
import edu.miu.cs.cs544.dto.request.ReservationStatusRequest;
import edu.miu.cs.cs544.dto.response.ReservationResponse;
import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ProductRepository productRepository;

    @Override
    public ReservationResponse createReservation(ReservationRequest request) {
        Reservation reservation = ReservationRequest.to(request);
        reservation.setCustomer(getCustomerFromAuth());
        reservation = reservationRepository.save(reservation);
        reservation.setStatus(ReservationStatus.NEW);
        return ReservationResponse.from(reservation);
    }

    @Override
    public List<ReservationResponse> getAll() {
        return ReservationResponse.fromList(reservationRepository.findAll());
    }

    @Override
    public ReservationResponse getById(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Reservation.class, id));
        checkReservationOwner(reservation);
        return ReservationResponse.from(reservation);
    }

    @Override
    public void cancelReservation(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Reservation.class, id));
        checkReservationOwner(reservation);
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    public ReservationResponse updateStatus(int id, ReservationStatusRequest request) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Reservation.class, id));
        checkReservationOwner(reservation);
        ReservationStatus status = request.getStatus();
        checkUpdateStatus(request.getStatus());
        reservation.setStatus(status);
        Reservation reservationRes = reservationRepository.save(reservation);
        return ReservationResponse.from(reservationRes);
    }

    public ReservationResponse addItemToReservation(int id, ItemRequest request) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Reservation.class, id));

        if (reservation.getStatus().equals(ReservationStatus.NEW))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Item cannot be added because Order is " + reservation.getStatus());

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(Product.class, request.getProductId()));
        Item item = request.to();
        item.setProduct(product);
        reservation.addItem(item);
        return ReservationResponse.from(reservationRepository.save(reservation));
    }

    private User getUserFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    private Customer getCustomerFromAuth() {
        return getUserFromAuth().getCustomer();
    }

    private void checkReservationOwner(Reservation reservation) {
        User user = getUserFromAuth();

        if (user.getType().equals(UserType.ADMIN))
            return;

        if (!user.getCustomer().getId().equals(reservation.getCustomer().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Access Denied: Client doesn't own this reservation");
    }

    private void checkUpdateStatus(ReservationStatus status) {
        User user = getUserFromAuth();

        if (user.getType().equals(UserType.ADMIN) && (status.equals(ReservationStatus.NEW) ||
                status.equals(ReservationStatus.PLACED) ||
                status.equals(ReservationStatus.CANCELLED))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot update reservation status to " + status);
        }

        if (user.getType().equals(UserType.CLIENT) && (status.equals(ReservationStatus.PROCESSED) ||
                status.equals(ReservationStatus.ARRIVED) ||
                status.equals(ReservationStatus.DEPARTED) ||
                status.equals(ReservationStatus.CANCELLED))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot update reservation status to " + status);
        }
    }

    @Override
    public List<ReservationResponse> getAllByCustomer(Customer customer) {
        return ReservationResponse.fromList(reservationRepository.findAllByCustomer(customer));
    }

    @Override
    public boolean exists(int id) {
        return reservationRepository.existsById(id);
    }
}