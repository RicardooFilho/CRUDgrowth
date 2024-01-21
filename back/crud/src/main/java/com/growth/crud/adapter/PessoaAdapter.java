package com.growth.crud.adapter;

import com.growth.crud.domain.Pessoa;
import com.growth.crud.dto.PessoaDto;
import org.springframework.stereotype.Service;

@Service
public class PessoaAdapter implements Adapter<PessoaDto, Pessoa>{

    @Override
    public PessoaDto toDto(Pessoa pessoa) {
        return new PessoaDto(pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getDataNascimento(),
                            pessoa.getCpf(),
                            pessoa.getTelefone(),
                            pessoa.getIdade(),
                            pessoa.getEnderecos());
    }

    @Override
    public Pessoa toEntity(PessoaDto pessoaDto) {
        return new Pessoa(pessoaDto.getId(),
                        pessoaDto.getNome(),
                        pessoaDto.getDataNascimento(),
                        pessoaDto.getCpf(),
                        pessoaDto.getTelefone(),
                        pessoaDto.getIdade(),
                        pessoaDto.getEnderecos());
    }
}
