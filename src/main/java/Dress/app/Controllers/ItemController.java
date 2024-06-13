package Dress.app.Controllers;

import Dress.app.Mappers.infoFromItems;
import Dress.app.Models.Item;
import Dress.app.Responses.getAllItemsResponse;
import Dress.app.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Void> saveItem(@RequestBody Item item) {
        itemService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/saveAll")  // сохраняем наши джейсончики вещей
    public ResponseEntity<Void> saveAllItems(@RequestBody List<Item> items) {
        itemService.saveAll(items);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/link")
    public ResponseEntity<infoFromItems> getItemInfoByLink(@RequestParam String link) {
        Item item = itemService.getItemByLink(link);
        return ResponseEntity.ok(infoFromItems.createInfoAboutItem(item));
    }

    @GetMapping //получаем все вещи (название, ссылка, цвет вещи)
    public ResponseEntity<List<getAllItemsResponse>> getAllItems() {
        List<Item> items = itemService.getAll();
        return ResponseEntity.ok(infoFromItems.createInfoNameLinkColor(items));
    }

    @GetMapping("/user") //получаем гардероб юзера!!! (название, ссылка, цвет вещи)
    public ResponseEntity<List<getAllItemsResponse>> getUsersItems(@RequestParam UUID userId) {
        List<Item> items = itemService.getUsersItems(userId);
        return ResponseEntity.ok(infoFromItems.createInfoNameLinkColor(items));
    }
    @GetMapping("/userFavourites") //получаем любимые юзера!!! (название, ссылка, цвет вещи)
    public ResponseEntity<List<getAllItemsResponse>> getUsersFavourites(@RequestParam UUID userId) {
        List<Item> items = itemService.getUsersFavourites(userId);
        return ResponseEntity.ok(infoFromItems.createInfoNameLinkColor(items));
    }

    @GetMapping("/getById")
    public ResponseEntity<Item> getItemById(@RequestParam UUID id) {
        Optional<Item> item = itemService.get(id);
        if (item.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(item.get());
        }
    }
}
