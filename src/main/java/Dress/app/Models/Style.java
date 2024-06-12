package Dress.app.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "style")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "styles", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @ManyToMany(mappedBy = "styles", cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    }, fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

    public Style() {
    }

    @Override
    public String toString() {
        return "Style{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
