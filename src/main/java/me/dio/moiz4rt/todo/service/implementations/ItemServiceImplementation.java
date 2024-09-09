package me.dio.moiz4rt.todo.service.implementations;

import me.dio.moiz4rt.todo.model.Item;
import me.dio.moiz4rt.todo.service.ItemService;
import me.dio.moiz4rt.todo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.List;

@Service
public class ItemServiceImplementation implements ItemService
{
    private final ItemRepository itemRepository;

    public ItemServiceImplementation(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item findById(Long id)
    {
        return itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    @Override
    public Item create(Item newItem)
    {
        if(newItem.getId() != null && itemRepository.existsById(newItem.getId()))
        {
            throw new IllegalArgumentException("This user ID  already exists!");
        }
        return itemRepository.save(newItem);
    }

    @Override
    public List<Item> findAll()
    {
        return itemRepository.findAll();
    }

    @Override
    public boolean deleteItem(Long id)
    {
        if(itemRepository.existsById(id))
        {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
public Item save(Item updatedItem) {
    // Verifica se o item existe antes de tentar atualizá-lo
    if(updatedItem.getId() == null || !itemRepository.existsById(updatedItem.getId())) {
        throw new NoSuchElementException("Item not found with id: " + updatedItem.getId());
    }

    // Salva e retorna o item atualizado
    return itemRepository.save(updatedItem);
}

}
