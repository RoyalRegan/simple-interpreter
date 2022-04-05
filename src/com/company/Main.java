package com.company;

import com.company.interpreter.Lexer;
import com.company.interpreter.Parser;
import com.company.interpreter.token.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import static com.company.interpreter.token.TokenType.SPACE;

public class Main {
    public static void main(String[] args) throws IOException {
        final HashMap<String, Double> heap = new HashMap<>();

        String program = String.join("\n", Files.readAllLines(Path.of("test.txt")));

        Lexer lexer = new Lexer(program);

        List<Token> tokens = lexer.lex();
        tokens.removeIf(t -> t.type == SPACE);

        Parser parser = new Parser(tokens);

        parser.parse().start(heap);
    }
}
