package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource                                     //los metodos q heredo de jpa repository trabaje con transaction - y con el tipo de dato de la clave primaria Long
public interface TransactionRepository extends JpaRepository<Transaction, Long> { //trae y lleva datos a la base de datos
}                                              //jpa repository es una libreria que hereda metodos para llevar y traer los datos de la bd
