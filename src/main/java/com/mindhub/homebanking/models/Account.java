package com.mindhub.homebanking.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String number;
    private LocalDateTime creationDate;
    private double balance;

    @ManyToOne(fetch = FetchType.EAGER) //dice q tenga siempre listo la cuenta con los clientes asociados y sus transacciones
    // le dice a JPA que se asegure de que cuando una mascota se cargue desde la base de datos, los datos del propietario también se carguen
    @JoinColumn(name="client_id")
    private Client client;

    //fetch=  se utiliza para determinar cómo debe ser cargada la entidad
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER) //EAGER Indica que la relación debe de ser cargada al momento de cargar la entidad.
    private Set<Transaction> transactions = new HashSet<>();


    //constructor
    public Account() {

    }

    public Account(String number, LocalDateTime creationDate, double balance, Client client) {

        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;

    }



    //metodos


    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override //pasa a ser texto - retorna un objeto de string
    public String toString() {
        return "account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }

    public Client getClient(){
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }



}


