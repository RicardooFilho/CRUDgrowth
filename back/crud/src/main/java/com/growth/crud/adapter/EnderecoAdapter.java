package com.growth.crud.adapter;

import com.growth.crud.domain.Endereco;
import com.growth.crud.dto.EnderecoDto;
import org.springframework.stereotype.Service;

@Service
public class EnderecoAdapter implements Adapter<EnderecoDto, Endereco>{

    @Override
    public EnderecoDto toDto(Endereco endereco) {
        return new EnderecoDto(endereco.getId(),
                                endereco.getCep(),
                                endereco.getLogradouro(),
                                endereco.getNumero(),
                                endereco.getCidade(),
                                endereco.getUf());
    }

    @Override
    public Endereco toEntity(EnderecoDto enderecoDto) {
        return new Endereco(enderecoDto.getId(),
                            enderecoDto.getCep(),
                            enderecoDto.getLogradouro(),
                            enderecoDto.getNumero(),
                            enderecoDto.getCidade(),
                            enderecoDto.getUf());
    }
}
