package com.company.ast.values;


import com.company.interpreter.token.Token;

import java.util.HashMap;

public class NumberNode extends ValueNode {

    public final Token number;

    public NumberNode(Token number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number.text;
    }

    @Override
    public Double getValue(HashMap<String, Double> heap) {
        return Double.parseDouble(number.text);
    }
}
