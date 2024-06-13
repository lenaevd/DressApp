package Dress.app.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "look")
public class Look {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "looks_part",
            joinColumns = @JoinColumn(name = "look_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> parts = new ArrayList<>();

    public Look() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getParts() {
        return parts;
    }

    public void setParts(List<Item> parts) {
        this.parts = parts;
    }

    public void addPart(Item part) {
        this.parts.add(part);

    }
}