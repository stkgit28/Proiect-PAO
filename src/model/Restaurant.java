package model;

public class Restaurant {
    private long restaurantId;
    private String restaurantName;
    private Product[] products = new Product[100];

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Restaurant(long restaurantId, String restaurantName, Product[] products) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.products = products;
    }

    @Override
    public String toString(){
        return restaurantName;
    }
}
