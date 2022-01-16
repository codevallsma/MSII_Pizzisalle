package model.Orders;

import model.ModifyRamClasses;

public class ExtraIngredients implements ModifyRamClasses {
    //we only have the id in this database table in mysql
    private Integer id_ingredient;
    private Integer quantity;

    public ExtraIngredients(Integer id_ingredient, Integer quantity) {
        this.id_ingredient = id_ingredient;
        this.quantity = quantity;
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
        this.id_ingredient = id_ingredient;
    }

    @Override
    public String getName() {
        return "NONE";
    }
}
