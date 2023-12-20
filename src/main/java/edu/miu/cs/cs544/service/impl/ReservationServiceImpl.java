package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.dto.request.ItemRequest;
import edu.miu.cs.cs544.dto.request.ReservationStatusRequest;
import edu.miu.cs.cs544.dto.response.ReservationResponse;
import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public ReservationResponse createReservation(ReservationDTO reservationDTO) {
        Reservation reservationRequest = reservationDTO.to();
        reservationRequest.setCustomer(getCustomerFromAuth());
        Reservation reservation = reservationRepository.save(reservationRequest);
        reservation.setStatus(ReservationStatus.NEW);
        return ReservationResponse.from(reservation);
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        return ReservationResponse.fromList(reservationRepository.findAll());
    }

    @Override
    public ReservationResponse getReservation(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Reservation.class, id));
        checkReservationOwner(reservation);
        return ReservationResponse.from(reservation);
    }

    @Override
    public void cancelReservation(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Reservation.class, id));
        checkReservationOwner(reservation);
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    public ReservationResponse updateReservationStatus(int id, ReservationStatusRequest request) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Reservation.class, id));
        checkReservationOwner(reservation);
        ReservationStatus status = request.getStatus();
        checkUpdateStatus(status, reservation.getStatus());
        reservation.setStatus(status);
        Reservation reservationRes = reservationRepository.save(reservation);
        return ReservationResponse.from(reservationRes);
    }

    public ReservationResponse createItemForReservation(int id, ItemRequest request) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Reservation.class, id));

        if (reservation.getStatus().equals(ReservationStatus.NEW))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item cannot be added because Order is " + reservation.getStatus());

        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new ResourceNotFoundException(Product.class, request.getProductId()));
        Item item = request.to();
        item.setProduct(product);
        reservation.addItem(item);
        return ReservationResponse.from(reservationRepository.save(reservation));
    }

    private User getUserFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }

    private Customer getCustomerFromAuth() {
        return getUserFromAuth().getCustomer();
    }

    private void checkReservationOwner(Reservation reservation) {
        User user = getUserFromAuth();

        if (user.getType().equals(UserType.ADMIN))
            return;

        if (!user.getCustomer().getId().equals(reservation.getCustomer().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Access Denied: Client doesn't own this reservation");
    }

    private void checkUpdateStatus(ReservationStatus statusFromRequest, ReservationStatus statusFromDB) {
        User user = getUserFromAuth();

        // Admin should not be able to change status to NEW OR PLACED
        if (user.getType().equals(UserType.ADMIN) &&
                (statusFromRequest.equals(ReservationStatus.NEW) ||
                statusFromRequest.equals(ReservationStatus.PLACED)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are forbidden to change status to " + statusFromRequest);

        if (user.getType().equals(UserType.CLIENT) &&
                (statusFromRequest.equals(ReservationStatus.ARRIVED) ||
                        statusFromRequest.equals(ReservationStatus.DEPARTED) ||
                        statusFromRequest.equals(ReservationStatus.CANCELLED)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are forbidden to change status to " + statusFromRequest);

        if (user.getType().equals(UserType.CLIENT) &&
                (statusFromDB.equals(ReservationStatus.PROCESSED) ||
                        statusFromDB.equals(ReservationStatus.ARRIVED) ||
                        statusFromDB.equals(ReservationStatus.DEPARTED) ||
                        statusFromDB.equals(ReservationStatus.CANCELLED)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are forbidden to change status to " + statusFromRequest);
    }
}