package com.example.lab06.models;

import javax.persistence.*;

@Entity
@Table(name="Pricing")
public class Pricing {

    @Id
    @Column(name="serviceId", nullable = false)
    private int serviceId;

    @Column(name="price")
    private double price;

    public Pricing() {

    }

    public Pricing(int serviceId, double price) {
        this.serviceId = serviceId;
        this.price = price;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "serviceId=" + serviceId +
                ", price=" + price +
                '}';
    }
}


