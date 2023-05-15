package com.example.lab06.models;



import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Client")
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "clientId", nullable = false)
    private Long clientId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToMany(mappedBy= "clientId")
    private Set<Installation> installations;

    public Client() {

    }

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clienNumber) {
        this.clientId = clienNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clienNumber=" + clientId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
