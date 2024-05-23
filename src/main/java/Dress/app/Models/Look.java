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
    private User User;

    //и здесь хз тоже
    @ManyToMany(mappedBy = "Look", cascade = CascadeType.ALL)
    private List<Item> LooksPart = new ArrayList<>();
}
