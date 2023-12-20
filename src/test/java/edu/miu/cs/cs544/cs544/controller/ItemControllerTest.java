//package edu.miu.cs.cs544.cs544.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import edu.miu.cs.cs544.controller.ItemController;
//import edu.miu.cs.cs544.domain.Item;
//import edu.miu.cs.cs544.domain.Product;
//import edu.miu.cs.cs544.domain.ProductType;
//import edu.miu.cs.cs544.domain.Reservation;
//import edu.miu.cs.cs544.dto.ProductDTO;
//import edu.miu.cs.cs544.dto.ReservationDTO;
//import edu.miu.cs.cs544.dto.response.ItemDTO;
//import edu.miu.cs.cs544.service.ItemService;
//import edu.miu.cs.cs544.service.ReservationService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.Mockito.*;
//
//@WebMvcTest(ItemController.class)
//class ItemControllerTest {
//
//    @InjectMocks
//    private ItemController itemController;
//
//    @Mock
//    private ItemService itemService;
//
//    @Mock
//    private ReservationService reservationService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//    @Autowired
//    MockMvc mock;
//
//
//    @Test
//    @WithMockUser(username = "mahmoud", roles = "USER")
//    public void shouldCreateNewItemWhenNoItemExist() throws Exception {
//        Item item = new Item();
//        item.setId(1);
//        Reservation reservation = new Reservation();
//        reservation.setId(1);
//        item.setOrder(reservation);
//        ItemDTO itemDTO = ItemDTO.from(item);
//        String json = objectMapper.writeValueAsString(itemDTO);
//        Mockito.when(itemService.addItem(item)).thenReturn(item);
//
//        mock.perform(MockMvcRequestBuilders.post("/items").contentType(MediaType.APPLICATION_JSON)
//                        .with(SecurityMockMvcRequestPostProcessors.csrf())
//                        .content(json))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
//
//    }
//
////    @Test
////    void testAddItem() {
////        Item item = new Item();
////        Reservation reservation = new Reservation();
////        item.setOrder(reservation);
////
////
////        when(reservationService.getReservation(item.getOrder().getId())).thenReturn(ReservationDTO.from(reservation));
////        when(itemService.addItem(item)).thenReturn(item);
////        ResponseEntity<?> responseEntity = itemController.addItem(item);
////        verify(reservationService, times(1)).getReservation(item.getOrder().getId());
////        verify(itemService, times(1)).addItem(item);
////        assert responseEntity != null;
////        assert responseEntity.getStatusCode() == HttpStatus.OK;
////    }
////
////    @Test
////    public void testGetItem() {
////        Item item = new Item();
////        Reservation reservation = new Reservation();
////        item.setOrder(reservation);
////
////        when(itemService.getItem(anyInt())).thenReturn(item);
////        ResponseEntity<?> responseEntity = itemController.getItem(1);
////        verify(itemService, times(1)).getItem(anyInt());
////        assert responseEntity != null;
////        assert responseEntity.getStatusCode() == HttpStatus.OK;
////    }
////
////    @Test
////    public void testUpdateItem() {
////        Item item = new Item();
////        Reservation reservation = new Reservation();
////        item.setOrder(reservation);
////        when(reservationService.getReservation(item.getOrder().getId())).thenReturn(ReservationDTO.from(reservation));
////        when(itemService.updateItem(anyInt(), any(Item.class))).thenReturn(item);
////
////        ResponseEntity<?> responseEntity = itemController.updateItem(1, item);
////        verify(reservationService, times(1)).getReservation(item.getOrder().getId());
////        verify(itemService, times(1)).updateItem(anyInt(), any(Item.class));
////        assert responseEntity != null;
////        assert responseEntity.getStatusCode() == HttpStatus.OK;
////    }
////
////
////    @Test
////    public void testGetAllItems() {
////        Item item = new Item();
////        Reservation reservation = new Reservation();
////        item.setOrder(reservation);
////        // Mock repository behavior
////        when(itemService.addItem(item)).thenReturn(item);
////        // Call the controller method
////        ResponseEntity<?> responseEntity = itemController.getAllItems(item);
////        // Verify the result
////        verify(itemService, times(1)).addItem(item);
////        assert responseEntity != null;
////        assert responseEntity.getStatusCode() == HttpStatus.OK;
////    }
////
////    @Test
////    public void testDeleteItem() {
////        Item item = new Item();
////        Reservation reservation = new Reservation();
////        item.setOrder(reservation);
////        // Mock repository behavior
////        doNothing().when(itemService).deleteItem(anyInt());
////        // Call the controller method
////        ResponseEntity<?> responseEntity = itemController.deleteItem(1);
////        // Verify the result
////        verify(itemService, times(1)).deleteItem(anyInt());
////        assert responseEntity != null;
////        assert responseEntity.getStatusCode() == HttpStatus.OK;
////    }
//
//
//}
//
