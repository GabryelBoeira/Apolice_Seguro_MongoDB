package com.br.gabryel.apolices.seguros.api.service.impl;

import com.br.gabryel.apolices.seguros.api.model.Cliente;
import com.br.gabryel.apolices.seguros.api.repository.ClienteRepository;
import com.br.gabryel.apolices.seguros.api.service.ClienteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import java.util.List;

@Service("ClienteService")
public class ClienteServiceImpl implements ClienteService {

    protected static final String MSG_NOT_FOUND_INQUIRY = "Nenhum documento encontrado para a consulta solicitada";
    protected static final String MSG_NOT_FOUND_UPDATE = "Nenhum documento encontrado para a alteração solicitada";
    protected static final String MSG_NOT_FOUND_DELETE = "Nenhum documento encontrado para a exclução solicitada";

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente getCliente(String id) {
        return clienteRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_INQUIRY)
        );
    }

    @Override
    public Cliente update(Cliente cliente) {
        clienteRepository.findById(cliente.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_UPDATE)
        );
        return null;
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(String id) {
        if(!clienteRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_DELETE);
        }
        clienteRepository.deleteById(id);
    }

    private boolean validarCamposCliente(Cliente cliente){

        return  StringUtils.isNotBlank(cliente.getNome()) ||
                StringUtils.isNotBlank(cliente.getCidade()) ||
                StringUtils.isNotBlank(cliente.getUf()) ;
                //Util.(cliente.getCpf()) ;
    }
}
