package Dress.app.Responses;

public class getAllItemsResponse {
    private String name;
    private String link;
    private String color;

    public getAllItemsResponse(String name, String link, String color) {
        this.name = name;
        this.link = link;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
