package com.company.ast.statments;

import com.company.ast.values.PredicateNode;

import java.util.HashMap;
import java.util.List;

public class WhileOpNode extends StatementNode {
    public final PredicateNode predicateNode;
    public final List<StatementNode> actionsNode;

    public WhileOpNode(PredicateNode predicateNode, List<StatementNode> actionsNode) {
        this.predicateNode = predicateNode;
        this.actionsNode = actionsNode;
    }

    @Override
    public void doAction(HashMap<String, Double> heap) {
        switch (predicateNode.predicateT.type) {
            case PREDICATE: {
                while (predicateNode.getValue(heap) != 0) {
                    actionsNode.forEach(it -> it.doAction(heap));
                }
                break;
            }
        }
    }
}
