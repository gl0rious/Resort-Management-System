package edu.miu.cs.cs544.cs544.service;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.response.ItemDTO;
import edu.miu.cs.cs544.repository.ItemRepository;
import edu.miu.cs.cs544.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemServiceImplTest {

    @Autowired
    private ItemServiceImpl itemService;
    @MockBean
    private ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        Item item = new Item();
        item.setId(1);
        Reservation reservation = new Reservation();
        reservation.setId(1);
        item.setOrder(reservation);
        ItemDTO itemDTO = ItemDTO.from(item);

        Optional<Item> itemOptional = Optional.of(item);
        Mockito.when(itemRepository.findById(1)).thenReturn(itemOptional);

        reservation.setId(2);
        item.setOrder(reservation);
        Optional<Item> itemOptionalUpdate = Optional.of(item);
        Mockito.when(itemRepository.findById(1)).thenReturn(itemOptionalUpdate);
    }

    @Test
    public void whenValidItemThenItemShouldBeFound() {
        Item found = itemService.getItem(1);
        assertThat(found.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidItemUpdatedThenItemShouldBeFound() {
        Item found = itemService.getItem(1);
        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getOrder().getId()).isEqualTo(2);
    }

}