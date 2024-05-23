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
    @OneToMany(mappedBy = "Item", cascade = CascadeType.ALL)
    private List<Style> ItemsStyles = new ArrayList<>();

    @OneToMany(mappedBy = "Item", cascade = CascadeType.ALL)
    private List<Season> ItemsSeasons = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "UsersItems",
            joinColumns = @JoinColumn(name = "ItemId"),
            inverseJoinColumns = @JoinColumn(name = "UserId")
    )
    private List<User> users = new ArrayList<>();



}
