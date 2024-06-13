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
    public ResponseEntity<Void> addItemToUser(@RequestBody addItemToUserRequest request) {
        userService.addItem(request.userId, request.itemId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/removeUsersItem") // удаляем вещь пользователя
    public ResponseEntity<Void> removeUsersItem(@RequestBody addItemToUserRequest request) {
        userService.removeItem(request.userId, request.itemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("addFavourite") //добавляем вещь в любимые
    public ResponseEntity<Void> addItemToUFavourites(@RequestBody addItemToUserRequest request) {
        userService.addItemToFavourites(request.userId, request.itemId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("removeFavourite") //удаляем из любимых
    public ResponseEntity<Void> removeItemFromFavourites(@RequestBody addItemToUserRequest request) {
        userService.removeItemFromFavourites(request.userId, request.itemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
