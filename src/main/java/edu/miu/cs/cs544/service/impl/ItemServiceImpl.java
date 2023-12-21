package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.request.ItemRequest;
import edu.miu.cs.cs544.dto.response.ItemResponse;
import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.repository.ItemRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override

    public ItemResponse addItem(ItemRequest request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new ResourceNotFoundException(Reservation.class, request.getReservationId()));
        Item item = ItemRequest.to(request);
        item.setOrder(reservation);
        reservation.getItems().add(item);
        reservationRepository.save(reservation);
        Item result = itemRepository.save(item);
        return ItemResponse.from(result);
    }

    @Override
    public Item updateItem(int id, Item item) {
        Reservation reservation = reservationRepository.findById(item.getOrder().getId()).orElse(null);
        if (reservation != null) {
            item.setOrder(reservation);
            Item result = itemRepository.save(item);
            return result;
        }
        return null;
    }

    @Override
    public Item getItem(int id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getAllItems(int id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            return reservation.getItems();
        }
        return null;
    }
}
