package com.company.ast.values;


import com.company.interpreter.token.Token;

import java.util.HashMap;


public class VarNode extends ValueNode {

    public final Token name;

    public VarNode(Token name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.text;
    }

    @Override
    public Double getValue(HashMap<String, Double> heap) {
        if (!heap.containsKey(name.text)) {
            throw new RuntimeException("Variable " + name.text + " dont exists");
        }
        return heap.get(name.text);
    }
}
