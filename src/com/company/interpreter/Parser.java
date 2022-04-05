package com.company.interpreter;

import com.company.ast.DeclareNode;
import com.company.ast.ProgramNode;
import com.company.ast.statments.*;
import com.company.ast.values.NumberNode;
import com.company.ast.values.PredicateNode;
import com.company.ast.values.ValueNode;
import com.company.ast.values.VarNode;
import com.company.interpreter.token.Token;
import com.company.interpreter.token.TokenType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.interpreter.token.TokenType.*;

public class Parser {
    private final List<Token> tokens;
    private int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private void error(String message) {
        if (pos < tokens.size()) {
            Token t = tokens.get(pos);
            throw new RuntimeException(message + " in line " + t.lineNum + " position "+ t.posInLine);
        } else {
            throw new RuntimeException(message + " in the end of file");
        }
    }

    private Token match(TokenType... expected) {
        if (pos < tokens.size()) {
            Token curr = tokens.get(pos);
            if (Arrays.asList(expected).contains(curr.type)) {
                pos++;
                return curr;
            }
        }
        return null;
    }

    private Token require(TokenType... expected) {
        Token t = match(expected);
        if (t == null)
            error("Expected " + Arrays.toString(expected));
        return t;
    }

    private ValueNode parseElem() {
        Token num = match(NUMBER);
        if (num != null)
            return new NumberNode(num);
        Token id = match(VARIABLE);
        if (id != null)
            return new VarNode(id);
        error("Expected number or variable");
        return null;
    }

    private PredicateNode parsePredicate() {
        ValueNode lVal = parseElem();
        Token pr = require(PREDICATE);
        ValueNode rVal = parseElem();
        return new PredicateNode(pr, lVal, rVal);
    }

    private StatementNode parsePrint() {
        ValueNode var = parseElem();
        require(SEMICOLON);
        return new PrintOpNode(var);
    }

    private StatementNode parseIncDec(VarNode var) {
        Token incDecT = require(INCREMENT, DECREMENT);
        require(SEMICOLON);
        return new IncDecOpNode(var, incDecT);
    }

    private DeclareNode parseDeclare() {
        Token declare = match(DECLARE);
        if (declare != null) {
            List<AssigmentOpNode> assigmentOpNodeList = new ArrayList<>();
            AssigmentOpNode node;
            while ((node = parseAssigment()) != null) {
                assigmentOpNodeList.add(node);
            }
            return new DeclareNode(declare, assigmentOpNodeList);
        }
        return null;
    }

    public ProgramNode parse() {
        DeclareNode declareNode = parseDeclare();
        require(BEGIN);
        List<StatementNode> statementNodes = new ArrayList<>();

        StatementNode node;
        while ((node = parseStatement()) != null) {
            statementNodes.add(node);
        }

        require(END);
        require(SEMICOLON);
        return new ProgramNode(declareNode, statementNodes);
    }

    private AssigmentOpNode parseAssigment() {
        Token variable = match(VARIABLE);
        if (variable != null) {
            require(ASSIGNMENT);
            Token value = match(NUMBER);
            if (value != null) {
                require(SEMICOLON);
                return new AssigmentOpNode(variable, new NumberNode(value));
            }
        }
        return null;
    }

    private StatementNode parseStatement() {
        Token actionT = match(PRINT, VARIABLE, WHILE);
        if (actionT == null) {
            return null;
        }
        switch (actionT.type) {
            case PRINT: {
                return parsePrint();
            }
            case VARIABLE: {
                return parseIncDec(new VarNode(actionT));
            }
            case WHILE: {
                return parseWhile();
            }
            default:
                return null;
        }
    }

    private WhileOpNode parseWhile() {
        PredicateNode predicate = parsePredicate();
        require(DO);
        List<StatementNode> actions = new ArrayList<>();

        StatementNode action;
        while ((action = parseStatement()) != null) {
            actions.add(action);
        }

        require(DONE);
        require(SEMICOLON);
        return new WhileOpNode(predicate, actions);
    }
}
