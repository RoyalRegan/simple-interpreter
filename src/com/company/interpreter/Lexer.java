package com.company.interpreter;

import com.company.interpreter.token.Token;
import com.company.interpreter.token.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Lexer {

    private final String src;
    private int rawPos = 0;
    private int posInLine=0;
    private int lineNum =1;
    private final List<Token> tokens = new ArrayList<>();

    public Lexer(String src) {
        this.src = src;
    }

    private boolean nextToken() {
        if (rawPos >= src.length())
            return false;
        for (TokenType tokenType : TokenType.values()) {
            Matcher m = tokenType.pattern.matcher(src);
            m.region(rawPos, src.length());
            if (m.lookingAt()) {
                String s =  m.group();
                if(s.contains("\n")) {
                    lineNum++;
                    posInLine=0;
                }
                posInLine+=s.length();
                Token t = new Token(tokenType,s, posInLine, lineNum);
                tokens.add(t);
                rawPos = m.end();
                return true;
            }
        }
        throw new RuntimeException("Unexpected symbol " + src.charAt(rawPos) + " in line " + lineNum + " position "+ posInLine);
    }

    public List<Token> lex() {
        while (nextToken()) {
        }
        return tokens;
    }
}
