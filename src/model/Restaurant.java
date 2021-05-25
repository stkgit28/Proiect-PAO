package model;

import java.util.*;

public class Restaurant {
    private long restaurantId;
    private String restaurantName;
    private Set<Product> products = new TreeSet<>();

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Restaurant(long restaurantId, String restaurantName, Set<Product> products) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.products = products;
    }

    @Override
    public String toString(){
        return restaurantName;
    }
}
