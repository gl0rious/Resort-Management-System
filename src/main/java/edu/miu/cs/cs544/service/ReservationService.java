package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.request.ItemRequest;
import edu.miu.cs.cs544.dto.request.ReservationRequest;
import edu.miu.cs.cs544.dto.request.ReservationStatusRequest;
import edu.miu.cs.cs544.dto.response.ReservationResponse;

import java.util.List;

public interface ReservationService {

    ReservationResponse createReservation(ReservationRequest reservationDTO);

    List<ReservationResponse> getAll();

    ReservationResponse getById(int id);

    boolean exists(int id);

    ReservationResponse updateStatus(int id, ReservationStatusRequest request);

    void cancel(int id);

    List<ReservationResponse> getAllByCustomer(Customer customer);

    // ReservationResponse addItemToReservation(int id, ItemRequest request);
}