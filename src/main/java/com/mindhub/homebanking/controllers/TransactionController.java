package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRespository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;


    @Autowired
    TransactionService transactionService;

    @Transactional
    @PostMapping(path="/transactions")
    public ResponseEntity<Object> addTransaction(Authentication authentication, @RequestParam Double amount, @RequestParam String description,
                                                 @RequestParam String accountOrigin, @RequestParam String accountDestiny){


        Client clientAuthenticate =  clientService.getClientByEmail(authentication.getName());
        Account originAccount =  accountService.findByNumber(accountOrigin);
        Account destinyAccount = accountService.findByNumber(accountDestiny);





        if(amount <= 0 || amount.isNaN()){

            return new ResponseEntity<>("missing amount", HttpStatus.FORBIDDEN);

        }else if(description.isEmpty()){

            return new ResponseEntity<>("missing description", HttpStatus.FORBIDDEN);

        }else if(accountOrigin.isEmpty()){

            return new ResponseEntity<>("missing accountOrigin", HttpStatus.FORBIDDEN);

        }else if(accountDestiny.isEmpty()){

            return new ResponseEntity<>("missing accountDestiny", HttpStatus.FORBIDDEN);

        }else if(accountOrigin == accountDestiny){

            return new ResponseEntity<>("the account numbers are the same", HttpStatus.FORBIDDEN);

        }else if(originAccount == null){

            return new ResponseEntity<>("the account origin not exist", HttpStatus.FORBIDDEN);

        }else if(!clientAuthenticate.getAccounts().contains(originAccount)){

            return new ResponseEntity<>("The source account does not belong to the authenticated client", HttpStatus.FORBIDDEN);

        }else if(destinyAccount == null ){

            return new ResponseEntity<>("the account destiny not exist", HttpStatus.FORBIDDEN);

        }else if(accountService.findByNumber(accountOrigin).getBalance() < amount){

            return new ResponseEntity<>("the account origin not contain available amount", HttpStatus.FORBIDDEN);
            
        }


        Transaction transactionDebit = new Transaction(TransactionType.DEBIT, amount, description + " " +  accountDestiny, LocalDateTime.now(), originAccount);
        Transaction transactionCredit = new Transaction(TransactionType.CREDIT, amount, description + " " +  accountOrigin, LocalDateTime.now(),destinyAccount );

        transactionService.saveTransaction(transactionDebit);
        transactionService.saveTransaction(transactionCredit);

        originAccount.setBalance(originAccount.getBalance() - amount);
        destinyAccount.setBalance(destinyAccount.getBalance() + amount);



        return new ResponseEntity<>("the transaction has been successfully", HttpStatus.CREATED);
    }



}
