package model.Orders;

import model.ModifyRamClasses;

public class ExtraIngredients implements ModifyRamClasses {
    private Integer id_order_item;
    //we only have the id in this database table in mysql
    private Integer id_ingredient;
    private Integer quantity;

    public ExtraIngredients(Integer id_order_item, Integer id_ingredient, Integer quantity) {
        this.id_order_item = id_order_item;
        this.id_ingredient = id_ingredient;
        this.quantity = quantity;
    }

    public Integer getId_order_item() {
        return id_order_item;
    }

    public void setId_order_item(Integer id_order_item) {
        this.id_order_item = id_order_item;
    }

    public Integer getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(Integer id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public void insertID(Integer id) {
        this.id_order_item = id_ingredient;
    }
}
