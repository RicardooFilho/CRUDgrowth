package com.growth.crud.service;

import com.growth.crud.adapter.EnderecoAdapter;
import com.growth.crud.domain.Endereco;
import com.growth.crud.dto.EnderecoDto;
import com.growth.crud.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoAdapter enderecoAdapter;

    public EnderecoDto getUmEnderecoPorId(Long id) {
        EnderecoDto enderecoDto = this.enderecoAdapter.toDto(this.enderecoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado")));

        return enderecoDto;
    }

    public Page<EnderecoDto> getTodosEnderecos(Pageable pageable) {
        Page<EnderecoDto> enderecoDtoPage = this.enderecoRepository.findAll(pageable).map(endereco -> this.enderecoAdapter.toDto(endereco));

        return enderecoDtoPage;
    }

    public EnderecoDto putPessoa(Endereco enderecoNovo) {
        Endereco endereco = this.enderecoRepository.findById(enderecoNovo.getId()).orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        endereco.setCep(enderecoNovo.getCep());
        endereco.setLogradouro(enderecoNovo.getLogradouro());
        endereco.setNumero(enderecoNovo.getNumero());
        endereco.setCidade(enderecoNovo.getCidade());
        endereco.setUf(enderecoNovo.getUf());

        EnderecoDto enderecoDto = this.enderecoAdapter.toDto(this.enderecoRepository.save(endereco));

        return enderecoDto;
    }

    public EnderecoDto saveEndereco(EnderecoDto enderecoDto) {
        Endereco enderecoSalvo = this.enderecoRepository.save(this.enderecoAdapter.toEntity(enderecoDto));

        return this.enderecoAdapter.toDto(enderecoSalvo);
    }

    public void deleteEndereco(Long id) {
        this.enderecoRepository.deleteById(id);
    }
}
