package com.br.gabryel.apolices.seguros.api.service;

import com.br.gabryel.apolices.seguros.api.model.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAll();
    Cliente save(Cliente cliente);
    Cliente getCliente(String id);
    Cliente update(Cliente cliente);
    void deleteById(String id);

}
