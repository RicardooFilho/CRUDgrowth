package com.growth.crud.repository;

import com.growth.crud.domain.Endereco;
import com.growth.crud.domain.Pessoa;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "/sql/pessoa.sql")
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    private Pageable pageable;

    @Test
    public void getByNomeAndCpfTest() {


        Page<Pessoa> pessoaPage = this.pessoaRepository.getByNomeAndCpf("Ricardo", "10", pageable);

        assertThat(pessoaPage).extracting(Pessoa::getId)
                .hasSize(2)
                .containsExactlyInAnyOrder(4L, 2L);
    }

    @Test
    public void salvaPessoaTest() {
        Pessoa pessoa = new Pessoa(1L,
                "Ricardo",
                LocalDate.of(2003, 10, 27),
                "12345678910",
                "44974001153",
                20,
                List.of(new Endereco()));

        Pessoa novaPessoa = this.pessoaRepository.save(pessoa);

        assertThat(novaPessoa.getId()).isEqualTo(1L);
        assertThat(novaPessoa.getNome()).isEqualTo("Ricardo");
        assertThat(novaPessoa.getDataNascimento()).isEqualTo(LocalDate.of(2003, 10, 27));
        assertThat(novaPessoa.getCpf()).isEqualTo("12345678910");
        assertThat(novaPessoa.getTelefone()).isEqualTo("44974001153");
        assertThat(novaPessoa.getIdade()).isEqualTo(20);
        assertThat(novaPessoa.getEnderecos()).hasSize(1);
    }

    @Test
    public void atualizaPessoaTest() {
        Pessoa pessoa = this.pessoaRepository.findById(4L).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        pessoa.setNome("Nadai");

        Pessoa pessoaAtualizada = this.pessoaRepository.save(pessoa);

        assertThat(pessoaAtualizada.getId()).isEqualTo(4L);
        assertThat(pessoaAtualizada.getNome()).isEqualTo("Nadai");
        assertThat(pessoaAtualizada.getDataNascimento()).isEqualTo(LocalDate.of(2003, 10, 27));
        assertThat(pessoaAtualizada.getCpf()).isEqualTo("52369874110");
        assertThat(pessoaAtualizada.getTelefone()).isEqualTo("44974001185");
        assertThat(pessoaAtualizada.getIdade()).isEqualTo(20);
        assertThat(pessoaAtualizada.getEnderecos()).hasSize(0);
    }

    @Test
    public void deletaPessoaTest() {
        this.pessoaRepository.deleteById(4L);

        List<Pessoa> pessoasSalvas = this.pessoaRepository.findAll();

        assertThat(pessoasSalvas).extracting(Pessoa::getId)
                .containsExactlyInAnyOrder(3L, 2L);
    }
}
