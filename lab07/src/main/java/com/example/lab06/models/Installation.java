package com.example.lab06.models;

import javax.persistence.*;

@Entity
@Table(name="Installation")
public class Installation {

    @Id
    @GeneratedValue
    @Column(name="installationId", nullable = false)
    private Long installationId;

    @Column(name="routerId", nullable = false)
    private int routerId;

    @Column(name="address")
    private String address;

    @Column(name="serviceType")
    private String serviceType;

    @ManyToOne
    @JoinColumn(name = "Client", referencedColumnName = "clientId")
    private Client clientId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId")
    private Payments payment;

    public Installation() {

    }

    public Installation(int routerId, String address, String serviceType) {
        this.routerId = routerId;
        this.address = address;
        this.serviceType = serviceType;
    }

    public void setRouterId(int routerId) {
        this.routerId = routerId;
    }

    public int getRouterId() {
        return routerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Client getClientId(){
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Long getInstallationId() {
        return installationId;
    }

    public void setInstallationId(Long installationId) {
        this.installationId = installationId;
    }

    @Override
    public String toString() {
        return "Installation{" +
                "installationId=" + installationId +
                ", routerId=" + routerId +
                ", address='" + address + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", clientId=" + clientId +
                ", payment=" + payment +
                '}';
    }
}
