package com.company.ast.values;

import java.util.HashMap;

abstract public class ValueNode {
    public abstract Double getValue(HashMap<String, Double> heap);
}
