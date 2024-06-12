package Dress.app.services;

import Dress.app.Models.Item;
import Dress.app.repos.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    private ItemRepository repo;

    @Autowired
    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public void save(Item item){
        repo.save(item);
    }

    public void saveAll(List<Item> items) {
        repo.saveAll(items);
    }

    public List<Item> getAll(){
        return repo.findAll();
    }

    public Optional<Item> get(UUID id) {
        return repo.findById(id);
    }
}
