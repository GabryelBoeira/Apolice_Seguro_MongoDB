package com.br.gabryel.apolices.seguros.api.controller;

import com.br.gabryel.apolices.seguros.api.dto.ApoliceDTO;
import com.br.gabryel.apolices.seguros.api.model.Apolice;
import com.br.gabryel.apolices.seguros.api.model.Cliente;
import com.br.gabryel.apolices.seguros.api.service.ApoliceService;
import com.br.gabryel.apolices.seguros.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ApoliceController {

    @Autowired
    ApoliceService apoliceService;

    @PostMapping("/apolice/new")
    public Apolice createApolice(@RequestBody Apolice apolice) {
        return apoliceService.save(apolice);
    }

    @GetMapping("/apolice/list-all")
    public List<Apolice> getAllApolice() {
        return apoliceService.findAll();
    }

    @GetMapping("/apolice/{id}")
    public Apolice getApolice(@PathVariable String id) {
        return apoliceService.getApolice(id);
    }

    @PutMapping("/apolice/edit")
    public Apolice editApolice(@RequestBody Apolice apolice) {
        return apoliceService.updateApolice(apolice);
    }

    @DeleteMapping("/apolice/{id}")
    public ResponseEntity<String> deleteApolice(@PathVariable String id) {
        return new ResponseEntity<String>(apoliceService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/apolice/buscar-numero/{numero}")
    public ApoliceDTO buscarApolice(@PathVariable String numero) {
        return apoliceService.buscaApolicePorNumero(numero);
    }
}
