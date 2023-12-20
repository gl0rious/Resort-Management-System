package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    ReservationDTO createReservation(ReservationDTO reservationDTO);

    List<ReservationDTO> getAllReservations();

    ReservationDTO getReservation(int id);

    ReservationDTO updateReservation(int id, ReservationDTO reservationDTO);

    void cancelReservation(int id);

    List<ReservationDTO> getAllReservationsByCustomer(Customer customer);
}