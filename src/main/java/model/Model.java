package model;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.TableTypes;
import model.Delegation.Delegation;
import model.Delegation.DelegationBuilder;
import model.Delegation.DelegationCentral;
import model.Orders.CustomerOrder;
import model.Orders.Order;
import model.pizza.Pizza;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.*;

/**
 * OBSERVER METHOD
 * Every time the data is updated in the database, it is also updated in the Model of the application
 */

public class Model implements PropertyChangeListener {
    private Customer currentCustomer;
    private CustomerOrder customerOrder;
    private List<Order> orders;
    //the list of pizzas available to buy
    private List<Pizza> allPizzas;
    //current delegation
    Delegation currentDelegation;


    private static final class InstanceHolder {
        private static final Model instance = new Model();
    }

    public Model() {
        currentCustomer = null;
        customerOrder = new CustomerOrder();
        orders = new ArrayList<>();
        allPizzas = new ArrayList<>();
        // when initializing the model we have to randomly select a delegation
        Random random = new Random();
        // length is the upper bound of the random number selector
        int random_index = random.nextInt(DelegationCentral.values().length-1);
        currentDelegation = DelegationBuilder.buildDelegation(random_index);
    }

    public static Model getInstance() {
        return Model.InstanceHolder.instance;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Optional<TableTypes> tableType =
                Arrays.asList(TableTypes.values()).stream().filter(element -> element.toString().compareTo(evt.getPropertyName())==0).findAny();
        switch (tableType.get()) {
            case CUSTOMER:
                //updating the new value to the one recently received from the database
                setCurrentCustomer((Customer)evt.getNewValue());
                break;
            case PIZZA:
                setAllPizzas((List<Pizza>) evt.getNewValue());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + evt.getPropertyName());
        }
    }

    public Customer getCurrentCustomer() {
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

    public List<Pizza> getAllPizzas() {
        return allPizzas;
    }

    public void setAllPizzas(List<Pizza> allPizzas) {
        this.allPizzas = allPizzas;
    }

    public Delegation getCurrentDelegation() {
        return currentDelegation;
    }

    public void subscribeTables(){
        //subscribing to all tables
        for (TableTypes tt:
             TableTypes.values()) {
            MysqlConnector.getInstance().startObservingTable(tt, this);
        }
    }
    public void unsubscribeTableAlerts(){
        MysqlConnector.getInstance().stopObservingTable(TableTypes.CUSTOMER, this);
    }
}
