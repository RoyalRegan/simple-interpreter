package com.company.ast.statments;

import com.company.ast.values.NumberNode;
import com.company.interpreter.token.Token;

import java.util.HashMap;


public class AssigmentOpNode extends StatementNode {
    public Token variable;
    public NumberNode value;

    public AssigmentOpNode(Token variable, NumberNode value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public void doAction(HashMap<String, Double> heap) {
        heap.put(variable.text, value.getValue(heap));
    }
}
