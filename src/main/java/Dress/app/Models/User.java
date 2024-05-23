package Dress.app.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name ="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String Name;


    @ManyToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<Item> UsersItems = new ArrayList<>();
    // вот хз как это сделать
//    @ManyToMany(mappedBy = "User", cascade = CascadeType.ALL)
//    private List<Item> UsersFavourites;

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<Look> UsersLook = new ArrayList<>();
}
