package com.ali.utility;

import java.util.UUID;

public class ActivateCodeGenerator {


    public static String codeGenerator() {
        String code = UUID.randomUUID().toString();
        String[] data = code.split("-");
        StringBuilder activateCode = new StringBuilder();
        for (String string : data) {
            activateCode.append(string.charAt(0));
        }
        return activateCode.toString();
    }
}
