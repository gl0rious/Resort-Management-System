package edu.miu.cs.cs544.service.imp;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceIMP implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation ReservationRequest = reservationDTO.to();
        Reservation reservationResponse = reservationRepository.save(ReservationRequest);
        return ReservationDTO.from(reservationResponse);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return ReservationDTO.fromList(reservationRepository.findAll());
    }

    @Override
    public ReservationDTO getReservation(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Product.class, id));
        return ReservationDTO.from(reservation);
    }

    @Override
    public ReservationDTO updateReservation(int id, ReservationDTO reservationDTO) {
        Reservation reservationRequest = reservationDTO.to();
        reservationRequest.setId(id);
        Reservation reservationResponse = reservationRepository.save(reservationRequest);
        return ReservationDTO.from(reservationResponse);
    }

    @Override
    public void cancelReservation(int id) {
        reservationRepository.deleteById(id);
    }
}