package Dress.app.Mappers;

import Dress.app.Models.Look;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class infoFromLook {
    private UUID id;
    private List<infoFromItems> items;

    public infoFromLook() {
    }

    public infoFromLook(UUID id, List<infoFromItems> items) {
        this.id = id;
        this.items = items;
    }

    public static List<infoFromLook> createInfo(List<Look> looks) {
        List<infoFromLook> list = new ArrayList<>();
        for(Look look: looks) {
            infoFromLook info = new infoFromLook(
                    look.getId(),
                    infoFromItems.createInfo(look.getParts())
            );
            list.add(info);
        }
        return list;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<infoFromItems> getItems() {
        return items;
    }

    public void setItems(List<infoFromItems> items) {
        this.items = items;
    }
}
