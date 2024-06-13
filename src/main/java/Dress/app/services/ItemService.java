package Dress.app.services;

import Dress.app.Models.Item;
import Dress.app.Models.User;
import Dress.app.repos.ItemRepository;
import Dress.app.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    private final ItemRepository itemRepo;
    private final UserRepository userRepo;

    @Autowired
    public ItemService(ItemRepository itemRepo, UserRepository userRepo) {
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
    }

    public void save(Item item){
        itemRepo.save(item);
    }

    public void saveAll(List<Item> items) {
        itemRepo.saveAll(items);
    }

    public List<Item> getAll(){
        return itemRepo.findAll();
    }

    public List<Item> getUsersItems(UUID id) {
        User user = userRepo.findById(id).get();
        return user.getItems();
    }

    public Item getItemByLink(String link) {
        return itemRepo.findByLink(link);
    }

    public List<Item> getUsersFavourites(UUID id) {
        User user = userRepo.findById(id).get();
        return user.getFavourites();
    }

    public Optional<Item> get(UUID id) {
        return itemRepo.findById(id);
    }
}
