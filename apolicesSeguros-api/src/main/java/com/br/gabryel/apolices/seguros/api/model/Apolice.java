package com.br.gabryel.apolices.seguros.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Document(collection = "apolices")
public class Apolice {

    @Id
    private String id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "vigencia_inicial", nullable = false)
    private LocalDate vigenciaInicial;

    @Column(name = "vigencia_final", nullable = false)
    private LocalDate vigenciaFinal;

    @Column(name = "placa_veiculo", nullable = false)
    private String placaVeiculo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente clienteApolice;

}
