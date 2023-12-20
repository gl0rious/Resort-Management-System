package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.dto.request.ItemRequest;
import edu.miu.cs.cs544.dto.request.ReservationStatusRequest;
import edu.miu.cs.cs544.dto.response.ReservationResponse;

import java.util.List;

public interface ReservationService {

    ReservationResponse createReservation(ReservationDTO reservationDTO);

    List<ReservationResponse> getAllReservations();

    ReservationResponse getReservation(int id);

    ReservationResponse updateReservationStatus(int id, ReservationStatusRequest request);

    void cancelReservation(int id);

    public ReservationResponse createItemForReservation(int id, ItemRequest request);
}