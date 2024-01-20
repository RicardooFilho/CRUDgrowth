package com.growth.crud.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "endereco")
@Data
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_endereco")
    @SequenceGenerator(name = "gen_endereco", sequenceName = "endereco_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "cep", length = 8, nullable = false)
    private String cep;

    @Column(name = "logradouro", length = 150, nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "uf", length = 2, nullable = false)
    private String uf;
}
