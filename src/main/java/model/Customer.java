package model;

public class Customer implements ModifyRamClasses{
    private Integer customerId;
    private String name;
    private String surname1;
    private String surname2;
    private String phone_number;
    private String address;
    private String city;

    public Customer(String name, String surname1, String surname2, String phone_number, String address, String city) {
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.phone_number = phone_number;
        this.address = address;
        this.city = city;
    }

    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void insertID(Integer id) {
        this.customerId = id;
    }
}
