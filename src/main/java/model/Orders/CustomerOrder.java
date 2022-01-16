package model.Orders;

import model.Delegation.Delegation;
import model.ModifyRamClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerOrder implements ModifyRamClasses {
    private Integer idOrder;
    private Integer idCustomer;
    //we only have the id in the database table
    private Delegation delegation;
    private Date date;

    //constructor when retrieving orders from database
    public CustomerOrder(int idOrder, int idCustomer, Delegation delegation, Date date) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.delegation = delegation;
        this.date = date;
    }
    //constructor when inserting order to the database
    public CustomerOrder(int idCustomer, Delegation delegation, Date date) {
        this.idCustomer = idCustomer;
        this.delegation = delegation;
        this.date = date;
    }
    public CustomerOrder(int idCustomer, Delegation delegation) {
        this.idCustomer = idCustomer;
        this.delegation = delegation;
        // default is current date
        this.date = new Date();
    }

    public CustomerOrder() {
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void insertID(Integer id) {
        this.idOrder = id ;
    }

    @Override
    public String getName() {
        return Integer.toString(idOrder);
    }
}
