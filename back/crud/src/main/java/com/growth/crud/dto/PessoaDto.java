package com.growth.crud.dto;

import com.growth.crud.domain.Endereco;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class PessoaDto {

    private Long id;

    @NotBlank(message = "Insira um nome")
    private String nome;

    @NotEmpty
    private LocalDate dataNascimento;

    @NotBlank(message = "Insira um CPF")
    @CPF(message = "CPF inserido é inválido")
    private String cpf;

    @NotBlank(message = "Insira um telefone")
    private String telefone;

    @NotEmpty(message = "Insira uma idade")
    private Integer idade;

    private List<Endereco> enderecos = new ArrayList<Endereco>();
}
