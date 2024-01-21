package com.growth.crud.repository;

import com.growth.crud.domain.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "select p from Pessoa p where p.nome ilike %:nome% or p.cpf ilike %:cpf%")
    Page<Pessoa> getByNomeAndCpf(String nome, String cpf, Pageable pageable);
}
