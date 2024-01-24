package com.growth.crud.formatter;

import com.growth.crud.adapter.PessoaAdapter;
import com.growth.crud.dto.PessoaDto;
import com.growth.crud.fixtures.Fixtures;
import com.growth.crud.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql({"/sql/pessoa.sql", "/sql/endereco.sql"})
public class FormatterTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaAdapter pessoaAdapter;


    @Test
    public void formatTelefonePageableTest() {
        Pageable pageable = PageRequest.of(0, 20);

        Page<PessoaDto> pessoaDtoPage = this.pessoaRepository.findAll(pageable).map(pessoa -> this.pessoaAdapter.toDto(pessoa));

        pessoaDtoPage.map(pessoaDto -> {
            pessoaDto.setTelefone(Formatter.formatString(pessoaDto.getTelefone(), "(##) #####-####"));

            return pessoaDto;
        });

        assertThat(pessoaDtoPage.getContent().get(0).getTelefone()).isEqualTo("(44) 97400-1185");
    }

    @Test
    public  void formatCpfPageableTest() {
        Pageable pageable = PageRequest.of(0, 20);

        Page<PessoaDto> pessoaDtoPage = this.pessoaRepository.findAll(pageable).map(pessoa -> this.pessoaAdapter.toDto(pessoa));

        pessoaDtoPage.map(pessoaDto -> {
            pessoaDto.setCpf(Formatter.formatString(pessoaDto.getCpf(), "###.###.###-##"));

            return pessoaDto;
        });

        assertThat(pessoaDtoPage.getContent().get(0).getCpf()).isEqualTo("123.456.789-10");
    }

    @Test
    public void formatCepPageable() {
        Pageable pageable = PageRequest.of(0, 20);

        Page<PessoaDto> pessoaDtoPage = this.pessoaRepository.findAll(pageable).map(pessoa -> this.pessoaAdapter.toDto(pessoa));

        pessoaDtoPage.map(pessoaDto -> {
            pessoaDto.getEnderecos().forEach(endereco -> {
                endereco.setCep(Formatter.formatString(endereco.getCep(), "#####-###"));
            });

            return pessoaDto;
        });

        assertThat(pessoaDtoPage.getContent().get(0).getEnderecos().get(0).getCep()).isEqualTo("87046-280");
    }

    @Test
    public void formatTelefone() {
        Long id = 2L;

        PessoaDto pessoaDto = this.pessoaAdapter.toDto(this.pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada")));

        pessoaDto.setTelefone(Formatter.formatString(pessoaDto.getTelefone(), "(##) #####-####"));

        assertThat(pessoaDto.getTelefone()).isEqualTo("(44) 97400-1185");
    }

    @Test
    public void formatCpf() {
        Long id = 2L;

        PessoaDto pessoaDto = this.pessoaAdapter.toDto(this.pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada")));

        pessoaDto.setCpf(Formatter.formatString(pessoaDto.getCpf(), "###.###.###-##"));

        assertThat(pessoaDto.getCpf()).isEqualTo("123.456.789-10");
    }

    @Test
    public void formatCep() {
        Long id = 2L;

        PessoaDto pessoaDto = this.pessoaAdapter.toDto(this.pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada")));

        pessoaDto.getEnderecos().forEach(endereco -> {
            endereco.setCep(Formatter.formatString(endereco.getCep(), "#####-###"));
        });

        assertThat(pessoaDto.getEnderecos().get(0).getCep()).isEqualTo("87046-280");
    }
}
