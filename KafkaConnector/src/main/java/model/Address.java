package model;

public class Address {
    private String street;
    private int number;
    private String city;
    private String state;

    public Address(String street, int number, String city, String state) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
    }

    public String getStreetName() {
        return street;
    }

    public void setStreetName(String streetName) {
        this.street = streetName;
    }

    public int getStreetNumber() {
        return number;
    }

    public void setStreetNumber(int streetNumber) {
        this.number = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String city) {
        this.state = state;
    }

}