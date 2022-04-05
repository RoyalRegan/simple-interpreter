package com.company.ast.values;

import com.company.interpreter.token.Token;

import java.util.HashMap;

public class PredicateNode extends ValueNode {
    public final Token predicateT;
    public final ValueNode leftVal;
    public final ValueNode rightVal;

    public PredicateNode(Token predicateT, ValueNode leftVal, ValueNode rightVal) {
        this.predicateT = predicateT;
        this.leftVal = leftVal;
        this.rightVal = rightVal;
    }

    @Override
    public Double getValue(HashMap<String, Double> heap) {
        switch (predicateT.text) {
            case "=": {
                if (leftVal.getValue(heap).equals(rightVal.getValue(heap))) {
                    return 1.0;
                }
                break;
            }

            case ">": {
                if (leftVal.getValue(heap) > rightVal.getValue(heap)) {
                    return 1.0;
                }
                break;
            }

            case "<": {
                if (leftVal.getValue(heap) < rightVal.getValue(heap)) {
                    return 1.0;
                }
                break;
            }

            case ">=": {
                if (leftVal.getValue(heap) >= rightVal.getValue(heap)) {
                    return 1.0;
                }
                break;
            }

            case "<=": {
                if (leftVal.getValue(heap) <= rightVal.getValue(heap)) {
                    return 1.0;
                }
                break;
            }
        }
        return 0.0;
    }
}
