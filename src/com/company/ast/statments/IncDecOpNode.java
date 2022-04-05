package com.company.ast.statments;

import com.company.ast.values.VarNode;
import com.company.interpreter.token.Token;

import java.util.HashMap;

import static com.company.interpreter.token.TokenType.INCREMENT;

public class IncDecOpNode extends StatementNode {
    public VarNode variable;
    public Token action;

    public IncDecOpNode(VarNode variable, Token action) {
        this.variable = variable;
        this.action = action;
    }

    @Override
    public void doAction(HashMap<String, Double> heap) {
        if (!heap.containsKey(variable.name.text)) {
            throw new RuntimeException("Variable " + variable.name.text + " dont exists");
        }
        Double var = heap.get(variable.name.text);
        if (action.type == INCREMENT) {
            var += 1;
        } else {
            var -= 1;
        }
        heap.replace(variable.name.text, var);
    }
}
