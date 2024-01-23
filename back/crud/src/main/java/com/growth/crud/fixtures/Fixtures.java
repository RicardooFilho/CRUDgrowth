package com.growth.crud.fixtures;

import com.growth.crud.domain.Endereco;
import com.growth.crud.domain.Pessoa;
import com.growth.crud.dto.PessoaDto;

import java.time.LocalDate;
import java.util.List;

public class Fixtures {

    public static Pessoa criaPessoa() {
        Pessoa pessoa = new Pessoa(1L,
                "Ricardo",
                LocalDate.of(2003, 10, 27),
                "22029103012",
                "44974001153",
                20,
                List.of(new Endereco()));

        return pessoa;
    }

    public static PessoaDto criaPessoaDto() {
        PessoaDto pessoaDto = new PessoaDto(1L,
                "Ricardo",
                LocalDate.of(2003, 10, 27),
                "22029103012",
                "44974001153",
                20,
                List.of(new Endereco(1L,
                        "87045260",
                        "Rua São João",
                        50,
                        "Maringá",
                        "PR")));

        return pessoaDto;
    }
}
