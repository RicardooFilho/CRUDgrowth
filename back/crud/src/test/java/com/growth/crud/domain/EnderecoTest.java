package com.growth.crud.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnderecoTest {

    @Test
    public void criacaoDeEnderecoTest() {
        Endereco endereco = new Endereco(1L,
                "87045260",
                "Rua São João",
                50,
                "Maringá",
                "PR");

        assertThat(endereco.getId()).isEqualTo(1L);
        assertThat(endereco.getCep()).isEqualTo("87045260");
        assertThat(endereco.getLogradouro()).isEqualTo("Rua São João");
        assertThat(endereco.getNumero()).isEqualTo(50);
        assertThat(endereco.getCidade()).isEqualTo("Maringá");
        assertThat(endereco.getUf()).isEqualTo("PR");
    }
}
