package com.company.interpreter.token;

public class Token {
    public final TokenType type;
    public final String text;
    public final int posInLine;
    public final int lineNum;

    public Token(TokenType type, String text, int posInLine,int lineNum) {
        this.type = type;
        this.text = text;
        this.posInLine = posInLine;
        this.lineNum = lineNum;
    }

    @Override
    public String toString() {
        return text;
    }
}
