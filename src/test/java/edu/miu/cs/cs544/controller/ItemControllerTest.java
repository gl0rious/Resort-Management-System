package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.dto.response.ReservationResponse;
import edu.miu.cs.cs544.service.ItemService;
import edu.miu.cs.cs544.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    @Mock
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddItem() {
        Item item = new Item();
        Reservation reservation = new Reservation();
        item.setOrder(reservation);

        when(reservationService.getReservation(item.getOrder().getId())).thenReturn(ReservationResponse.from(reservation));
        when(itemService.addItem(item)).thenReturn(item);
        ResponseEntity<?> responseEntity = itemController.addItem(item);
        verify(reservationService, times(1)).getReservation(item.getOrder().getId());
        verify(itemService, times(1)).addItem(item);
        assert responseEntity != null;
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testGetItem() {
        Item item = new Item();
        Reservation reservation = new Reservation();
        item.setOrder(reservation);

        when(itemService.getItem(anyInt())).thenReturn(item);
        ResponseEntity<?> responseEntity = itemController.getItem(1);
        verify(itemService, times(1)).getItem(anyInt());
        assert responseEntity != null;
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testUpdateItem() {
        Item item = new Item();
        Reservation reservation = new Reservation();
        item.setOrder(reservation);
        when(reservationService.getReservation(item.getOrder().getId())).thenReturn(ReservationResponse.from(reservation));
        when(itemService.updateItem(anyInt(), any(Item.class))).thenReturn(item);

        ResponseEntity<?> responseEntity = itemController.updateItem(1, item);
        verify(reservationService, times(1)).getReservation(item.getOrder().getId());
        verify(itemService, times(1)).updateItem(anyInt(), any(Item.class));
        assert responseEntity != null;
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }


    @Test
    public void testGetAllItems() {
        Item item = new Item();
        Reservation reservation = new Reservation();
        item.setOrder(reservation);
        // Mock repository behavior
        when(itemService.addItem(item)).thenReturn(item);
        // Call the controller method
        ResponseEntity<?> responseEntity = itemController.getAllItems(item);
        // Verify the result
        verify(itemService, times(1)).addItem(item);
        assert responseEntity != null;
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testDeleteItem() {
        Item item = new Item();
        Reservation reservation = new Reservation();
        item.setOrder(reservation);
        // Mock repository behavior
        doNothing().when(itemService).deleteItem(anyInt());
        // Call the controller method
        ResponseEntity<?> responseEntity = itemController.deleteItem(1);
        // Verify the result
        verify(itemService, times(1)).deleteItem(anyInt());
        assert responseEntity != null;
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }


}

