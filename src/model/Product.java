package model;

public abstract class Product implements Comparable<Product> {
    protected long id;
    protected String name;
    protected double price;
    protected String restName;

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(long id, String name, double price, String restName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restName = restName;
    }
    @Override
    public String toString(){
        return name  + "/" + price + "/" + restName;
    }

    @Override
    public int compareTo(Product product){
        if(this.price > product.price){
            return 1;
        } else if(this.price == product.price){
            return 0;
        } else {
            return -1;
        }
    }
}
