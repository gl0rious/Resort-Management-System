
package edu.miu.cs.cs544.cs544.service;

import edu.miu.cs.cs544.controller.SpringSecurityWebTestConfig;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.ReservationStatus;
import edu.miu.cs.cs544.dto.response.ReservationResponse;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(SpringSecurityWebTestConfig.class)
public class ReservationServiceIMPTest {

    @Autowired
    private ReservationServiceImpl reservationServiceIMP;
    @MockBean
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setStatus(ReservationStatus.NEW);
        Customer customer = new Customer();
        customer.setId(2);
        reservation.setCustomer(customer);

        Optional<Reservation> reservationOptional = Optional.of(reservation);
        Mockito.when(reservationRepository.findById(1)).thenReturn(reservationOptional);

        reservation.setStatus(ReservationStatus.ARRIVED);
        Optional<Reservation> reservationOptionalUpdate = Optional.of(reservation);
        Mockito.when(reservationRepository.findById(1)).thenReturn(reservationOptionalUpdate);
    }

    @Test
    @WithMockUser
    public void whenValidReservationThenReservationShouldBeFound() {
        ReservationResponse found = reservationServiceIMP.getById(1);
        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getCustomer().getId()).isEqualTo(2);
    }

    @Test
    @WithMockUser
    public void whenUpdateReservationThenReservationShouldBeFound() {
        ReservationResponse found = reservationServiceIMP.getById(1);
        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getCustomer().getId()).isEqualTo(2);
        assertThat(found.getStatus()).isEqualTo(ReservationStatus.ARRIVED);
    }
}