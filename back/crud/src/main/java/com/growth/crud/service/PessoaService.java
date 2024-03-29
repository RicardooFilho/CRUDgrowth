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

import java.util.Objects;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaAdapter pessoaAdapter;


    public PessoaDto getUmaPessoaPorId(Long id) {
        PessoaDto pessoaDto = this.pessoaAdapter.toDto(this.pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada")));

        Formatter.formatTelefone(pessoaDto);
        Formatter.formatCpf(pessoaDto);
        Formatter.formatCep(pessoaDto);

        return pessoaDto;
    }

    public Long getQuantidadePessoas() {
        return this.pessoaRepository.count();
    }

    public Page<PessoaDto> getTodasPessoasPorNomeCpf(String nome, String cpf, Pageable pageable) {
        if (Objects.nonNull(nome) || Objects.nonNull(cpf)) {
            Page<PessoaDto> pessoaDtoPage = this.pessoaRepository.getByNomeAndCpf(nome, cpf, pageable).map(pessoa -> this.pessoaAdapter.toDto(pessoa));

            Formatter.formatTelefonePageable(pessoaDtoPage);
            Formatter.formatCpfPageable(pessoaDtoPage);
            Formatter.formatCepPageable(pessoaDtoPage);

            return pessoaDtoPage;
        }

        Page<PessoaDto> pessoaDtoPage = this.pessoaRepository.findAll(pageable).map(pessoa -> this.pessoaAdapter.toDto(pessoa));

        Formatter.formatTelefonePageable(pessoaDtoPage);
        Formatter.formatCpfPageable(pessoaDtoPage);
        Formatter.formatCepPageable(pessoaDtoPage);

        return pessoaDtoPage;
    }

    public Pessoa putPessoa (Long id, Pessoa pessoaNova) {
        Pessoa pessoa = this.pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        pessoa.setNome(pessoaNova.getNome());
        pessoa.setDataNascimento(pessoaNova.getDataNascimento());
        pessoa.setCpf(pessoaNova.getCpf());
        pessoa.setTelefone(pessoaNova.getTelefone());
        pessoa.setIdade(pessoaNova.getIdade());
        pessoa.getEnderecos().clear();
        pessoa.getEnderecos().addAll(pessoaNova.getEnderecos());

        return this.pessoaRepository.save(pessoa);
    }

    public Pessoa savePessoa(Pessoa pessoa) {
         return this.pessoaRepository.save(pessoa);
    }

    public void deletePessoa(Long id) {
        this.pessoaRepository.deleteById(id);
    }
}
