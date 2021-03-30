package model;

public class Food extends Product{
    private String details;
    private String size;
    private int weight;
    private boolean spicy;


    public Food(long id, String name, double price, String restName, String details, String size, int weight, boolean spicy) {
        super(id, name, price, restName);
        this.details = details;
        this.size = size;
        this.weight = weight;
        this.spicy = spicy;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isSpicy() {
        return spicy;
    }

    public void setSpicy(boolean spicy) {
        this.spicy = spicy;
    }

    public String toString() {
        return super.toString() + "/" + details +"/" +size + "/" + weight +"/" + spicy;
    }
    }

