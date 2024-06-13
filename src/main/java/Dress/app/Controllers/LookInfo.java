package Dress.app.Controllers;

import Dress.app.Models.Look;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LookInfo {
    private UUID id;
    private List<ItemsInfo> items;

    public LookInfo() {
    }

    public LookInfo(UUID id, List<ItemsInfo> items) {
        this.id = id;
        this.items = items;
    }

    public static List<LookInfo> createInfo(List<Look> looks) {
        List<LookInfo> list = new ArrayList<>();
        for(Look look: looks) {
            LookInfo info = new LookInfo(
                    look.getId(),
                    ItemsInfo.createInfo(look.getParts())
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

    public List<ItemsInfo> getItems() {
        return items;
    }

    public void setItems(List<ItemsInfo> items) {
        this.items = items;
    }
}
