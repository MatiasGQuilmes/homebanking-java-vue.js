package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.DTOS.ClientDTO;
import com.mindhub.homebanking.DTOS.LoanApplicationDTO;
import com.mindhub.homebanking.DTOS.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    ClientService clientService;




    @Autowired
    AccountService accountService;


    @Autowired
    TransactionService transactionService;

    @Autowired
    ClientLoanService clientLoanService;

    @GetMapping(value = "/loans")
    public List<LoanDTO> getLoans(){

        return loanService.getLoansDTO();

    }


    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> createLoan(Authentication authentication, @RequestBody LoanApplicationDTO loan){

        Client clientAuthenticate = clientService.getClientByEmail(authentication.getName());
        Loan loan1 = loanService.getLoan(loan.getId());
        Account account1 = accountService.findByNumber(loan.getDestinationAccount());

        Set<ClientLoan> clientLoan2 = clientAuthenticate.getLoans().stream().filter(clientLoan1 -> clientLoan1.getLoan().getName().equals(loan1.getName())).collect(Collectors.toSet());

        if(loan.getId() <= 0){
            return new ResponseEntity<>("the id does not exist", HttpStatus.FORBIDDEN);
        }
        if(loan.getAmount() <= 0 || loan.getAmount().isNaN()){
            return new ResponseEntity<>("the amount entered is not valid", HttpStatus.FORBIDDEN);
        }
        if(loan.getPayments() <= 0 ){
            return new ResponseEntity<>("the payments entered is not valid", HttpStatus.FORBIDDEN);
        }
        if(loan.getDestinationAccount() == " " || loan.getDestinationAccount().isEmpty()){
            return new ResponseEntity<>("the account destiny entered is not valid", HttpStatus.FORBIDDEN);
        }
        if(loan1 == null){
            return new ResponseEntity<>("the loan entered is not valid", HttpStatus.FORBIDDEN);
        }
        if(loan1.getMaxAmount()<= loan.getAmount()){
            return new ResponseEntity<>("The loan amount requested exceeds the maximum loan amount available.", HttpStatus.FORBIDDEN);
        }
        if(!loan1.getPayments().contains(loan.getPayments())){
            return new ResponseEntity<>("the requested quota is not available", HttpStatus.FORBIDDEN);
        }
        if(accountService.findByNumber(loan.getDestinationAccount())==null){
            return new ResponseEntity<>("The target account does not exist.", HttpStatus.FORBIDDEN);
        }
        if(!clientAuthenticate.getAccounts().contains(accountService.findByNumber(loan.getDestinationAccount()))){
            return new ResponseEntity<>("The destination account does not belong to the customer", HttpStatus.FORBIDDEN);
        }
        if (clientLoan2.size() >0 ){

           return new ResponseEntity<>("You already have a loan of this type.", HttpStatus.FORBIDDEN);
       }

        if(loan1.getName().equals("mortgage")){
            ClientLoan clientLoan = new ClientLoan( loan.getPayments(), loan.getAmount()*1.4,clientService.getClientByEmail(authentication.getName()),loan1);
            clientLoanService.saveClientLoan(clientLoan);
        }
        if(loan1.getName().equals("personal")){
            ClientLoan clientLoan = new ClientLoan( loan.getPayments(), loan.getAmount()*1.2,clientService.getClientByEmail(authentication.getName()),loan1);
            clientLoanService.saveClientLoan(clientLoan);
        }
        if(loan1.getName().equals("automotive")){
            ClientLoan clientLoan = new ClientLoan( loan.getPayments(), loan.getAmount()*1.3,clientService.getClientByEmail(authentication.getName()),loan1);
            clientLoanService.saveClientLoan(clientLoan);
        }


        accountService.findByNumber(loan.getDestinationAccount()).setBalance(account1.getBalance() + loan.getAmount());


        Transaction transaction = new Transaction(TransactionType.CREDIT, loan.getAmount(), loan1.getName() + " " + "loan approved",LocalDateTime.now(), accountService.findByNumber(loan.getDestinationAccount()));
        transactionService.saveTransaction(transaction);


        return new ResponseEntity<>("the loan has been successfully", HttpStatus.CREATED);
    }


}
