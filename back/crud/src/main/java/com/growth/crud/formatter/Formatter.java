package com.growth.crud.formatter;

import com.growth.crud.dto.PessoaDto;
import org.springframework.data.domain.Page;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class Formatter {

    public static String formatString(String string, String mascara) {
        try {
            MaskFormatter mask = new MaskFormatter(mascara);
            mask.setValueContainsLiteralCharacters(false);

            return mask.valueToString(string);
        } catch (ParseException e) {
                throw new RuntimeException(e);
        }
    }

    public static void formatTelefonePageable(Page<PessoaDto> pessoaDtoPage) {
        pessoaDtoPage.map(pessoaDto -> {
            pessoaDto.setTelefone(Formatter.formatString(pessoaDto.getTelefone(), "(##) #####-####"));

            return pessoaDto;
        });
    }

    public static void formatCpfPageable(Page<PessoaDto> pessoaDtoPage) {
        pessoaDtoPage.map(pessoaDto -> {
            pessoaDto.setCpf(Formatter.formatString(pessoaDto.getCpf(), "###.###.###-##"));

            return pessoaDto;
        });
    }

    public static void formatCepPageable(Page<PessoaDto> pessoaDtoPage) {
        pessoaDtoPage.map(pessoaDto -> {
            pessoaDto.getEnderecos().forEach(endereco -> {
                endereco.setCep(Formatter.formatString(endereco.getCep(), "#####-###"));
            });

            return pessoaDto;
        });
    }

    public static void formatTelefone(PessoaDto pessoaDto) {
            pessoaDto.setTelefone(Formatter.formatString(pessoaDto.getTelefone(), "(##) #####-####"));
    }

    public static void formatCpf(PessoaDto pessoaDto) {
            pessoaDto.setCpf(Formatter.formatString(pessoaDto.getCpf(), "###.###.###-##"));
    }

    public static void formatCep(PessoaDto pessoaDto) {
            pessoaDto.getEnderecos().forEach(endereco -> {
                endereco.setCep(Formatter.formatString(endereco.getCep(), "#####-###"));
            });
    }
}
