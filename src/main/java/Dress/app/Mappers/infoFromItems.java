package Dress.app.Mappers;

import Dress.app.Models.Item;
import Dress.app.Models.Season;
import Dress.app.Models.Style;
import Dress.app.Responses.getAllItemsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class infoFromItems {
    private UUID id;
    private String name;
    private String link;
    private String fabric;
    private String color;
    private String type;
    private List<String> styles;
    private List<String> seasons;

    public infoFromItems() {
    }

    public infoFromItems(UUID id, String name, String link, String fabric,
                         String color, String type, List<String> styles, List<String> seasons) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.fabric = fabric;
        this.color = color;
        this.type = type;
        this.styles = styles;
        this.seasons = seasons;
    }

    public static List<getAllItemsResponse> createInfoNameLinkColor(List<Item> items) {
        List<getAllItemsResponse> list = new ArrayList<>();
        for (Item item : items) {
            getAllItemsResponse info = new getAllItemsResponse(
                    item.getName(),
                    item.getLink(),
                    item.getColor()
            );
            list.add(info);
        }
        return list;
    }

    public static List<infoFromItems> createInfo(List<Item> items) {
        List<infoFromItems> list = new ArrayList<>();
        for (Item item : items) {
            infoFromItems info = new infoFromItems(
                    item.getId(),
                    item.getName(),
                    item.getLink(),
                    item.getFabric(),
                    item.getColor(),
                    item.getType(),
                    createStylesInfo(item.getStyles()),
                    createSeasonsInfo(item.getSeasons())
            );
            list.add(info);
        }
        return list;
    }

    public static infoFromItems createInfoAboutItem(Item item) {
        return new infoFromItems(
                item.getId(),
                item.getName(),
                item.getLink(),
                item.getFabric(),
                item.getColor(),
                item.getType(),
                createStylesInfo(item.getStyles()),
                createSeasonsInfo(item.getSeasons())
        );
    }


    public static List<String> createStylesInfo(List<Style> styles) {
        List<String> list = new ArrayList<>();
        for (Style style : styles) {
            list.add(style.getName());
        }
        return list;
    }

    public static List<String> createSeasonsInfo(List<Season> seasons) {
        List<String> list = new ArrayList<>();
        for (Season season : seasons) {
            list.add(season.getName());
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

    public List<String> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<String> seasons) {
        this.seasons = seasons;
    }
}
