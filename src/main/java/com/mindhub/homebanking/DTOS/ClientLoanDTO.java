package com.mindhub.homebanking.DTOS;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Set;

public class ClientLoanDTO {

    private long id;

    private int payments;
    private String name;
    private double amount;

    private long loan_id;



    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.payments = clientLoan.getPayments();
        this.name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.loan_id = clientLoan.getLoan().getId();
    }




    public long getId() {
        return id;
    }

    public int getPayments() {
        return payments;
    }


    public String getName() {
        return name;
    }


    public double getAmount() {
        return amount;
    }


    public long getLoan_id() {
        return loan_id;
    }


}
