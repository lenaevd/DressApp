package Dress.app.Models;

import java.util.List;
import java.util.UUID;

public class Item {
    public UUID Id;
    public String Name;
    public String Link;
    public String Fabric;
    public String Color;

    //Каким видом коллекции указывать связанные таблицы?
    List<ItemsStyles> ItemsStyles;
    List<ItemsSeasons> ItemsSeasons;
}
