package model;

import java.util.ArrayList;

public class Customers {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    public Address address;
    ArrayList<String> products = new ArrayList<>();

    public Customers(String id, String firstName, String lastName, int age, Address address, ArrayList<String> products) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.products = products;
    }

    public String getId() { return id; }
    public void setId() { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public ArrayList<String> getProducts() { return products; }
    public void setProducts(ArrayList<String> products) { this.products = products; }

}