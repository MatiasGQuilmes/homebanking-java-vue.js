package com.mindhub.homebanking.service.imprement;

import com.mindhub.homebanking.DTOS.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service //esto va a ser un servicio
public class ClientServiceImplement implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<ClientDTO> getClientsDTO() {
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }


    @Override
    public ClientDTO getClientDTO(Long id){

        return clientRepository.findById(id).map(idclient -> new ClientDTO(idclient)).orElse(null);
        //si no encuentra nada en findById, retorna nulo

    }

    @Override
    public Client getClientByEmail(String email){
        return clientRepository.findByEmail(email);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

}
