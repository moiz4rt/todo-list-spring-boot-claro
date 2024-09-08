package me.dio.moiz4rt.todo.controller;

import me.dio.moiz4rt.todo.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/items")
public class ViewController
{
    private final ItemService itemService;

    public ViewController(ItemService itemService)
    {
        this.itemService = itemService;
    }

    @GetMapping
    public String getItems(Model model)
    {
        model.addAttribute("items", itemService.findAll());
        return "items";
    }
}
