package com.br.gabryel.apolices.seguros.api.controller;

import com.br.gabryel.apolices.seguros.api.model.Cliente;
import com.br.gabryel.apolices.seguros.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String hello() {
        return "Bem vindo ao microserviço Apólice de Seguros";
    }

    @PostMapping("/cliente/new")
    public Cliente createTask(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @GetMapping("/cliente/find-all")
    public List<Cliente> getAll() {
        return clienteService.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Cliente getTask(@PathVariable String id) {
        return clienteService.getCliente(id);
    }

    @PutMapping("/cliente/edit")
    public Cliente editTask(@RequestBody Cliente cliente) {
        return clienteService.update(cliente);
    }

    @DeleteMapping("/cliente/{id}")
    public void deleteTask(@PathVariable String id) throws ResponseStatusException {
        clienteService.deleteById(id);
    }
}
