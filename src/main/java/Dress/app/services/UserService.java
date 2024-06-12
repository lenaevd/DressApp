package Dress.app.services;

import Dress.app.Models.User;
import Dress.app.Requests.GetUserByName;
import Dress.app.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UUID save(User user){
        repo.save(user);
        return user.getId();
    }

    public Optional<User> get(UUID id) {
        return repo.findById(id);
    }

    public User getByName(GetUserByName request) {
        return repo.findUserByNameAndPassword(request.name, request.password);
    }
}
