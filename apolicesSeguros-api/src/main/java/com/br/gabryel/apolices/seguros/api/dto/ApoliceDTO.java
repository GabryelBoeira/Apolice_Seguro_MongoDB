package com.br.gabryel.apolices.seguros.api.dto;

import com.br.gabryel.apolices.seguros.api.model.Cliente;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

public class ApoliceDTO {

    private String id;
    private String numero;
    private Float valor;
    private LocalDate vigenciaInicial;
    private LocalDate vigenciaFinal;
    private String placaVeiculo;
    private Cliente clienteApolice;
    private String statusApolice;
    private String periodoValido;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public LocalDate getVigenciaInicial() {
        return vigenciaInicial;
    }

    public void setVigenciaInicial(LocalDate vigenciaInicial) {
        this.vigenciaInicial = vigenciaInicial;
    }

    public LocalDate getVigenciaFinal() {
        return vigenciaFinal;
    }

    public void setVigenciaFinal(LocalDate vigenciaFinal) {
        this.vigenciaFinal = vigenciaFinal;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Cliente getClienteApolice() {
        return clienteApolice;
    }

    public void setClienteApolice(Cliente clienteApolice) {
        this.clienteApolice = clienteApolice;
    }

    public String getStatusApolice() {
        return statusApolice;
    }

    public void setStatusApolice(String statusApolice) {
        this.statusApolice = statusApolice;
    }

    public String getPeriodoValido() {
        return periodoValido;
    }

    public void setPeriodoValido(String periodoValido) {
        this.periodoValido = periodoValido;
    }
}
