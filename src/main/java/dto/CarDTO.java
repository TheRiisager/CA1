/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Car;

/**
 *
 * @author Lasse Emil, Benjamin Choleva
 */
public class CarDTO {

    private int id;
    private int year;
    private String model;
    private String make;
    private String owner;
    private double price;

    public CarDTO() {
    }

    public CarDTO(Car car) {
        this.id = car.getId();
        this.year = car.getYear();
        this.make = car.getMake();
        this.model = car.getModel();
        this.owner = car.getOwner();
        this.price = car.getPrice();
    }

    public CarDTO(int year, String make, String model, String owner, double price) {
        this.year = year;
        this.model = model;
        this.make = make;
        this.owner = owner;
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

}
