package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.DTOS.CardDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController //dice q esto va a ser un controlador, el rest dice q va a estar restringido bajo las restricciones de rest(get, put, delete, post, patch)
@RequestMapping("/api")
public class ClientController {


    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;



    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping(value = "/clients") //asocia esta ruta /clients una peticion, y cada vez q pegue a este edpoint se ejecuta el metodo de abajo
    public List<ClientDTO> getClients(){

        return clientService.getClientsDTO();

    }

    //son servlets
    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){

       return clientService.getClientDTO(id);

    }

    public int getRamdonNumber(int min, int max){

        return (int) ((Math.random()) * (max-min)) + min;

    }

    //o @PostMapping(path = "/clients")
    //@RequestMapping(path = "/clients", method = RequestMethod.POST)

    @PostMapping(path = "/clients")
    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password ) {



        if (firstName.isEmpty() ) {

            return new ResponseEntity<>("the first name is missing", HttpStatus.FORBIDDEN);

        }
        if (lastName.isEmpty() ) {

            return new ResponseEntity<>("the last name is missing", HttpStatus.FORBIDDEN);

        }
        if (email.isEmpty() ) {

            return new ResponseEntity<>("the email is missing", HttpStatus.FORBIDDEN);

        }
        if (password.isEmpty() ) {

            return new ResponseEntity<>("the password is missing", HttpStatus.FORBIDDEN);

        }






        if (clientService.getClientByEmail(email) !=  null) {

            return new ResponseEntity<>("email already in use", HttpStatus.FORBIDDEN);

        }

            Client newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password));

            clientService.saveClient(newClient);


            Account account = new Account("VIN" + getRamdonNumber(10000000, 100000000), LocalDateTime.now(), 00.00, newClient);
            accountService.saveAccount(account);



        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @RequestMapping("/clients/current")
    public ClientDTO getClientAuthenticate(Authentication authentication) {

        return new ClientDTO(clientService.getClientByEmail(authentication.getName()));

    }
//
//    @RequestMapping("/traeCards")
//    public List<CardDTO> Cards(Authentication authentication){
//
//        Client client = clientService.getClientByEmail(authentication.getName());
//
//        List<CardDTO> cards = client.getCards().stream().map(card -> new CardDTO(card)).collect(Collectors.toList());
//
//        return cards;
//    }


}
