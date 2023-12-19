package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.repository.ItemRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override

    public Item addItem(Item item) {
        Reservation reservation = reservationRepository.findById(item.getOrder().getId()).orElse(null);
        if (reservation != null) {
            item.setOrder(reservation);
            Item result = itemRepository.save(item);
            return result;
        }
        return null;
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
