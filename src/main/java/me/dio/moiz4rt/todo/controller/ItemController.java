package me.dio.moiz4rt.todo;

import me.dio.moiz4rt.todo.model.Item;
import me.dio.moiz4rt.todo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController
{
    private final ItemService itemService;

    public ItemController(ItemService itemService)
    {
        this.itemService = itemService;
    }

	@GetMapping("/{id}")
	public ResponseEntity<Item> findById(@PathVariable Long id)
    {
        var item = itemService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item)
    {
        var newItem = itemService.create(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newItem.getId()).toUri();
        return ResponseEntity.created(location).body(newItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updatedItem)
    {
        Item item = itemService.findById(id);
        if(item != null)
        {
            item.setTitulo(updatedItem.getTitulo());
            item.setDescricao(updatedItem.getDescricao());
            itemService.save(item);
            return ResponseEntity.ok(item);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id)
    {
        boolean isRemoved = itemService.deleteItem(id);
        if(!isRemoved)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
