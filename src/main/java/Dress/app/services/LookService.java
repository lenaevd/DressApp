package Dress.app.services;

import Dress.app.Models.*;
import Dress.app.repos.ItemRepository;
import Dress.app.repos.LookRepository;
import Dress.app.repos.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LookService {
    private LookRepository lookRepo;
    private ItemRepository itemRepo;
    private StyleRepository styleRepo;


    @Autowired
    public LookService(LookRepository lookRepo, ItemRepository itemRepo, StyleRepository styleRepo) {
        this.lookRepo = lookRepo;
        this.itemRepo = itemRepo;
        this.styleRepo = styleRepo;
    }

    public List<Look> getAll() {
        return lookRepo.findAll();
    }

    public void removeSeasons(List<Item> items, List<Season> seasons) {
        items.removeIf(item -> !item.getSeasons().stream().anyMatch(seasons::contains));
    }

    public void removeStyles(List<Item> items, List<Style> styles) {
        items.removeIf(item -> !item.getStyles().stream().anyMatch(styles::contains));
    }

    //передаём какой вариант делать, список обязательных сезонов стилей и обязательная вещь если есть
    public Look createLook(int option, List<Season> seasons, List<Style> styles, Item item){
        String[] overTopNames = {"Outwear","Jacket"};
        List<String> overTop = Arrays.asList(overTopNames);

        String[] topNames = {"Shirt","Shortsleeve","Longsleeve"};
        List<String> top = Arrays.asList(topNames);

        String[] bottomNames = {"Jeans","Shorts","Skirt","Trousers"};
        List<String> bottom = Arrays.asList(bottomNames);
        //Also there are "Boots" and "Dress"
        if (item != null) {
            String itemType = item.getType();
            if (overTop.contains(itemType)) {
                option = 1;
            }
            if (top.contains(itemType) && option == 2) {
                option = 0;
            }
            if (bottom.contains(itemType) && option == 2) {
                option = 0;
            }
            if (Objects.equals(itemType, "Dress")) {
                option = 2;
            }
        }

        Look look = new Look();
        look.setUser(new User());
        Random rn = new Random();
        if (option == 0) { //делаем просто верх + низ + обувь
            List<Item> topItems = itemRepo.findAllByTypeIn(top);
            List<Item> bottomItems = itemRepo.findAllByTypeIn(bottom);
            List<Item> bootsItems = itemRepo.findAllByTypeIn(Collections.singletonList("Boots"));
            //убираем вещи с неподходящими сезонами и стилями
            removeStyles(bootsItems, styles);
            removeStyles(topItems, styles);
            removeStyles(bottomItems, styles);
            if (seasons != null) {
                removeSeasons(bootsItems, seasons);
                removeSeasons(bottomItems, seasons);
                removeSeasons(topItems, seasons);
            }
            //выбираем рандомные вещи и создаем лук
            if (item == null) {
                look.addPart(topItems.get(rn.nextInt(topItems.size() + 1)));
                look.addPart(bottomItems.get(rn.nextInt(bottomItems.size() + 1)));
                look.addPart(bootsItems.get(rn.nextInt(bootsItems.size() + 1)));
            } else {
                String itemType = item.getType();
                if (Objects.equals(itemType, "Boots")){
                    look.addPart(topItems.get(rn.nextInt(topItems.size() + 1)));
                    look.addPart(bottomItems.get(rn.nextInt(bottomItems.size() + 1)));
                    look.addPart(item);
                } else if (top.contains(itemType)) {
                    look.addPart(item);
                    look.addPart(bottomItems.get(rn.nextInt(bottomItems.size() + 1)));
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size() + 1)));
                } else if (bottom.contains(itemType)) {
                    look.addPart(topItems.get(rn.nextInt(topItems.size() + 1)));
                    look.addPart(item);
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size() + 1)));
                }
            }
        }

        if (option == 1) { //делаем верх + верх + низ + обувь
            List<Item> overTopItems = itemRepo.findAllByTypeIn(overTop);
            List<Item> topItems = itemRepo.findAllByTypeIn(top);
            List<Item> bottomItems = itemRepo.findAllByTypeIn(bottom);
            List<Item> bootsItems = itemRepo.findAllByTypeIn(Collections.singletonList("Boots"));

            //убираем вещи с неподходящими сезонами и стилями
            removeStyles(bootsItems, styles);
            removeStyles(overTopItems, styles);
            removeStyles(topItems, styles);
            removeStyles(bottomItems, styles);
            if (seasons != null) {
                removeSeasons(bootsItems, seasons);
                removeSeasons(bottomItems, seasons);
                removeSeasons(topItems, seasons);
                removeSeasons(overTopItems, seasons);
            }
            //выбираем рандомные вещи и создаем лук
            look.addPart(overTopItems.get(rn.nextInt(overTopItems.size() + 1)));
            look.addPart(topItems.get(rn.nextInt(topItems.size() + 1)));
            look.addPart(bottomItems.get(rn.nextInt(bottomItems.size() + 1)));
            look.addPart(bootsItems.get(rn.nextInt(bootsItems.size() + 1)));

            if (item == null) { // если обязательной вещи нет
                look.addPart(overTopItems.get(rn.nextInt(overTopItems.size() + 1)));
                look.addPart(topItems.get(rn.nextInt(topItems.size() + 1)));
                look.addPart(bottomItems.get(rn.nextInt(bottomItems.size() + 1)));
                look.addPart(bootsItems.get(rn.nextInt(bootsItems.size() + 1)));
            } else {
                String itemType = item.getType();
                if (Objects.equals(itemType, "Boots")){
                    look.addPart(overTopItems.get(rn.nextInt(overTopItems.size() + 1)));
                    look.addPart(topItems.get(rn.nextInt(topItems.size() + 1)));
                    look.addPart(bottomItems.get(rn.nextInt(bottomItems.size() + 1)));
                    look.addPart(item);
                } else if (top.contains(itemType)) {
                    look.addPart(overTopItems.get(rn.nextInt(overTopItems.size() + 1)));
                    look.addPart(item);
                    look.addPart(bottomItems.get(rn.nextInt(bottomItems.size() + 1)));
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size() + 1)));
                } else if (bottom.contains(itemType)) {
                    look.addPart(overTopItems.get(rn.nextInt(overTopItems.size() + 1)));
                    look.addPart(topItems.get(rn.nextInt(topItems.size() + 1)));
                    look.addPart(item);
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size() + 1)));
                } else if (overTop.contains(itemType)) {
                    look.addPart(item);
                    look.addPart(topItems.get(rn.nextInt(topItems.size() + 1)));
                    look.addPart(bottomItems.get(rn.nextInt(bottomItems.size() + 1)));
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size() + 1)));
                }
            }
        }

        if (option == 2){  //делаем платье + обувь
            List<Item> dressItems = itemRepo.findAllByTypeIn(Collections.singletonList("Dress"));
            List<Item> bootsItems = itemRepo.findAllByTypeIn(Collections.singletonList("Boots"));

            //убираем вещи с неподходящими сезонами и стилями
            removeStyles(bootsItems, styles);
            removeStyles(dressItems, styles);
            if (seasons != null) {
                removeSeasons(bootsItems, seasons);
                removeSeasons(dressItems, seasons);
            }
            //выбираем рандомные вещи и создаем лук
            if (item == null) {
                look.addPart(dressItems.get(rn.nextInt(dressItems.size() + 1)));
                look.addPart(bootsItems.get(rn.nextInt(bootsItems.size() + 1)));
            } else {
                String itemType = item.getType();
                if (Objects.equals(itemType, "Boots")) {
                    look.addPart(dressItems.get(rn.nextInt(dressItems.size() + 1)));
                    look.addPart(item);
                } else if (Objects.equals(itemType, "Dress")) {
                    look.addPart(item);
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size() + 1)));
                }
            }
        }
        return look;
    }

    //метод, создающий лук, когда обязательных штук нет
    public Look createLookNoParameters() {
        Random rn = new Random();
        int randomLook = rn.nextInt(3); // три варианта состава лука
        int randomStyle = rn.nextInt(5) + 1; //выбираем стиль
        Style style = styleRepo.findById(randomStyle).get(); //выбираем стиль

        Look look = createLook(randomLook, null, (List<Style>) style, null);
        lookRepo.save(look);
        return look;
    }

    //метод, создающий лук, когда есть какие-то обязательные параметры
    public Look createLookWithParameters(List<Season> seasons, List<Style> styles, UUID itemId) {
        Random rn = new Random();
        int randomLook = rn.nextInt(3); // три варианта состава лука
        int randomStyle = rn.nextInt(5) + 1; //выбираем стиль
        Style style = styleRepo.findById(randomStyle).get(); //выбираем стиль

        Look look = new Look();
        look.setUser(new User());

        if (seasons == null && styles == null && itemId != null) { //здесь только item обязательный
            Item item = itemRepo.findById(itemId).get();
            look = createLook(randomLook, null, item.getStyles(), item);
        } else
        if (seasons == null && itemId == null && styles != null) { //здесь только styles обязательный
            look = createLook(randomLook, null, styles, null);
        } else
        if (itemId == null && styles == null && seasons != null) { //здесь только seasons обязательный
            look = createLook(randomLook, seasons, (List<Style>) style, null);
        } else
        if (seasons == null && styles != null && itemId != null) { //здесь  item style обязательный
            Item item = itemRepo.findById(itemId).get();
            look = createLook(randomLook, null, styles, item);
        } else
        if (itemId == null && styles != null && seasons != null) { //здесь  seasons style обязательный
            look = createLook(randomLook, seasons, styles, null);
        } else
        if (styles == null && itemId != null && seasons != null) { //здесь  item seasons обязательный
            Item item = itemRepo.findById(itemId).get();
            look = createLook(randomLook, seasons, (List<Style>) style, item);
        } else
        if (seasons != null && styles != null && itemId != null) { //здесь все поля обязательные
            Item item = itemRepo.findById(itemId).get();
            look = createLook(randomLook, seasons, styles, item);
        }
        lookRepo.save(look);
        return look;

    }
}
