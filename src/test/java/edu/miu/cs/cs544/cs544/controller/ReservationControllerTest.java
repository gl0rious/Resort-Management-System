//package edu.miu.cs.cs544.cs544.controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import edu.miu.cs.cs544.controller.ReservationController;
//import edu.miu.cs.cs544.domain.Customer;
//import edu.miu.cs.cs544.domain.Product;
//import edu.miu.cs.cs544.domain.Reservation;
//import edu.miu.cs.cs544.domain.ReservationStatus;
//import edu.miu.cs.cs544.dto.ProductDTO;
//import edu.miu.cs.cs544.dto.ReservationDTO;
//import edu.miu.cs.cs544.dto.response.ReservationResponse;
//import edu.miu.cs.cs544.service.ReservationService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ReservationController.class)
//public class ReservationControllerTest {
//
//    @Autowired
//    MockMvc mock;
//
//    @MockBean
//    ReservationService reservationService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Test
//    @WithMockUser(username = "mahmoud", roles = "USER")
//    public void testGetReservation() throws Exception {
////        Product product = new Product();
////        product.setId(1);
////        Mockito.when(productService.getProduct(1)).thenReturn(ProductDTO.from(product));
////        mock.perform(MockMvcRequestBuilders.get("/products/1").contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
//
//
//        ReservationResponse response = new ReservationResponse();
//        response.setId(1);
//        response.setStatus(ReservationStatus.NEW);
//        Customer customer = new Customer();
//        customer.setId(2);
//        response.setCustomer(customer);
//
//        Mockito.when(reservationService.getReservation(1)).thenReturn(response);
//        mock.perform(MockMvcRequestBuilders.get("/reservations/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
//
//    }
//
////    @Test
////    public void testGetNotExistReservation() throws Exception {
////        Reservation reservation = new Reservation();
////        reservation.setId(1);
////        reservation.setStatus(ReservationStatus.NEW);
////        Customer customer = new Customer();
////        customer.setId(2);
////        reservation.setCustomer(customer);
////
////        Mockito.when(reservationService.getReservation(999)).thenReturn(ReservationDTO.from(reservation));
////        mock.perform(MockMvcRequestBuilders.get("/api/v1/reservations/{id}", 999).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
////    }
////
////    @Test
////    @WithMockUser(username = "mahmoud", roles = "USER")
////    public void shouldCreateNewReservationWhenNoReservation() throws Exception {
////        Reservation reservation = new Reservation();
////        reservation.setId(1);
////        reservation.setStatus(ReservationStatus.NEW);
////        Customer customer = new Customer();
////        customer.setId(2);
////        reservation.setCustomer(customer);
////        ReservationDTO reservationDTO = ReservationDTO.from(reservation);
////
////        String json = objectMapper.writeValueAsString(reservationDTO);
////
////        Mockito.when(reservationService.createReservation(reservationDTO)).thenReturn(ReservationDTO.from(reservation));
////        mock.perform(MockMvcRequestBuilders.post("/reservations").contentType(MediaType.APPLICATION_JSON)
////                        .with(SecurityMockMvcRequestPostProcessors.csrf())
////                        .content(json))
////                .andExpect(MockMvcResultMatchers.status().isCreated())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
////
////    }
//}
