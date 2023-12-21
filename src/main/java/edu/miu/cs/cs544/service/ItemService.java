package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.dto.request.ItemRequest;
import edu.miu.cs.cs544.dto.response.ItemResponse;

import java.util.List;

public interface ItemService {
    ItemResponse addItem(ItemRequest item);

    ItemResponse updateItem(ItemRequest item);

    ItemResponse getById(int id);

    void remove(int id);
    // List<Item> getAllItems(int id);
}
