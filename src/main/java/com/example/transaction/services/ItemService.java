package com.example.transaction.services;


import com.example.transaction.models.Item;
import com.example.transaction.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> searchItems(String keyword) {
        return itemRepository.findByNameContaining(keyword);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }
}
