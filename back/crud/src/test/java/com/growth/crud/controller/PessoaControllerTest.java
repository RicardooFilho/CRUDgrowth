package com.growth.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growth.crud.adapter.PessoaAdapter;
import com.growth.crud.domain.Pessoa;
import com.growth.crud.fixtures.Fixtures;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void pessoaPostTest() throws Exception{
        Pessoa pessoa = Fixtures.criaPessoa();

        mockMvc.perform(post("/api/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isCreated());
    }

    @Test
    public void pessoaGetPorIdTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/api/pessoas/{id}", id))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                status().isOk(),
                        jsonPath("$.id").value(1),
                        jsonPath("$.nome").value("Ricardo"),
                        jsonPath("$.dataNascimento").value(LocalDate.of(2003, 10, 27).toString()),
                        jsonPath("$.cpf").value("220.291.030-12"),
                        jsonPath("$.telefone").value("(44) 97400-1153"),
                        jsonPath("$.idade").value(20),
                        jsonPath("$.enderecos[0].id").value(1),
                        jsonPath("$.enderecos[0].cep").value("87045-260"),
                        jsonPath("$.enderecos[0].logradouro").value("Rua São João"),
                        jsonPath("$.enderecos[0].numero").value(50),
                        jsonPath("$.enderecos[0].cidade").value("Maringá"),
                        jsonPath("$.enderecos[0].uf").value("PR"));
    }

    @Test
    public void pessoaGetTodasPessoasPorNomeTest() throws Exception {
        mockMvc.perform(get("/api/pessoas?nome=ricar"))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.content[0].id").value(1),
                        jsonPath("$.content[0].nome").value("Ricardo"),
                        jsonPath("$.content[0].dataNascimento").value(LocalDate.of(2003, 10, 27).toString()),
                        jsonPath("$.content[0].cpf").value("220.291.030-12"),
                        jsonPath("$.content[0].telefone").value("(44) 97400-1153"),
                        jsonPath("$.content[0].idade").value(20),
                        jsonPath("$.content[0].enderecos[0].id").value(1),
                        jsonPath("$.content[0].enderecos[0].cep").value("87045-260"),
                        jsonPath("$.content[0].enderecos[0].logradouro").value("Rua São João"),
                        jsonPath("$.content[0].enderecos[0].numero").value(50),
                        jsonPath("$.content[0].enderecos[0].cidade").value("Maringá"),
                        jsonPath("$.content[0].enderecos[0].uf").value("PR"));
    }

    @Test
    public void pessoaGetTodasPessoasPorCpfTest() throws Exception {
        mockMvc.perform(get("/api/pessoas?cpf=20"))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.content[0].id").value(1),
                        jsonPath("$.content[0].nome").value("Ricardo"),
                        jsonPath("$.content[0].dataNascimento").value(LocalDate.of(2003, 10, 27).toString()),
                        jsonPath("$.content[0].cpf").value("220.291.030-12"),
                        jsonPath("$.content[0].telefone").value("(44) 97400-1153"),
                        jsonPath("$.content[0].idade").value(20),
                        jsonPath("$.content[0].enderecos[0].id").value(1),
                        jsonPath("$.content[0].enderecos[0].cep").value("87045-260"),
                        jsonPath("$.content[0].enderecos[0].logradouro").value("Rua São João"),
                        jsonPath("$.content[0].enderecos[0].numero").value(50),
                        jsonPath("$.content[0].enderecos[0].cidade").value("Maringá"),
                        jsonPath("$.content[0].enderecos[0].uf").value("PR"));
    }

    @Test
    public void pessoaGetTodasPessoasPorNomeCpfTest() throws Exception {
        mockMvc.perform(get("/api/pessoas?nome=ricar&cpf=20"))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.content[0].id").value(1),
                        jsonPath("$.content[0].nome").value("Ricardo"),
                        jsonPath("$.content[0].dataNascimento").value(LocalDate.of(2003, 10, 27).toString()),
                        jsonPath("$.content[0].cpf").value("220.291.030-12"),
                        jsonPath("$.content[0].telefone").value("(44) 97400-1153"),
                        jsonPath("$.content[0].idade").value(20),
                        jsonPath("$.content[0].enderecos[0].id").value(1),
                        jsonPath("$.content[0].enderecos[0].cep").value("87045-260"),
                        jsonPath("$.content[0].enderecos[0].logradouro").value("Rua São João"),
                        jsonPath("$.content[0].enderecos[0].numero").value(50),
                        jsonPath("$.content[0].enderecos[0].cidade").value("Maringá"),
                        jsonPath("$.content[0].enderecos[0].uf").value("PR"));
    }

    @Test
    public void pessoaPutTest() throws Exception {
        Long id = 1L;

        String requestBody = "{\"nome\": \"Nadai\"," +
                            "\"dataNascimento\": \"2003-10-27\"," +
                            "\"cpf\": \"58294480096\"," +
                            "\"telefone\": \"44974001171\"," +
                            "\"enderecos[0]\": {\"id\": \"1\"}";

        mockMvc.perform(put("/api/pessoas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }
}
