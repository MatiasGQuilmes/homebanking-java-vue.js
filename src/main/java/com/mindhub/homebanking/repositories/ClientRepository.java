package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//REST: arquitectura de datos (Representational State Transfer), R(Representa informacion en estado json), ES(cambia el estado del cliente), T(transfiere los datos)
@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long> {


    Client findByEmail(String email);


}
