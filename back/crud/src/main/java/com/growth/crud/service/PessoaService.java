package com.growth.crud.service;

import com.growth.crud.adapter.PessoaAdapter;
import com.growth.crud.domain.Pessoa;
import com.growth.crud.dto.PessoaDto;
import com.growth.crud.formatter.Formatter;
import com.growth.crud.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.text.ParseException;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaAdapter pessoaAdapter;


    public PessoaDto getUmaPessoaPorId(Long id) {
        PessoaDto pessoaDto = this.pessoaAdapter.toDto(this.pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada")));

        pessoaDto.setTelefone(Formatter.formatTelefone(pessoaDto.getTelefone(), "(##) #####-####"));

        pessoaDto.setCpf(Formatter.formatCpf(pessoaDto.getCpf(), "###.###.###-##"));

        return pessoaDto;
    }

    public Long getQuantidadePessoas() {
        return this.pessoaRepository.count();
    }

    public Page<PessoaDto> getTodasPessoasPorNomeCpf(String nome, String cpf, Pageable pageable) {
        Page<PessoaDto> pessoaDtoPage = this.pessoaRepository.getByNomeAndCpf(nome, cpf, pageable).map(pessoa -> this.pessoaAdapter.toDto(pessoa));

        pessoaDtoPage.map(pessoaDto -> {
                    pessoaDto.setTelefone(Formatter.formatTelefone(pessoaDto.getTelefone(), "(##) #####-####"));

                    return pessoaDto;
                });

        pessoaDtoPage.map(pessoaDto -> {
            pessoaDto.setCpf(Formatter.formatCpf(pessoaDto.getCpf(), "###.###.###-##"));

            return pessoaDto;
        });

        return pessoaDtoPage;
    }

    public PessoaDto putPessoa (Pessoa pessoaNova) {
        Pessoa pessoa = this.pessoaRepository.findById(pessoaNova.getId()).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        pessoa.setNome(pessoaNova.getNome());
        pessoa.setDataNascimento(pessoaNova.getDataNascimento());
        pessoa.setCpf(pessoaNova.getCpf());
        pessoa.setTelefone(pessoaNova.getTelefone());
        pessoa.setIdade(pessoaNova.getIdade());
        pessoa.setEnderecos(pessoaNova.getEnderecos());

        PessoaDto pessoaDto = this.pessoaAdapter.toDto(this.pessoaRepository.save(pessoa));

        return pessoaDto;
    }

    public Pessoa savePessoa(Pessoa pessoa) {
         return this.pessoaRepository.save(pessoa);
    }

    public void deletePessoa(Long id) {
        this.pessoaRepository.deleteById(id);
    }
}
