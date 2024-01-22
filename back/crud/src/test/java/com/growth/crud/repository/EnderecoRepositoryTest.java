package com.growth.crud.repository;

import com.growth.crud.domain.Endereco;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles
@Sql(scripts = {"/sql/pessoa.sql", "/sql/endereco.sql"})
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    public void salvaEnderecoTest() {
        Endereco endereco = new Endereco(1L,
                "87045260",
                "Rua São João",
                50,
                "Maringá",
                "PR");

        Endereco enderecoSalvo = this.enderecoRepository.save(endereco);

        assertThat(enderecoSalvo.getId()).isNotNull().isEqualTo(1L);
        assertThat(enderecoSalvo.getCep()).isEqualTo("87045260");
        assertThat(enderecoSalvo.getLogradouro()).isEqualTo("Rua São João");
        assertThat(enderecoSalvo.getNumero()).isEqualTo(50);
        assertThat(enderecoSalvo.getCidade()).isEqualTo("Maringá");
        assertThat(enderecoSalvo.getUf()).isEqualTo("PR");
    }

    @Test
    public void atualizaEnderecoTest() {
        Endereco endereco = this.enderecoRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        endereco.setUf("SP");

        Endereco enderecoAtualizado = this.enderecoRepository.save(endereco);

        assertThat(enderecoAtualizado.getId()).isNotNull().isEqualTo(1L);
        assertThat(enderecoAtualizado.getCep()).isEqualTo("87046280");
        assertThat(enderecoAtualizado.getLogradouro()).isEqualTo("Rua Garibaldi");
        assertThat(enderecoAtualizado.getNumero()).isEqualTo(50);
        assertThat(enderecoAtualizado.getCidade()).isEqualTo("Maringá");
        assertThat(enderecoAtualizado.getUf()).isEqualTo("SP");
    }

    @Test
    public void deletaEnderecoTest() {
        this.enderecoRepository.deleteById(1L);

        List<Endereco> enderecosSalvos = this.enderecoRepository.findAll();

        assertThat(enderecosSalvos).extracting(Endereco::getId)
                .hasSize(0);
    }
}
