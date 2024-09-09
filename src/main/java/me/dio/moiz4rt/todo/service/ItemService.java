package me.dio.moiz4rt.todo.service;

import me.dio.moiz4rt.todo.model.Item;
import java.util.List;

public interface ItemService
{
    Item findById(Long id);

    Item create(Item newItem);

    List<Item> findAll();

    boolean deleteItem(Long id);

    Item save(Item updatedItem);
}
