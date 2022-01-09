package model.Orders;

import model.ModifyRamClasses;

import java.util.List;

public class Order implements ModifyRamClasses {
    private Integer id_order;
    private List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public void insertID(Integer id) {
        this.id_order = id;
    }
}
