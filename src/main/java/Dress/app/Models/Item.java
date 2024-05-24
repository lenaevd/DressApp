package Dress.app.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String Name;
    private String Link;
    private String Fabric;
    private String Color;

    //Каким видом коллекции указывать связанные таблицы?
    @OneToMany(mappedBy = "Item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Style> ItemsStyles = new ArrayList<>();

    @OneToMany(mappedBy = "Item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Season> ItemsSeasons = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UsersItems",
            joinColumns = @JoinColumn(name = "ItemId"),
            inverseJoinColumns = @JoinColumn(name = "UserId")
    )
    private List<User> users = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "LooksPart",
            joinColumns = @JoinColumn(name = "ItemId"),
            inverseJoinColumns = @JoinColumn(name = "LookId")
    )
    private List<Look> looks = new ArrayList<>();


    public Item() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getFabric() {
        return Fabric;
    }

    public void setFabric(String fabric) {
        Fabric = fabric;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
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
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Link='" + Link + '\'' +
                ", Fabric='" + Fabric + '\'' +
                ", Color='" + Color + '\'' +
                ", ItemsStyles=" + ItemsStyles +
                ", ItemsSeasons=" + ItemsSeasons +
                ", users=" + users +
                ", looks=" + looks +
                '}';
    }
}
