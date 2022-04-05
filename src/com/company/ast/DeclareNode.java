package com.company.ast;

import com.company.ast.statments.AssigmentOpNode;
import com.company.interpreter.token.Token;

import java.util.List;

public class DeclareNode {
    public Token declare;
    public List<AssigmentOpNode> assigmentOpNodes;

    public DeclareNode(Token declare, List<AssigmentOpNode> assigmentOpNodes) {
        this.declare = declare;
        this.assigmentOpNodes = assigmentOpNodes;
    }
}
