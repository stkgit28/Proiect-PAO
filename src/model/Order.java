package model;

import java.util.Set;
import java.util.TreeSet;

public class Order {
    private Set<Product> order = new TreeSet<>();

    public Set<Product> getOrder() {
        return order;
    }

    public void setOrder(Set<Product> order) {
        this.order = order;
    }
}
