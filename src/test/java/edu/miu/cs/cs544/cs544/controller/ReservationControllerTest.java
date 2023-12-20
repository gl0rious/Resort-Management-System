package edu.miu.cs.cs544.cs544.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs544.controller.ReservationController;
import edu.miu.cs.cs544.controller.SpringSecurityWebTestConfig;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.ReservationStatus;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@WebMvcTest(ReservationController.class)
@Import(SpringSecurityWebTestConfig.class)
// @SpringBootTest
// @AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    MockMvc mock;

    @MockBean
    ReservationService reservationService;

    @Autowired
    ObjectMapper objectMapper;

    // @Autowired
    // private UserDetailsService userDetailsService;

    @Test
    @WithMockUser
    public void testGetReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setStatus(ReservationStatus.NEW);
        Customer customer = new Customer();
        customer.setId(2);
        reservation.setCustomer(customer);
        User user = new User("user1", "{noop}password", UserType.ADMIN);
        Mockito.when(reservationService.getReservation(1)).thenReturn(ReservationDTO.from(reservation));
        mock.perform(MockMvcRequestBuilders.get("/reservations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

    }

    @Test
    @WithMockUser
    public void testGetNotExistReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setStatus(ReservationStatus.NEW);
        Customer customer = new Customer();
        customer.setId(2);
        reservation.setCustomer(customer);

        Mockito.when(reservationService.getReservation(999)).thenReturn(ReservationDTO.from(reservation));
        mock.perform(
                MockMvcRequestBuilders.get("/api/v1/reservations/{id}", 999).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void shouldCreateNewReservationWhenNoReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setStatus(ReservationStatus.NEW);
        Customer customer = new Customer();
        customer.setId(2);
        reservation.setCustomer(customer);
        ReservationDTO reservationDTO = ReservationDTO.from(reservation);

        String json = objectMapper.writeValueAsString(reservationDTO);

        Mockito.when(reservationService.createReservation(reservationDTO)).thenReturn(ReservationDTO.from(reservation));
        mock.perform(MockMvcRequestBuilders.post("/reservations").contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

    }

    @Test
    // @WithMockUser(roles = "CLIENT") // (value = "user1") // ,
    // userDetailsServiceBeanName = "userDetailsService")
    @WithUserDetails(value = "user1", userDetailsServiceBeanName = "userDetailsService")
    public void shouldReturnReservation_WhenCustomerIsOwner() throws Exception {
        // Object principal =
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // User user = (User) principal;
        User user = new User("user1", "{noop}password", UserType.CLIENT);
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setStatus(ReservationStatus.NEW);
        Customer customer = new Customer();
        customer.setId(1);
        customer.setUser(user);
        user.setCustomer(customer);
        reservation.setCustomer(customer);

        Mockito.when(reservationService.getReservation(1)).thenReturn(ReservationDTO.from(reservation));
        mock.perform(
                MockMvcRequestBuilders.get("/reservations/1")
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .with(user(user)))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "user1", userDetailsServiceBeanName = "userDetailsService")
    public void shouldReturnAnAuh_WhenCustomerIsNotOwner() throws Exception {
        // Object principal =
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // User user = (User) principal;
        User user1 = new User("user1", "{noop}password", UserType.CLIENT);
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setUser(user1);
        user1.setCustomer(customer1);

        User user2 = new User("user2", "{noop}password", UserType.CLIENT);
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setUser(user2);
        user2.setCustomer(customer2);

        Reservation reservation = new Reservation();
        reservation.setId(2);
        reservation.setStatus(ReservationStatus.NEW);

        reservation.setCustomer(customer1);

        Mockito.when(reservationService.getReservation(1)).thenReturn(ReservationDTO.from(reservation));
        mock.perform(
                MockMvcRequestBuilders.get("/reservations/1")
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .with(user(user2)))
                .andExpect(status().isUnauthorized());
    }
}
