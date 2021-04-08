package com.br.gabryel.apolices.seguros.api.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cliente")
public class Cliente {

    @Id
    private String id;

    @NonNull
    private String nome;

    @NonNull
    private String cpf;

    @NonNull
    private String cidade;

    @NonNull
    private String uf;

    public Cliente(String nome, String cpf, String cidade, String uf) {
        this.nome = nome;
        this.cpf = cpf;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
