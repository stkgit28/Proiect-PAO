package model;

public class Order {
    private Product[] order = new Product[100];

    public Product[] getOrder() {
        return order;
    }

    public void setOrder(Product[] order) {
        this.order = order;
    }
}
