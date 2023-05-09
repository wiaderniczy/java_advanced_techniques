package com.example.lab06.models;

import jakarta.persistence.*;

@Entity
@Table(name="Payments")
public class Payments {

    @Id
    @GeneratedValue
    @Column(name="paymentId", nullable=false)
    private Long paymentId;

    @Column(name="date")
    private String date;

    @Column(name="amount")
    private double amount;

    @Column(name="isOutstanding")
    private boolean isOutstanding;

    @OneToOne(mappedBy = "payment")
    private Installation installation;

    public Payments() {

    }

    public Payments(String date, double amount, boolean isOutstanding) {
        this.date = date;
        this.amount = amount;
        this.isOutstanding = isOutstanding;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isOutstanding() {
        return isOutstanding;
    }

    public void setOutstanding(boolean outstanding) {
        isOutstanding = outstanding;
    }

    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "paymentId=" + paymentId +
                ", date=" + date +
                ", amount=" + amount +
                ", isOutstanding=" + isOutstanding +
                ", installation=" + installation +
                '}';
    }
}
