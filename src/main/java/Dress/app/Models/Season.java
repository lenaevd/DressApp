package Dress.app.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Season")
public class Season {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String Name;
    @ManyToOne
    private Item Item;
}
