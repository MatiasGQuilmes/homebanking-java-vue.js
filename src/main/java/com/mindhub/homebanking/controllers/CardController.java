package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    ClientService clientService;



    public int getRamdonNumber(int min, int max){

        return (int) ((Math.random()) * (max-min)) + min;

    }



   @PostMapping(path = "/clients/current/cards")
   public ResponseEntity<Object> addCard(Authentication authentication, @RequestParam CardType type, @RequestParam  CardColor color){

       Client clientAuthenticate= clientService.getClientByEmail(authentication.getName());

       Set<Card> types =clientAuthenticate.getCards().stream().filter(card -> card.getCardType()==type).collect(Collectors.toSet());


       String cardNumber = CardUtils.getCardNumber();

       int cvv = CardUtils.getCvv();

       if (types.size() >= 3){
          return new ResponseEntity<>("you can not have more than 3 cards", HttpStatus.FORBIDDEN);
       }else{
           Card card1 = new Card(color,type,getRamdonNumber(1000,10000) +" " + getRamdonNumber(1000,10000) +" "+getRamdonNumber(1000,10000) +" " + getRamdonNumber(1000,10000), getRamdonNumber(100,1000) , LocalDate.now(),LocalDate.now().plusYears(5), clientAuthenticate);
           cardService.saveCard(card1);
           return new ResponseEntity<>("your account has been created successfully", HttpStatus.CREATED);
       }

    }

    @DeleteMapping(path = "/clients/current/{id}")
    public ResponseEntity<Object> deleteCard(@PathVariable Long id){

        return new ResponseEntity<>("card removal successful", HttpStatus.ACCEPTED);
    }



}
