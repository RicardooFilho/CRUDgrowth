package com.growth.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growth.crud.domain.Pessoa;
import com.growth.crud.fixtures.Fixtures;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void pessoaPostTest() throws Exception{
        Pessoa pessoa = Fixtures.criaPessoa();

        mockMvc.perform(post("/api/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void pessoaComCpfInvalidoPostTest() throws Exception {
        Pessoa pessoa = Fixtures.criaPessoa();
        pessoa.setCpf("00000000000");

        mockMvc.perform(post("/api/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoa)))
                        .andExpect(content().string(containsStringIgnoringCase("CPF inserido é inválido")));
    }

    @Test
    @Order(3)
    public void getQuantiadePessoaTest() throws Exception {
        mockMvc.perform(get("/api/pessoas/quantidade-pessoa"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
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
    @Order(5)
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
    @Order(6)
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
    @Order(7)
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
    @Order(8)
    public void pessoaGetTodasPessoasSemNomeCpf() throws Exception {
        mockMvc.perform(get("/api/pessoas"))
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
    @Order(9)
    public void pessoaPutTest() throws Exception {
        Long id = 1L;

        String requestBody = "{\"nome\": \"Nadai\"," +
                            "\"dataNascimento\": \"2003-10-27\"," +
                            "\"cpf\": \"58294480096\"," +
                            "\"telefone\": \"44974001171\"," +
                            "\"idade\": \"20\"," +
                            "\"enderecos\":[{\"id\": \"1\"," +
                                            "\"cep\": \"87045280\"," +
                                            "\"logradouro\": \"Rua Garibaldi\"," +
                                            "\"numero\": \"51\"," +
                                            "\"cidade\": \"Maringá\"," +
                                            "\"uf\": \"PR\"}]}";

        mockMvc.perform(put("/api/pessoas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @Order(10)
    public void pessoaGetPorIdDepoisDoPut() throws Exception{
        Long id = 1L;

        mockMvc.perform(get("/api/pessoas/{id}", id))
                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.id").value(1),
                        jsonPath("$.nome").value("Nadai"),
                        jsonPath("$.dataNascimento").value(LocalDate.of(2003, 10, 27).toString()),
                        jsonPath("$.cpf").value("582.944.800-96"),
                        jsonPath("$.telefone").value("(44) 97400-1171"),
                        jsonPath("$.idade").value(20),
                        jsonPath("$.enderecos[0].id").value(1),
                        jsonPath("$.enderecos[0].cep").value("87045-280"),
                        jsonPath("$.enderecos[0].logradouro").value("Rua Garibaldi"),
                        jsonPath("$.enderecos[0].numero").value(51),
                        jsonPath("$.enderecos[0].cidade").value("Maringá"),
                        jsonPath("$.enderecos[0].uf").value("PR"));
    }

    @Test
    @Order(11)
    public void pessoaDeleteTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/pessoas/{id}", id))
                .andExpect(status().isNoContent());
    }
}
