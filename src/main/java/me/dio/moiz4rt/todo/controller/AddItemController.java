package me.dio.moiz4rt.todo.controller;

import me.dio.moiz4rt.todo.model.Item;
import me.dio.moiz4rt.todo.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/add")
public class AddItemController
{
    private final ItemService itemService;

    public AddItemController(ItemService itemService)
    {
        this.itemService = itemService;
    }

    @GetMapping
    public String getAddItemPage()
    {
        return "add";
    }

    @PostMapping
    public String addNewItem(@RequestParam String titulo, @RequestParam String descricao)
    {
        Item newItem = new Item();
        newItem.setTitulo(titulo);
        newItem.setDescricao(descricao);
        itemService.create(newItem);
        return "redirect:/items";
    }
}
