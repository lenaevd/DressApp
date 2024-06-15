package Dress.app.services;

import Dress.app.Models.*;
import Dress.app.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LookService {
    private final LookRepository lookRepo;
    private final ItemRepository itemRepo;
    private final StyleRepository styleRepo;
    private final UserRepository userRepo;
    private final SeasonRepository seasonRepo;


    @Autowired
    public LookService(LookRepository lookRepo, ItemRepository itemRepo,
                       StyleRepository styleRepo, UserRepository userRepo, SeasonRepository seasonRepo) {
        this.lookRepo = lookRepo;
        this.itemRepo = itemRepo;
        this.styleRepo = styleRepo;
        this.userRepo = userRepo;
        this.seasonRepo = seasonRepo;
    }

    public List<Look> getAll() {
        return lookRepo.findAll();
    }

    public List<Look> getUsersLooks(UUID id) {
        User user = userRepo.findById(id).get();
        return lookRepo.findByUser(user);
    }

    public void removeSeasons(List<Item> items, List<Season> seasons) {
        items.removeIf(item -> !item.getSeasons().stream().anyMatch(seasons::contains));
    }

    public void removeStyles(List<Item> items, List<Style> styles) {
        items.removeIf(item -> !item.getStyles().stream().anyMatch(styles::contains));
    }

    public <T> List<T> createListFromObject(T object) {
        List<T> list = new ArrayList<>();
        list.add(object);
        return list;
    }

    //передаём какой вариант делать, список обязательных сезонов стилей и обязательная вещь если есть
    public Look createLook(UUID userId, int option, List<Season> seasons, List<Style> styles, Item item){
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
        look.setUser(userRepo.findById(userId).get());
        Random rn = new Random();
        if (option == 0) { //делаем просто верх + низ + обувь
            List<Item> topItems = itemRepo.findAllByTypeIn(top);
            List<Item> bottomItems = itemRepo.findAllByTypeIn(bottom);
            List<Item> bootsItems = itemRepo.findAllByTypeIn(Collections.singletonList("Boots"));
            //убираем вещи с неподходящими сезонами и стилями
            removeStyles(bootsItems, styles);
            removeStyles(topItems, styles);
            removeStyles(bottomItems, styles);

//            removeSeasons(bootsItems, seasons);
//            removeSeasons(bottomItems, seasons);
//            removeSeasons(topItems, seasons);

            if (seasons != null) {
                removeSeasons(bootsItems, seasons);
                removeSeasons(bottomItems, seasons);
                removeSeasons(topItems, seasons);
            }

            //выбираем рандомные вещи и создаем лук
            if (item == null) {
                look.addPart(topItems.get(rn.nextInt(topItems.size())));
                look.addPart(bottomItems.get(rn.nextInt(bottomItems.size())));
                look.addPart(bootsItems.get(rn.nextInt(bootsItems.size())));
            } else {
                String itemType = item.getType();
                if (Objects.equals(itemType, "Boots")){
                    look.addPart(topItems.get(rn.nextInt(topItems.size())));
                    look.addPart(bottomItems.get(rn.nextInt(bottomItems.size())));
                    look.addPart(item);
                } else if (top.contains(itemType)) {
                    look.addPart(item);
                    look.addPart(bottomItems.get(rn.nextInt(bottomItems.size())));
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size())));
                } else if (bottom.contains(itemType)) {
                    look.addPart(topItems.get(rn.nextInt(topItems.size())));
                    look.addPart(item);
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size())));
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

//            removeSeasons(bootsItems, seasons);
//            removeSeasons(bottomItems, seasons);
//            removeSeasons(topItems, seasons);
//            removeSeasons(overTopItems, seasons);

            if (seasons != null) {
                removeSeasons(bootsItems, seasons);
                removeSeasons(bottomItems, seasons);
                removeSeasons(topItems, seasons);
                removeSeasons(overTopItems, seasons);
            }

            if (item == null) { // если обязательной вещи нет
                look.addPart(overTopItems.get(rn.nextInt(overTopItems.size())));
                look.addPart(topItems.get(rn.nextInt(topItems.size())));
                look.addPart(bottomItems.get(rn.nextInt(bottomItems.size())));
                look.addPart(bootsItems.get(rn.nextInt(bootsItems.size())));
            } else {
                String itemType = item.getType();
                if (Objects.equals(itemType, "Boots")){
                    look.addPart(overTopItems.get(rn.nextInt(overTopItems.size())));
                    look.addPart(topItems.get(rn.nextInt(topItems.size())));
                    look.addPart(bottomItems.get(rn.nextInt(bottomItems.size())));
                    look.addPart(item);
                } else if (top.contains(itemType)) {
                    look.addPart(overTopItems.get(rn.nextInt(overTopItems.size())));
                    look.addPart(item);
                    look.addPart(bottomItems.get(rn.nextInt(bottomItems.size())));
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size())));
                } else if (bottom.contains(itemType)) {
                    look.addPart(overTopItems.get(rn.nextInt(overTopItems.size())));
                    look.addPart(topItems.get(rn.nextInt(topItems.size())));
                    look.addPart(item);
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size())));
                } else if (overTop.contains(itemType)) {
                    look.addPart(item);
                    look.addPart(topItems.get(rn.nextInt(topItems.size())));
                    look.addPart(bottomItems.get(rn.nextInt(bottomItems.size())));
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size())));
                }
            }
        }

        if (option == 2){  //делаем платье + обувь
            List<Item> dressItems = itemRepo.findAllByTypeIn(Collections.singletonList("Dress"));
            List<Item> bootsItems = itemRepo.findAllByTypeIn(Collections.singletonList("Boots"));

            //убираем вещи с неподходящими сезонами и стилями
            removeStyles(bootsItems, styles);
            removeStyles(dressItems, styles);

//            removeSeasons(bootsItems, seasons);
//            removeSeasons(dressItems, seasons);

            if (seasons != null) {
                removeSeasons(bootsItems, seasons);
                removeSeasons(dressItems, seasons);
            }

            //выбираем рандомные вещи и создаем лук
            if (item == null) {
                look.addPart(dressItems.get(rn.nextInt(dressItems.size())));
                look.addPart(bootsItems.get(rn.nextInt(bootsItems.size())));
            } else {
                String itemType = item.getType();
                if (Objects.equals(itemType, "Boots")) {
                    look.addPart(dressItems.get(rn.nextInt(dressItems.size())));
                    look.addPart(item);
                } else if (Objects.equals(itemType, "Dress")) {
                    look.addPart(item);
                    look.addPart(bootsItems.get(rn.nextInt(bootsItems.size())));
                }
            }
        }
        return look;
    }

    //метод, создающий лук, когда обязательных штук нет
    public Look createLookNoParameters(UUID userId) {
        Random rn = new Random();
        int randomLook = rn.nextInt(3); // три варианта состава лука
        int randomStyle = rn.nextInt(5) + 1; //выбираем стиль
        Style style = styleRepo.findById(randomStyle).get();

        //int randomSeason = rn.nextInt(5) + 1; //выбираем сезон
        //Season season = seasonRepo.findById(randomSeason).get();
        //Look look = createLook(randomLook, createListFromObject(season), createListFromObject(style), null);
        Look look = createLook(userId, randomLook, null, createListFromObject(style), null);
        lookRepo.save(look);
        return look;
    }

    //метод, создающий лук, когда есть какие-то обязательные параметры
    // здесь стринг айтем айди это ссылка на картинку, всё обман....
    public Look createLookWithParameters(UUID userId, List<Season> seasons, List<Style> styles, String itemId) {
        Random rn = new Random();
        int randomLook = rn.nextInt(3); // три варианта состава лука
        int randomStyle = rn.nextInt(5) + 1; //выбираем стиль
        Style style = styleRepo.findById(randomStyle).get();

//        int randomSeason = rn.nextInt(5) + 1; //выбираем сезон
//        Season season = seasonRepo.findById(randomSeason).get();

        Look look = new Look();
        look.setUser(userRepo.findById(userId).get());

        if (seasons.isEmpty() && styles.isEmpty() && itemId != null) { //здесь только item обязательный
            Item item = itemRepo.findByLink(itemId);
            //look = createLook(randomLook, createListFromObject(season), item.getStyles(), item);
            look = createLook(userId, randomLook, null, item.getStyles(), item);
        } else
        if (seasons.isEmpty() && itemId == null && !styles.isEmpty()) { //здесь только styles обязательный
//            look = createLook(randomLook, createListFromObject(season), styles, null);
            look = createLook(userId, randomLook, null, styles, null);
        } else
        if (itemId == null && styles.isEmpty() && !seasons.isEmpty()) { //здесь только seasons обязательный
            look = createLook(userId, randomLook, seasons, createListFromObject(style), null);
        } else
        if (seasons.isEmpty() && !styles.isEmpty() && itemId != null) { //здесь  item style обязательный
            Item item = itemRepo.findByLink(itemId);
            //look = createLook(randomLook, createListFromObject(season), styles, item);
            look = createLook(userId, randomLook, null, styles, item);
        } else
        if (itemId == null && !styles.isEmpty() && !seasons.isEmpty()) { //здесь  seasons style обязательный
            look = createLook(userId, randomLook, seasons, styles, null);
        } else
        if (styles.isEmpty() && itemId != null && !seasons.isEmpty()) { //здесь  item seasons обязательный
            Item item = itemRepo.findByLink(itemId);
            look = createLook(userId, randomLook, seasons, createListFromObject(style), item);
        } else
        if (!seasons.isEmpty() && !styles.isEmpty() && itemId != null) { //здесь все поля обязательные
            Item item = itemRepo.findByLink(itemId);
            look = createLook(userId, randomLook, seasons, styles, item);
        }
        lookRepo.save(look);
        return look;

    }
}
