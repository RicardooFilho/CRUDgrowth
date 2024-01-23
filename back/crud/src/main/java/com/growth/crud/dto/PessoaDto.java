package com.growth.crud.dto;

import com.growth.crud.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class PessoaDto {

    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String cpf;

    private String telefone;

    private Integer idade;

    private List<Endereco> enderecos = new ArrayList<Endereco>();
}
