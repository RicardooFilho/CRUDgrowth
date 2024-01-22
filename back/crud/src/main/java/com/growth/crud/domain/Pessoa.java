package com.growth.crud.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

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
    @NotBlank(message = "Insira seu nome")
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    @PastOrPresent(message = "Data nascimento não pode ser uma data futura")
    @NotNull(message = "Insira sua data de nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf", length = 11, nullable = false)
    @NotBlank(message = "Insira o seu CPF")
    @CPF(message = "CPF inserido é inválido")
    private String cpf;

    @Column(name = "telefone", length = 11, nullable = false)
    @NotBlank(message = "Insira o seu número de telefone")
    @Size(min = 11, max = 11, message = "Telefone inválido")
    private String telefone;

    @Column(name = "idade", nullable = false)
    @NotNull(message = "Informe sua idade")
    private Integer idade;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private List<Endereco> enderecos = new ArrayList<Endereco>();
}
