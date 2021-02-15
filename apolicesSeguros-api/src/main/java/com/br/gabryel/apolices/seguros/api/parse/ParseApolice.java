package com.br.gabryel.apolices.seguros.api.parse;

import com.br.gabryel.apolices.seguros.api.dto.ApoliceDTO;
import com.br.gabryel.apolices.seguros.api.model.Apolice;

import java.time.LocalDate;

public class ParseApolice {

    public static ApoliceDTO parseDto(Apolice apolice) {
        ApoliceDTO parseDTO  = new ApoliceDTO();

        if (apolice == null) return parseDTO;

        parseDTO.setId(apolice.getId());
        parseDTO.setNumero(apolice.getNumero());
        parseDTO.setValor(apolice.getValor());
        parseDTO.setPlacaVeiculo(apolice.getPlacaVeiculo());
        parseDTO.setVigenciaInicial(apolice.getVigenciaInicial());
        parseDTO.setVigenciaFinal(apolice.getVigenciaFinal());
        parseDTO.setClienteApolice(apolice.getClienteApolice());

        return parseDTO;
    }

}
