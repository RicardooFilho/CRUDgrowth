package com.growth.crud.controller;

import com.growth.crud.domain.Pessoa;
import com.growth.crud.dto.PessoaDto;
import com.growth.crud.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Pessoa> postPessoa (@RequestBody @Validated Pessoa novaPessoa) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.pessoaService.savePessoa(novaPessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> putPessoa(@PathVariable Long id, @RequestBody @Validated Pessoa pessoa) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pessoaService.putPessoa(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        this.pessoaService.deletePessoa(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
