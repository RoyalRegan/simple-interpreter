package com.company.ast;

import com.company.ast.statments.StatementNode;

import java.util.HashMap;
import java.util.List;

public class ProgramNode {
    public DeclareNode declareNode;
    public List<StatementNode> statementNodes;

    public ProgramNode(DeclareNode declareNode, List<StatementNode> statementNodes) {
        this.declareNode = declareNode;
        this.statementNodes = statementNodes;
    }

    public void start(HashMap<String, Double> heap) {
        if (declareNode != null) {
            declareNode.assigmentOpNodes.forEach(it -> it.doAction(heap));
        }
        statementNodes.forEach(it -> it.doAction(heap));
    }
}
