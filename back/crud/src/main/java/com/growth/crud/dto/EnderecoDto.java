package com.growth.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EnderecoDto {

    private Long id;

    @NotBlank(message = "Insira um CEP")
    private String cep;

    @NotBlank(message = "Insira um logradouro")
    private String logradouro;

    @NotEmpty(message = "Insira um número")
    private Integer numero;

    @NotBlank(message = "Insira uma cidade")
    private String cidade;

    @NotBlank(message = "Insira uma unidade federativa")
    private String uf;
}
