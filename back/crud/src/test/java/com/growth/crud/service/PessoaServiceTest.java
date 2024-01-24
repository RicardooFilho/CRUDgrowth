package com.growth.crud.service;

import com.growth.crud.adapter.PessoaAdapter;
import com.growth.crud.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void getUmaPessoaPorIdExceptionTest() {
        assertThrows(EntityNotFoundException.class, () -> this.pessoaService.getUmaPessoaPorId(1L));
    }
}
