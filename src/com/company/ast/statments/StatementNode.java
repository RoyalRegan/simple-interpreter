package com.company.ast.statments;

import java.util.HashMap;

abstract public class StatementNode {
    public abstract void doAction(HashMap<String, Double> heap);
}
