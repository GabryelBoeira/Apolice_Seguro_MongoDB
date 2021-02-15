package com.br.gabryel.apolices.seguros.api.service.impl;

import com.br.gabryel.apolices.seguros.api.model.Apolice;
import com.br.gabryel.apolices.seguros.api.model.Cliente;
import com.br.gabryel.apolices.seguros.api.repository.ApoliceRepository;
import com.br.gabryel.apolices.seguros.api.repository.ClienteRepository;
import com.br.gabryel.apolices.seguros.api.service.ClienteService;
import com.br.gabryel.apolices.seguros.api.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.jsf.FacesContextUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service("ClienteService")
public class ClienteServiceImpl implements ClienteService {

    protected static final String MSG_NOT_FOUND_INQUIRY = "Nenhum documento encontrado para a consulta solicitada";
    protected static final String MSG_NOT_FOUND_UPDATE = "Nenhum documento encontrado para a alteração solicitada";
    protected static final String MSG_NOT_FOUND_DELETE = "Nenhum documento encontrado para a exclução solicitada";
    protected static final String MSG_INVALID_CPF = "CPF informado ínválido";
    protected static final String MSG_DUPLICATE_CPF = "CPF informado já Cadastrado";
    protected static final String MSG_INVALID_CUSTOMER_INFORMATION = "Dados co cliente não informados corretamente";
    protected static final String MSG_DELETE_SUCCESS = "Cadastro Removido com Sucesso";


    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ApoliceRepository apoliceRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(Cliente cliente) {
        cliente.setCpf(cliente.getCpf().replace("\\D", ""));

        if(validarCamposCliente(cliente)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INVALID_CUSTOMER_INFORMATION);
        }

        if(!Util.isValidCPF(cliente.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INVALID_CPF);
        }

        if(clienteRepository.findByCpfEquals(cliente.getCpf()) != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_DUPLICATE_CPF);
        }

        return clienteRepository.save(cliente);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public Cliente getCliente(String id) {
        return clienteRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_INQUIRY)
        );
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public Cliente update(Cliente clienteUpdate) {
        clienteUpdate.setCpf(clienteUpdate.getCpf().replace("\\D", ""));

        if(validarCamposCliente(clienteUpdate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INVALID_CUSTOMER_INFORMATION);
        }

        if (!clienteRepository.existsById(clienteUpdate.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_UPDATE);
        }

        if(!Util.isValidCPF(clienteUpdate.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INVALID_CPF);
        }

        Cliente cliente = clienteRepository.findByCpfEquals(clienteUpdate.getCpf());
        if(cliente != null && !cliente.getId().equals(clienteUpdate.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_DUPLICATE_CPF);
        }

        return clienteRepository.save(clienteUpdate);
    }

    @Override
    @ResponseStatus(value = HttpStatus.OK, reason = MSG_DELETE_SUCCESS)
    public String deleteById(String id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_DELETE);
        }

        apoliceRepository.deleteAllByClienteApolice(id);
        clienteRepository.deleteById(id);
        return MSG_DELETE_SUCCESS;
    }

    private boolean validarCamposCliente(Cliente cliente){

        return  StringUtils.isBlank(cliente.getNome()) ||
                StringUtils.isBlank(cliente.getCidade()) ||
                StringUtils.isBlank(cliente.getUf());
    }
}
