package com.growth.crud.formatter;

import com.growth.crud.adapter.PessoaAdapter;
import com.growth.crud.dto.PessoaDto;
import com.growth.crud.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
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
@Sql("/sql/pessoa.sql")
@Component
public class FormatterTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaAdapter pessoaAdapter;


    @Test
    public void formatTelefonePageableTest() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<PessoaDto> pessoaDtoPage = this.pessoaRepository.findAll(pageable).map(pessoa -> this.pessoaAdapter.toDto(pessoa));

        pessoaDtoPage.map(pessoaDto -> {
            pessoaDto.setTelefone(Formatter.formatString(pessoaDto.getTelefone(), "(##) #####-####"));

            return pessoaDto;
        });

        assertThat(pessoaDtoPage.getContent().get(0).getTelefone()).isEqualTo("123.456.789-10");
    }

    public static void formatCpfPageable(Page<PessoaDto> pessoaDtoPage) {
        pessoaDtoPage.map(pessoaDto -> {
            pessoaDto.setCpf(Formatter.formatString(pessoaDto.getCpf(), "###.###.###-##"));

            return pessoaDto;
        });
    }

    public static void formatCepPageable(Page<PessoaDto> pessoaDtoPage) {
        pessoaDtoPage.map(pessoaDto -> {
            pessoaDto.getEnderecos().forEach(endereco -> {
                endereco.setCep(Formatter.formatString(endereco.getCep(), "#####-###"));
            });

            return pessoaDto;
        });
    }

    public static void formatTelefone(PessoaDto pessoaDto) {
        pessoaDto.setTelefone(Formatter.formatString(pessoaDto.getTelefone(), "(##) #####-####"));
    }

    public static void formatCpf(PessoaDto pessoaDto) {
        pessoaDto.setCpf(Formatter.formatString(pessoaDto.getCpf(), "###.###.###-##"));
    }

    public static void formatCep(PessoaDto pessoaDto) {
        pessoaDto.getEnderecos().forEach(endereco -> {
            endereco.setCep(Formatter.formatString(endereco.getCep(), "#####-###"));
        });
    }
}
