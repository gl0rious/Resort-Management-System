package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Item;

import java.util.List;

public interface ItemService {

    public Item addItem(Item item);
    public Item updateItem(int id, Item item);

    public Item getItem(int id);

//    public void searchItem(int id);
    public void deleteItem(int id);

    public List<Item> getAllItems(int id);
}
