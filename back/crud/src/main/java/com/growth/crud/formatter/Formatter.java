package com.growth.crud.formatter;

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
}
