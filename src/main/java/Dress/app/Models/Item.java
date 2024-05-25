package Dress.app.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String link;
    private String fabric;
    private String color;

    //Каким видом коллекции указывать связанные таблицы?
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Style> ItemsStyles = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Season> ItemsSeasons = new ArrayList<>();

    @ManyToMany(mappedBy = "items", fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_items",
//            joinColumns = @JoinColumn(name = "item_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
    private List<User> users = new ArrayList<>();
//    @ManyToMany(mappedBy = "UsersItems", fetch = FetchType.EAGER)
//    private List<User> users = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "looks_part",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "look_id")
    )
    private List<Look> looks = new ArrayList<>();


    public Item() {
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

    public List<Style> getItemsStyles() {
        return ItemsStyles;
    }

    public void setItemsStyles(List<Style> itemsStyles) {
        ItemsStyles = itemsStyles;
    }

    public List<Season> getItemsSeasons() {
        return ItemsSeasons;
    }

    public void setItemsSeasons(List<Season> itemsSeasons) {
        ItemsSeasons = itemsSeasons;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Look> getLooks() {
        return looks;
    }

    public void setLooks(List<Look> looks) {
        this.looks = looks;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", fabric='" + fabric + '\'' +
                ", color='" + color + '\'' +
                ", ItemsStyles=" + ItemsStyles +
                ", ItemsSeasons=" + ItemsSeasons +
                ", users=" + users +
                ", looks=" + looks +
                '}';
    }
}
