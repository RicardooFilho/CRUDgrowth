package com.growth.crud.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PessoaTest {

    @Test
    public void criacaoDePessoaTest() {
        Pessoa pessoa = new Pessoa(1L,
                "Ricardo",
                LocalDate.of(2003, 10, 27),
                "12345678910",
                "44974001153",
                20,
                List.of(new Endereco()));

        assertThat(pessoa.getId()).isEqualTo(1L);
        assertThat(pessoa.getNome()).isEqualTo("Ricardo");
        assertThat(pessoa.getDataNascimento()).isEqualTo(LocalDate.of(2003, 10, 27));
        assertThat(pessoa.getCpf()).isEqualTo("12345678910");
        assertThat(pessoa.getTelefone()).isEqualTo("44974001153");
        assertThat(pessoa.getIdade()).isEqualTo(20);
        assertThat(pessoa.getEnderecos()).hasSize(1);


    }
}
