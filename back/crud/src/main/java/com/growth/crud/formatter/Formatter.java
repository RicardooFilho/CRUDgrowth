package com.growth.crud.formatter;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class Formatter {

    public static String formatTelefone(String telefone, String mascara) {
        try {
            MaskFormatter mask = new MaskFormatter(mascara);
            mask.setValueContainsLiteralCharacters(false);

            return mask.valueToString(telefone);
        } catch (ParseException e) {
                throw new RuntimeException(e);
        }
    }

    public static String formatCpf(String cpf, String mascara) {
        try {
            MaskFormatter mask = new MaskFormatter(mascara);
            mask.setValueContainsLiteralCharacters(false);

            return mask.valueToString(cpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
