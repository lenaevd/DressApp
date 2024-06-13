package Dress.app.services;

import Dress.app.Models.Item;
import Dress.app.Models.User;
import Dress.app.Requests.GetUserByName;
import Dress.app.repos.ItemRepository;
import Dress.app.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final ItemRepository itemRepo;

    @Autowired
    public UserService(UserRepository userRepo, ItemRepository itemRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
    }

    public UUID save(User user){
        userRepo.save(user);
        return user.getId();
    }

    public Optional<User> get(UUID id) {
        return userRepo.findById(id);
    }

    public User getByName(GetUserByName request) {
        return userRepo.findUserByNameAndPassword(request.name, request.password);
    }

    public void addItem(UUID userId, UUID itemId) {
        User user = userRepo.findById(userId).get();
        Item item = itemRepo.findById(itemId).get();
        user.addItem(item);
        userRepo.save(user);
    }

    public void removeItem(UUID userId, UUID itemId) {
        User user = userRepo.findById(userId).get();
        Item item = itemRepo.findById(itemId).get();
        user.removeItem(item);
        userRepo.save(user);
    }

    public void addItemToFavourites(UUID userId, UUID itemId) {
        User user = userRepo.findById(userId).get();
        Item item = itemRepo.findById(itemId).get();
        user.addItemToFavourites(item);
        userRepo.save(user);
    }

    public void removeItemFromFavourites(UUID userId, UUID itemId) {
        User user = userRepo.findById(userId).get();
        Item item = itemRepo.findById(itemId).get();
        user.removeItemFromFavourites(item);
        userRepo.save(user);
    }
}
