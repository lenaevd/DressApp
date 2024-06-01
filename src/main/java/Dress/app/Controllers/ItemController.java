package Dress.app.Controllers;

import Dress.app.Models.Item;
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

    @PostMapping("/saveAll")
    public ResponseEntity<Void> saveAllItems(@RequestBody List<Item> items) {
        itemService.saveAll(items);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAll();
        return ResponseEntity.ok(items);
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
