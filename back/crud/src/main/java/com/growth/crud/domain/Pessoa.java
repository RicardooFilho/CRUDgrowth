package com.growth.crud.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_pessoa")
    @SequenceGenerator(name = "gen_pessoa", sequenceName = "pessoa_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "telefone", length = 11, nullable = false)
    private String telefone;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @OneToMany
    @JoinColumn(name = "cep_id", referencedColumnName = "id", nullable = false)
    private List<Endereco> enderecos = new ArrayList<Endereco>();
}
