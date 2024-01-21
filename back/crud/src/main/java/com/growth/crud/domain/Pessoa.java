package com.growth.crud.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_pessoa")
    @SequenceGenerator(name = "gen_pessoa", sequenceName = "pessoa_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    @PastOrPresent(message = "Data nascimento não pode ser uma data futura")
    private LocalDate dataNascimento;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "telefone", length = 11, nullable = false)
    private String telefone;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private List<Endereco> enderecos = new ArrayList<Endereco>();
}
