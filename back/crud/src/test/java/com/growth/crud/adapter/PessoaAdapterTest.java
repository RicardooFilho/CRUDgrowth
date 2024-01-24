package com.growth.crud.adapter;

import com.growth.crud.domain.Pessoa;
import com.growth.crud.dto.PessoaDto;
import com.growth.crud.fixtures.Fixtures;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PessoaAdapterTest {

    @InjectMocks
    private PessoaAdapter pessoaAdapter;

    @Test
    public void toDtoTest() {
        Pessoa pessoa = Fixtures.criaPessoa();

        PessoaDto pessoaDto = this.pessoaAdapter.toDto(pessoa);

        assertThat(pessoaDto.getId()).isEqualTo(1L);
        assertThat(pessoaDto.getNome()).isEqualTo("Ricardo");
        assertThat(pessoaDto.getDataNascimento()).isEqualTo(LocalDate.of(2003, 10, 27));
        assertThat(pessoaDto.getCpf()).isEqualTo("22029103012");
        assertThat(pessoaDto.getTelefone()).isEqualTo("44974001153");
        assertThat(pessoaDto.getIdade()).isEqualTo(20);
        assertThat(pessoaDto.getEnderecos()).hasSize(1);
    }

    @Test
    public void toEntityTest() {
        PessoaDto pessoaDto = Fixtures.criaPessoaDto();

        Pessoa pessoa = this.pessoaAdapter.toEntity(pessoaDto);

        assertThat(pessoa.getId()).isEqualTo(1L);
        assertThat(pessoa.getNome()).isEqualTo("Ricardo");
        assertThat(pessoa.getDataNascimento()).isEqualTo(LocalDate.of(2003, 10, 27));
        assertThat(pessoa.getCpf()).isEqualTo("22029103012");
        assertThat(pessoa.getTelefone()).isEqualTo("44974001153");
        assertThat(pessoa.getIdade()).isEqualTo(20);
        assertThat(pessoa.getEnderecos()).hasSize(1);
    }
}
