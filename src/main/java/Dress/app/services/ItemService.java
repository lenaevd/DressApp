package Dress.app.services;

import Dress.app.Models.Item;
import Dress.app.repos.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Item> getAll(){
        return repo.findAll();
    }

}
