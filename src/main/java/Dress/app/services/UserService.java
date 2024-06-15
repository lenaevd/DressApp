package Dress.app.services;

import Dress.app.Models.Item;
import Dress.app.Models.User;
import Dress.app.Requests.GetUserByName;
import Dress.app.repos.ItemRepository;
import Dress.app.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public String addItem(UUID userId, UUID itemId) {
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<Item> itemOptional = itemRepo.findById(itemId);
        if (userOptional.isPresent() && itemOptional.isPresent()) {
            User user = userOptional.get();
            user.addItem(itemOptional.get());
            userRepo.save(user);
            return "Item saved";
        } else {
            return "not found";
        }
    }

    public String addItems(UUID userId, List<String> itemsLinks) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            for (String items : itemsLinks) {
                Item itemOptional = itemRepo.findByLink(items);
                user.addItem(itemOptional);
                userRepo.save(user);
            }
            return "Item saved";
        }
         else {
            return "user not found";
        }
    }

    public String removeItem(UUID userId, UUID itemId) {
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<Item> itemOptional = itemRepo.findById(itemId);
        if (userOptional.isPresent() && itemOptional.isPresent()) {
            User user = userOptional.get();
            user.removeItem(itemOptional.get());
            userRepo.save(user);
            return "Item removed";
        } else {
            return "not found";
        }
    }

    public String addItemToFavourites(UUID userId, UUID itemId) {
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<Item> itemOptional = itemRepo.findById(itemId);
        if (userOptional.isPresent() && itemOptional.isPresent()) {
            User user = userOptional.get();
            user.addItemToFavourites(itemOptional.get());
            userRepo.save(user);
            return "Item saved";
        } else {
            return "not found";
        }
    }

    public String removeItemFromFavourites(UUID userId, UUID itemId) {
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<Item> itemOptional = itemRepo.findById(itemId);
        if (userOptional.isPresent() && itemOptional.isPresent()) {
            User user = userOptional.get();
            user.removeItemFromFavourites(itemOptional.get());
            userRepo.save(user);
            return "Item removed";
        } else {
            return "not found";
        }
    }
}
