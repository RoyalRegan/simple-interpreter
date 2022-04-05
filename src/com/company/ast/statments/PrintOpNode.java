package com.company.ast.statments;

import com.company.ast.values.ValueNode;

import java.util.HashMap;

public class PrintOpNode extends StatementNode {
    public final ValueNode variable;

    public PrintOpNode(ValueNode variable) {
        this.variable = variable;
    }

    @Override
    public void doAction(HashMap<String, Double> heap) {
        System.out.println(variable.getValue(heap));
    }
}