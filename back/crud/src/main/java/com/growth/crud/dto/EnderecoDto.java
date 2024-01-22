package com.growth.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EnderecoDto {

    private Long id;

    private String cep;

    private String logradouro;

    private Integer numero;

    private String cidade;

    private String uf;
}
