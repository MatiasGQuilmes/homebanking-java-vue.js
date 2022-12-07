package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.DTOS.AccountDTO;
import com.mindhub.homebanking.DTOS.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRespository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ClientService clientService;


    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts(){

        return accountService.getAccounts();

    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){

        return accountService.getAccount(id);

    }




    public int getRamdonNumber(int min, int max){

        return (int) ((Math.random()) * (max-min)) + min;

    }

    @PostMapping(path = "/clients/current/accounts")
    public ResponseEntity<Object> addAccount(Authentication authentication){

        Client clientAuthenticate = clientService.getClientByEmail(authentication.getName());


        if(clientAuthenticate.getAccounts().size() >=3 ){
            return new ResponseEntity<>("you can not have more than 3 accounts", HttpStatus.FORBIDDEN);
        }else{
            Account account = new Account("VIN" + getRamdonNumber(10000000, 100000000), LocalDateTime.now(), 00.00, clientAuthenticate);
            accountService.saveAccount(account);
            return new ResponseEntity<>("your account has been created successfully", HttpStatus.CREATED);
        }

    }


}
