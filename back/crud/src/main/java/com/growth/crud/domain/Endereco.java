package com.growth.crud.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_endereco")
    @SequenceGenerator(name = "gen_endereco", sequenceName = "endereco_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "cep", length = 8, nullable = false)
    @NotBlank(message = "Insira o seu CEP")
    @Size(min = 8, max = 8, message = "CEP inválido")
    private String cep;

    @Column(name = "logradouro", length = 150, nullable = false)
    @NotBlank(message = "Insira o seu logradouro")
    private String logradouro;

    @Column(name = "numero", nullable = false)
    @NotNull(message = "Insira o número do logradouro")
    private Integer numero;

    @Column(name = "cidade", nullable = false)
    @NotBlank(message = "Insira o nome da sua cidade")
    private String cidade;

    @Column(name = "uf", length = 2, nullable = false)
    @NotBlank(message = "Insira a unidade federativa")
    @Size(min = 2, max = 2, message = "UF inválida")
    private String uf;
}
