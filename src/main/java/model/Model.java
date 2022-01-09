package model;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.TableTypes;
import model.Orders.CustomerOrder;
import model.Orders.Order;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static database.Connectors.enums.TableTypes.*;

public class Model implements PropertyChangeListener {
    private Customer currentCustomer;
    private CustomerOrder customerOrder;
    private List<Order> orders;

    private static final class InstanceHolder {
        private static final Model instance = new Model();
    }

    public Model() {
        currentCustomer = null;
        customerOrder = new CustomerOrder();
        orders = new ArrayList<>();

    }

    public static Model getInstance() {
        return Model.InstanceHolder.instance;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Optional<TableTypes> tableType = Arrays.stream(TableTypes.values()).findFirst().filter(element -> element.toString().equals(evt.getPropertyName()));
        switch (tableType.get()) {
            case CUSTOMER:
                setCurrentCustomer((Customer)evt.getNewValue());
                System.out.println("NOTIFIED BITCH");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + evt.getPropertyName());
        }
    }

    public Customer getCurrentCustomer() {
        if(currentCustomer == null)
            return currentCustomer = new Customer();
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void subscribeTables(){
        MysqlConnector.getInstance().startObservingTable(TableTypes.CUSTOMER, this);
    }
    public void unsubscribeTableAlerts(){
        MysqlConnector.getInstance().stopObservingTable(TableTypes.CUSTOMER, this);
    }
}
