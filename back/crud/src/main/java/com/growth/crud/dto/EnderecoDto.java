package com.growth.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EnderecoDto {

    private Long id;

    @NotBlank(message = "Insira um CEP")
    @Size(min = 8, max = 8, message = "CEP inválido")
    private String cep;

    @NotBlank(message = "Insira um logradouro")
    private String logradouro;

    @NotNull
    private Integer numero;

    @NotBlank(message = "Insira uma cidade")
    private String cidade;

    @NotBlank(message = "Insira uma unidade federativa")
    @Size(min = 2, max = 2, message = "UF inválida")
    private String uf;

}
