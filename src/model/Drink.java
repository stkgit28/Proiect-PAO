package model;

public class Drink extends Product {
    private String size;
    private int volume;
    private String details;

    public Drink(long id, String drinkName, double drinkprice, String drinkRestName, String name, double price, String restName) {
        super(id, name, price, restName);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
