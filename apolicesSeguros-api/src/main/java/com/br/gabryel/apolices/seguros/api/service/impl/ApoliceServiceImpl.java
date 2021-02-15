package com.br.gabryel.apolices.seguros.api.service.impl;

import com.br.gabryel.apolices.seguros.api.dto.ApoliceDTO;
import com.br.gabryel.apolices.seguros.api.model.Apolice;
import com.br.gabryel.apolices.seguros.api.model.Cliente;
import com.br.gabryel.apolices.seguros.api.parse.ParseApolice;
import com.br.gabryel.apolices.seguros.api.repository.ApoliceRepository;
import com.br.gabryel.apolices.seguros.api.repository.ClienteRepository;
import com.br.gabryel.apolices.seguros.api.service.ApoliceService;
import com.br.gabryel.apolices.seguros.api.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service("ApoliceService")
public class ApoliceServiceImpl implements ApoliceService {

    protected static final String MSG_NOT_FOUND_INQUIRY = "Nenhum documento encontrado para a consulta solicitada";
    protected static final String MSG_NOT_FOUND_UPDATE = "Nenhum documento encontrado para a alteração solicitada";
    protected static final String MSG_NOT_FOUND_DELETE = "Nenhum documento encontrado para a exclução solicitada";
    protected static final String MSG_INVALID_APOLICE_INFORMATION = "Dados da Apólice não informados corretamente";
    protected static final String MSG_INVALID_CLIENTE_INFORMATION = "Cliente não Encontrado para criar nova apólice";
    protected static final String MSG_INVALID_APOLICE_NUMERO ="Número da Apólice informada não encontrada";
    protected static final String MSG_DELETE_SUCCESS = "Cadastro Removido com Sucesso";

    @Autowired
    ApoliceRepository apoliceRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<Apolice> findAll() {
        return apoliceRepository.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public Apolice save(Apolice apolice) {
        if(validarCamposApolice(apolice)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INVALID_APOLICE_INFORMATION);
        }

        if(!clienteRepository.existsById(apolice.getClienteApolice().getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INVALID_CLIENTE_INFORMATION);
        }

        apolice.setNumero(Long.toString(new Date().getTime()));
        Apolice apoliceSave = apoliceRepository.save(apolice);
        return apoliceRepository.findById(apoliceSave.getId()).get();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public Apolice getApolice(String id) {
        return apoliceRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_INQUIRY)
        );
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public Apolice updateApolice(Apolice apolice) {

        if(validarCamposApolice(apolice)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INVALID_APOLICE_INFORMATION);
        }

        if(!clienteRepository.existsById(apolice.getClienteApolice().getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INVALID_CLIENTE_INFORMATION);
        }

        if (!apoliceRepository.existsById(apolice.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_UPDATE);
        }

        return apoliceRepository.save(apolice);
    }

    @Override
    public String deleteById(String id) {
        if (!apoliceRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_DELETE);
        }
        apoliceRepository.deleteById(id);
        return MSG_DELETE_SUCCESS;
    }

    @Override
    public ApoliceDTO buscaApolicePorNumero(String numero) {

        Apolice apolice = apoliceRepository.findByNumero(numero);

        if(apolice == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INVALID_APOLICE_NUMERO);
        }

        ApoliceDTO apoliceDTO = ParseApolice.parseDto(apolice);
        long dias = apoliceDTO.getVigenciaInicial().until(apoliceDTO.getVigenciaFinal(),  ChronoUnit.DAYS);

        if (apoliceDTO.getVigenciaFinal().isEqual(LocalDate.now()) || !apoliceDTO.getVigenciaFinal().isAfter(LocalDate.now())){
            apoliceDTO.setStatusApolice("Apólice Vencida entrar em contado para renovar");
            apoliceDTO.setPeriodoValido("Apólide vencida á " + dias +" dias");
        } else {
            apoliceDTO.setStatusApolice("Apólice dentro da Valida");
            apoliceDTO.setPeriodoValido("Apólide valida por mais "+ dias+ " dias");

        }

        return apoliceDTO;
    }

    private boolean validarCamposApolice(Apolice apolice){

        return StringUtils.isBlank(apolice.getPlacaVeiculo()) ||
               StringUtils.isBlank(apolice.getValor().toString()) ||
               StringUtils.isBlank(apolice.getVigenciaFinal().toString()) ||
               StringUtils.isBlank(apolice.getVigenciaInicial().toString()) ||
               StringUtils.isBlank(apolice.getClienteApolice().getId());
    }
}
