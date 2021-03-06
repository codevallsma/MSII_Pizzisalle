package model;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.TableTypes;
import model.Delegation.Delegation;
import model.Delegation.DelegationFactory;
import model.Delegation.DelegationCentral;
import model.Orders.CustomerOrder;
import model.Orders.ExtraIngredients;
import model.Orders.Order;
import model.Orders.OrderItem;
import model.pizza.Dough;
import model.pizza.Drinks.Drinks;
import model.pizza.Ingredient;
import model.pizza.Pizza;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

/**
 * OBSERVER METHOD
 * Every time the data is updated in the database, it is also updated in the Model of the application
 * SINGLETON PATTERN
 * There can only be one model, therefore this class is also a singleton class
 */

public class Model implements PropertyChangeListener {
    private Customer currentCustomer;
    private CustomerOrder customerOrder;
    private List<Order> orders;
    //the list of pizzas available to buy
    private List<Pizza> allPizzas;
    //the list of all the available drinks
    private List<Drinks> drinks;
    //List of all dough
    private List<Dough> doughs;
    private List<Ingredient> ingredients;
    private int index_orders;
    //current delegation
    Delegation currentDelegation;
    private static Model instance = null;

    private Model() {
        currentCustomer = null;
        customerOrder = new CustomerOrder();
        orders = new ArrayList<>();
        allPizzas = new ArrayList<>();
        ingredients = new ArrayList<>();
        doughs = new ArrayList<>();
        index_orders = 0;
        // when initializing the model we have to randomly select a delegation
        Random random = new Random();
        // length is the upper bound of the random number selector
        int random_index = random.nextInt(DelegationCentral.values().length);
        //System.out.println("EL NUMERO ES "+random_index+" i el length es "+DelegationCentral.values().length);
        currentDelegation = DelegationFactory.getDelegation(random_index+1);
    }

    public static Model getInstance() {
        if(Model.instance == null) {
            return Model.instance = new Model();
        }
        return instance;
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
                //updating all the available pizzas
                setAllPizzas((List<Pizza>) evt.getNewValue());
                break;
            case DRINK:
                //updating all the drinks
                this.setDrinks((List<Drinks>) evt.getNewValue());
                break;
            case INGREDIENT:
                //setting all the available ingredients to the list of ingredients
                this.setIngredients((List<Ingredient>) evt.getNewValue());
                break;
            case DOUGH:
                this.setDoughs((List<Dough>) evt.getNewValue());
                break;
            case CUSTOMER_ORDER:
                this.setCustomerOrder((CustomerOrder)evt.getNewValue());
                break;
            case ORDER_ITEMS:
                //adding the inserted customer item to the current order
                OrderItem orderItem = (OrderItem) evt.getNewValue();
                Order currentOrder = findOrderById(orderItem.getId_order());
                currentOrder.getOrderItems().add(orderItem);
                break;
            case EXTRA_INGREDIENTS:
                ExtraIngredients extraIngredient = (ExtraIngredients) evt.getNewValue();
                ExtraIngredients found = findExtraIngredientByIds(extraIngredient.getId_order(), extraIngredient.getId_order_item(), extraIngredient.getId_ingredient());
                OrderItem orderItemExtra = findOrderItemById(extraIngredient.getId_order(), extraIngredient.getId_order_item());
                if(found != null){
                    //removing found element to replace it with the new one
                    orderItemExtra.getExtraIngredients().remove(extraIngredient);
                }
                //add the inserted extra ingredient to the order item
                orderItemExtra.getExtraIngredients().add(extraIngredient);
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
    public Order getLastOrder(){
        return orders.get(index_orders-1);
    }

    public void addOrder(Order order){
        this.orders.add(order);
        index_orders++;
    }
    public Order findOrderById(Integer index_order){
        Optional<Order> order =  this.orders.stream().filter(e-> e.getId_order().equals(index_order)).findFirst();
        return order.orElse(null);
    }
    public OrderItem findOrderItemById(Integer idOrder, Integer id_order_item){
        Order order = findOrderById(idOrder);
        return order.getOrderItems().stream().filter(e -> e.getId_order_item() == id_order_item).findFirst().orElse(null);
    }
    public ExtraIngredients findExtraIngredientByIds(Integer idOrder,  Integer id_order_item, Integer extra_ing_Id){
        Order order = findOrderById(idOrder);
        OrderItem orderItem = order.getOrderItems().stream().filter(e -> e.getId_order_item() == id_order_item).findFirst().orElse(null);
        if (orderItem != null){
            return  orderItem.getExtraIngredients().stream().filter(e -> e.getId_ingredient().equals(extra_ing_Id)).findFirst().orElse(null);
        }
        return null;
    }

    public List<Pizza> getAllPizzas() {
        return allPizzas;
    }

    public void setAllPizzas(List<Pizza> allPizzas) {
        this.allPizzas = allPizzas;
    }

    public List<Drinks> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drinks> drinks) {
        this.drinks = drinks;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Dough> getDoughs() {
        return doughs;
    }

    public void setDoughs(List<Dough> doughs) {
        this.doughs = doughs;
    }

    public Delegation getCurrentDelegation() {
        return currentDelegation;
    }

    public void setCurrentDelegation(Delegation currentDelegation) {
        this.currentDelegation = currentDelegation;
    }

    public void subscribeTables(){
        //subscribing to all tables
        for (TableTypes tt:
             TableTypes.values()) {
            MysqlConnector.getInstance().startObservingTable(tt, this);
        }
    }
    public void unsubscribeTableAlerts(){
        //unsubscri
        for (TableTypes tt:
                TableTypes.values()) {
            MysqlConnector.getInstance().stopObservingTable(tt, this);
        }
    }
}
