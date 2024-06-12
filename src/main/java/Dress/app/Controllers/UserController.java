package Dress.app.Controllers;

import Dress.app.Mappers.CreateUserReqToUser;
import Dress.app.Models.Item;
import Dress.app.Models.User;
import Dress.app.Requests.CreateUserRequest;
import Dress.app.Requests.GetUserByName;
import Dress.app.services.ItemService;
import Dress.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final CreateUserReqToUser mapper;

    @Autowired
    public UserController(
            UserService userService,
            CreateUserReqToUser mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UUID> saveUser(@RequestBody CreateUserRequest request) {
        User user = mapper.Map(request);
        UUID savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/getByName")
    public ResponseEntity<User> getUser(@RequestBody GetUserByName request) {
        User user = userService.getByName(request);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<User> getUser(@RequestParam UUID id) {
        Optional<User> user = userService.get(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user.get());
        }
    }
}
