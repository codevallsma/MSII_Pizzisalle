package model.Orders;

import model.ModifyRamClasses;

import java.util.ArrayList;
import java.util.List;

public class Order implements ModifyRamClasses {
    private Integer id_order;
    private List<OrderItem> orderItems;

    public Order(Integer id_order, List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.id_order = id_order;
    }

    public Order(Integer id_order) {
        this.id_order = id_order;
        orderItems = new ArrayList<>();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    @Override
    public void insertID(Integer id) {
        this.id_order = id;
    }

    @Override
    public String getName() {
        return Integer.toString(id_order);
    }
}
