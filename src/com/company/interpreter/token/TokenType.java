package com.company.interpreter.token;

import java.util.regex.Pattern;

public enum TokenType {
    //TODO: simplify
    NUMBER("-?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"),
    DECLARE("DECLARE|declare"),
    BEGIN("BEGIN|begin"),
    END("END|end"),
    WHILE("WHILE|while"),
    DONE("DONE|done"),
    DO("DO|do"),
    PRINT("PRINT|print"),
    ASSIGNMENT(":="),
    PREDICATE("(>=)|(<=)|>|<|="),
    VARIABLE("[a-zA-Z_][a-zA-Z_0-9]*"),
    INCREMENT("\\+\\+"),
    DECREMENT("--"),
    SEMICOLON(";"),
    SPACE("[ \t\r\n]+");

    public final Pattern pattern;

    TokenType(String regexp) {
        pattern = Pattern.compile(regexp);
    }
}
