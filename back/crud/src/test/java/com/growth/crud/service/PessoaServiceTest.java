package com.growth.crud.service;

import com.growth.crud.adapter.PessoaAdapter;
import com.growth.crud.domain.Pessoa;
import com.growth.crud.dto.PessoaDto;
import com.growth.crud.fixtures.Fixtures;
import com.growth.crud.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.Mod10Check;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PessoaAdapter pessoaAdapter;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    public void getUmaPessoaPorIdTest() {
        when(this.pessoaRepository.findById(any())).thenReturn(Optional.of(Fixtures.criaPessoa()));
        when(this.pessoaAdapter.toDto(any())).thenReturn(Fixtures.criaPessoaDto());

        PessoaDto pessoaDto1 = this.pessoaService.getUmaPessoaPorId(1L);

        assertThat(pessoaDto1.getCpf()).isEqualTo("220.291.030-12");
        assertThat(pessoaDto1.getTelefone()).isEqualTo("(44) 97400-1153");
        assertThat(pessoaDto1.getEnderecos().get(0).getCep()).isEqualTo("87045-260");
    }

    @Test
    public void getUmaPessoaPorIdExceptionTest() {
        assertThrows(EntityNotFoundException.class, () -> this.pessoaService.getUmaPessoaPorId(1L));
    }

    /*@Test
    public void getTodasPessoasPorNomeCpfTest() {
        when(this.pessoaRepository.getByNomeAndCpf(any(), any(), any()).map(pessoa -> this.pessoaAdapter.toDto(pessoa))).thenReturn((Page<PessoaDto>) List.of(Fixtures.criaPessoaDto()));

        Page<PessoaDto> pessoaDtoPage = this.pessoaService.getTodasPessoasPorNomeCpf("Ricardo", "20", pageable);

        assertThat(pessoaDtoPage.map(pessoaDto -> pessoaDto.getEnderecos().get(0).getCep())).isEqualTo("87045-260");
    }*/


}
