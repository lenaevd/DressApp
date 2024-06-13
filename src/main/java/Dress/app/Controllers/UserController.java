package Dress.app.Controllers;

import Dress.app.Mappers.CreateUserReqToUser;
import Dress.app.Models.Item;
import Dress.app.Models.User;
import Dress.app.Requests.CreateUserRequest;
import Dress.app.Requests.GetUserByName;
import Dress.app.Requests.addItemToUserRequest;
import Dress.app.services.ItemService;
import Dress.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    public ResponseEntity<Map<String, UUID>> saveUser(@RequestBody CreateUserRequest request) {
        User user = mapper.Map(request);
        UUID savedUserId = userService.save(user);
        Map<String, UUID> response = new HashMap<>();
        if (savedUserId != null) {
            response.put("id", savedUserId);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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

    @PostMapping("addUsersItem") //добавляем вещь пользователю
    public ResponseEntity<String> addItemToUser(@RequestBody addItemToUserRequest request) {
        String response = userService.addItem(request.userId, request.itemId);
        if (Objects.equals(response, "Item saved")) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/removeUsersItem") // удаляем вещь пользователя
    public ResponseEntity<String> removeUsersItem(@RequestBody addItemToUserRequest request) {
        String response = userService.removeItem(request.userId, request.itemId);
        if (Objects.equals(response, "Item removed")) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("addFavourite") //добавляем вещь в любимые
    public ResponseEntity<String> addItemToUFavourites(@RequestBody addItemToUserRequest request) {
        String response = userService.addItemToFavourites(request.userId, request.itemId);
        if (Objects.equals(response, "Item saved")) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("removeFavourite") //удаляем из любимых
    public ResponseEntity<String> removeItemFromFavourites(@RequestBody addItemToUserRequest request) {
        String response = userService.removeItemFromFavourites(request.userId, request.itemId);
        if (Objects.equals(response, "Item removed")) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
