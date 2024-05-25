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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //и здесь хз тоже
    @ManyToMany(mappedBy = "looks", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> LooksPart = new ArrayList<>();

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

    public List<Item> getLooksPart() {
        return LooksPart;
    }

    public void setLooksPart(List<Item> looksPart) {
        LooksPart = looksPart;
    }
}