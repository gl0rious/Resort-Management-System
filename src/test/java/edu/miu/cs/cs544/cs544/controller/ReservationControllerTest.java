package edu.miu.cs.cs544.cs544.controller;


import edu.miu.cs.cs544.controller.ReservationController;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.ReservationStatus;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    MockMvc mock;

    @MockBean
    ReservationService reservationService;

    @Test
    public void testGetReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setStatus(ReservationStatus.NEW);
        Customer customer = new Customer();
        customer.setId(2);
        reservation.setCustomer(customer);

        Mockito.when(reservationService.getReservation(1)).thenReturn(ReservationDTO.from(reservation));
        mock.perform(MockMvcRequestBuilders.get("/api/v1/reservations/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

    }

    @Test
    public void testGetNotExistReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setStatus(ReservationStatus.NEW);
        Customer customer = new Customer();
        customer.setId(2);
        reservation.setCustomer(customer);

        Mockito.when(reservationService.getReservation(999)).thenReturn(ReservationDTO.from(reservation));
        mock.perform(MockMvcRequestBuilders.get("/api/v1/reservations/{id}", 999).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateNewReservationWhenNoReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(2);
        reservation.setStatus(ReservationStatus.NEW);
        Customer customer = new Customer();
        customer.setId(2);
        reservation.setCustomer(customer);
        ReservationDTO reservationDTO = ReservationDTO.from(reservation);


        Mockito.when(reservationService.createReservation(reservationDTO)).thenReturn(reservationDTO);
        mock.perform(MockMvcRequestBuilders.get("/api/v1/reservations/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(ReservationStatus.NEW))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(0.0))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.accountHolder").value("Mahmoud Osman"))
        ;

    }
}
