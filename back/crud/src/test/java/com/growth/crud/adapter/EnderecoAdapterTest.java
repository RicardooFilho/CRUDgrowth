package com.growth.crud.adapter;

import com.growth.crud.domain.Endereco;
import com.growth.crud.dto.EnderecoDto;
import com.growth.crud.fixtures.Fixtures;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EnderecoAdapterTest {

    @InjectMocks
    private EnderecoAdapter enderecoAdapter;

    @Test
    public void toDtoTest() {
        Endereco endereco = Fixtures.criaEndereco();

        EnderecoDto enderecoDto = this.enderecoAdapter.toDto(endereco);

        assertThat(enderecoDto.getId()).isEqualTo(1L);
        assertThat(enderecoDto.getCep()).isEqualTo("87045260");
        assertThat(enderecoDto.getLogradouro()).isEqualTo("Rua São João");
        assertThat(enderecoDto.getNumero()).isEqualTo(50);
        assertThat(enderecoDto.getCidade()).isEqualTo("Maringá");
        assertThat(enderecoDto.getUf()).isEqualTo("PR");
    }

    @Test
    public void toEntityTest() {
        EnderecoDto enderecoDto = Fixtures.criaEnderecoDto();

        Endereco endereco = this.enderecoAdapter.toEntity(enderecoDto);

        assertThat(endereco.getId()).isEqualTo(1L);
        assertThat(endereco.getCep()).isEqualTo("87045260");
        assertThat(endereco.getLogradouro()).isEqualTo("Rua São João");
        assertThat(endereco.getNumero()).isEqualTo(50);
        assertThat(endereco.getCidade()).isEqualTo("Maringá");
        assertThat(endereco.getUf()).isEqualTo("PR");
    }
}
