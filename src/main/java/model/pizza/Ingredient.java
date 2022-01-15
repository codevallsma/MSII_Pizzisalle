package model.pizza;

import model.ModifyRamClasses;

public class Ingredient implements ModifyRamClasses {
    private Integer id_ingredient;
    private String ingredient;

    public Ingredient(Integer id_ingredient, String ingredient) {
        this.id_ingredient = id_ingredient;
        this.ingredient = ingredient;
    }

    public Ingredient() {
    }

    public Integer getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(Integer id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public void insertID(Integer id) {
        this.id_ingredient = id;
    }

    @Override
    public String getName() {
       return ingredient;
    }
}
