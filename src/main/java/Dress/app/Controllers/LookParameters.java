package Dress.app.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LookParameters {
    private List<String> seasonsNames = new ArrayList<>();
    private List<String> stylesNames = new ArrayList<>();
    private UUID itemId;

    public LookParameters() {
    }

    @Override
    public String toString() {
        return "LookInfo{" +
                "seasonsNames=" + seasonsNames +
                ", stylesNames=" + stylesNames +
                ", itemId=" + itemId +
                '}';
    }

    public List<String> getSeasonsNames() {
        return seasonsNames;
    }

    public void setSeasonsNames(List<String> seasonsNames) {
        this.seasonsNames = seasonsNames;
    }

    public List<String> getStylesNames() {
        return stylesNames;
    }

    public void setStylesNames(List<String> stylesNames) {
        this.stylesNames = stylesNames;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }
}
