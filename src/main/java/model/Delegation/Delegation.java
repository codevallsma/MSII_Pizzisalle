package model.Delegation;

import model.ModifyRamClasses;

public class Delegation implements ModifyRamClasses {
    private Integer id;
    private String name;

    public Delegation(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void insertID(Integer id) {
        this.id = id;
    }
}
