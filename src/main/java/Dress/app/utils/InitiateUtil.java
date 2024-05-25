package Dress.app.utils;

import Dress.app.Models.Item;
import Dress.app.services.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InitiateUtil implements CommandLineRunner {

    private final ItemService service;

    public InitiateUtil (ItemService service) {//незабываем конструктор для внедрения
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {

//создаем несколько сущностей фруктов, через сеттеры заполняем поля
        Item one = new Item();
        one.setId(UUID.fromString("2a53c2d0-f0de-4b27-8d3f-3c1210ade9a4"));
        one.setName("dress");
        one.setColor("blue");
        one.setLink("http://iuururu");
        one.setFabric("cotton");

        Item two = new Item();
        two.setId(UUID.randomUUID());
        two.setName("shirt");
        two.setColor("white");
        two.setLink("http://iuurururu");
        two.setFabric("cotton");

        service.save(one);
        service.save(two);

        List<Item> all = service.getAll();


        for (Item entity : all) {
            System.out.println(entity);
        }
    }
}
