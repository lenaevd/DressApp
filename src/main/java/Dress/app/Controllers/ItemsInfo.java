package Dress.app.Controllers;

import Dress.app.Models.Item;
import Dress.app.Models.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemsInfo {
    private UUID id;
    private String name;
    private String link;
    private String fabric;
    private String color;
    private String type;
    private List<String> styles;

    public ItemsInfo() {
    }

    public ItemsInfo(UUID id, String name, String link, String fabric, String color, String type, List<String> styles) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.fabric = fabric;
        this.color = color;
        this.type = type;
        this.styles = styles;
    }

    public static List<ItemsInfo> createInfo(List<Item> items) {
        List<ItemsInfo> list = new ArrayList<>();
        for(Item item: items) {
            ItemsInfo info = new ItemsInfo(
                    item.getId(),
                    item.getName(),
                    item.getLink(),
                    item.getFabric(),
                    item.getColor(),
                    item.getType(),
                    createStylesInfo(item.getStyles())
            );
            list.add(info);
        }
        return list;
    }

    public static List<String> createStylesInfo(List<Style> styles) {
        List<String> list = new ArrayList<>();
        for(Style style: styles) {
            list.add(style.getName());
        }
        return list;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getStyles() {
        return styles;
    }

    public void setStyles(List<String> styles) {
        this.styles = styles;
    }
}
