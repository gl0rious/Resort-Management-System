package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Customer customer = customerRepository.findById(reservationDTO.getCustomerID())
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class, reservationDTO.getCustomerID()));
        Reservation reservationRequest = reservationDTO.to();
        reservationRequest.setCustomer(customer);
        Reservation reservationResponse = reservationRepository.save(reservationRequest);
        return ReservationDTO.from(reservationResponse);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return ReservationDTO.fromList(reservationRepository.findAll());
    }

    @Override
    public ReservationDTO getReservation(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Reservation.class, id));
        return ReservationDTO.from(reservation);
    }

    @Override
    public ReservationDTO updateReservation(int id, ReservationDTO reservationDTO) {
        Customer customer = customerRepository.findById(reservationDTO.getCustomerID())
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class, reservationDTO.getCustomerID()));
        Reservation reservationRequest = reservationDTO.to();
        reservationRequest.setId(id);
        reservationRequest.setCustomer(customer);
        Reservation reservationResponse = reservationRepository.save(reservationRequest);
        return ReservationDTO.from(reservationResponse);
    }

    @Override
    public void cancelReservation(int id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationDTO> getAllReservationsByCustomer(Customer customer) {
        List<Reservation> reservations = reservationRepository.findAllByCustomer(customer);
        List<ReservationDTO> reservationDTOS = reservations.stream().map(ReservationDTO::from)
                .collect(java.util.stream.Collectors.toList());
        return reservationDTOS;
    }
}