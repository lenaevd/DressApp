package Dress.app.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Style")
public class Style {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String Name;
    @ManyToOne
    private Item Item;
}
