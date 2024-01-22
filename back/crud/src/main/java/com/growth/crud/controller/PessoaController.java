package com.growth.crud.controller;

import com.growth.crud.adapter.PessoaAdapter;
import com.growth.crud.domain.Pessoa;
import com.growth.crud.dto.PessoaDto;
import com.growth.crud.repository.PessoaRepository;
import com.growth.crud.service.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PostLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> getUmaPessoaPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pessoaService.getUmaPessoaPorId(id));
    }

    @GetMapping("/quantidade-pessoa")
    public ResponseEntity<Long> getQuantidadePessoas() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pessoaService.getQuantidadePessoas());
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDto>> getTodasPessoasPorNomeCpf(@RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "cpf", required = false) String cpf, Pageable pageable) throws Exception{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pessoaService.getTodasPessoasPorNomeCpf(nome, cpf, pageable));
    }

    @PostMapping
    public ResponseEntity<PessoaDto> postPessoa (@RequestBody @Validated PessoaDto novaPessoaDto) {
        PessoaDto pessoaDtoSalva = this.pessoaService.savePessoa(novaPessoaDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pessoaDtoSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> putPessoa(@PathVariable Long id, @RequestBody @Validated Pessoa pessoa) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pessoaService.putPessoa(pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        this.pessoaService.deletePessoa(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
