package Dress.app.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Look")
public class Look {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User Owner;

    //и здесь хз тоже
    @ManyToMany(mappedBy = "looks", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> LooksPart = new ArrayList<>();

    public Look() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public User getOwner() {
        return Owner;
    }

    public void setOwner(User owner) {
        Owner = owner;
    }

    public List<Item> getLooksPart() {
        return LooksPart;
    }

    public void setLooksPart(List<Item> looksPart) {
        LooksPart = looksPart;
    }
}
