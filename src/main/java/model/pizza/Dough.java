package model.pizza;

import model.ModifyRamClasses;

public class Dough implements ModifyRamClasses {
    private Integer id_massa;
    private String tipusMassa;

    public Dough(Integer id_massa, String tipusMassa) {
        this.id_massa = id_massa;
        this.tipusMassa = tipusMassa;
    }

    public Integer getId_massa() {
        return id_massa;
    }

    public void setId_massa(Integer id_massa) {
        this.id_massa = id_massa;
    }

    public String getTipusMassa() {
        return tipusMassa;
    }

    public void setTipusMassa(String tipusMassa) {
        this.tipusMassa = tipusMassa;
    }

    @Override
    public void insertID(Integer id) {
        this.id_massa = id;
    }

    @Override
    public String getName() {
        return tipusMassa;
    }
}
