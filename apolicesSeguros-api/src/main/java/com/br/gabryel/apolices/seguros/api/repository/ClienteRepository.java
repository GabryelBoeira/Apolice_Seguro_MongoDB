package com.br.gabryel.apolices.seguros.api.repository;


import com.br.gabryel.apolices.seguros.api.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

}