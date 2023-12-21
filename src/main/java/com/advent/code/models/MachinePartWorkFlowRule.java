package com.advent.code.models;

import com.advent.code.exception.ServiceException;

import java.util.function.Function;

import static com.advent.code.models.MachinePartRange.*;

public class MachinePartWorkFlowRule {

    private final String rule;
    private Function<MachinePart, String> function;
    private MachinePartRange machinePartRange;
    private String field;
    private String symbol;
    private int value;
    private String output;

    public String getOutput() {
        return output;
    }

    public String getField() {
        return field;
    }

    public MachinePartWorkFlowRule(String rule) {
        this.rule = rule;
        calculateParams();
    }

    public void calculateParams() {
        String[] ruleArr;
        if (rule.contains("<")) {
            symbol = "<";
            ruleArr = rule.split("<");
        } else if (rule.contains(">")) {
            symbol = ">";
            ruleArr = rule.split(">");
        } else {
            function = mp -> rule;
            output = rule;
            machinePartRange = rule.equals("R") ? EMPTY_RANGE : FULL_RANGE;
            return ;
        }
        field = ruleArr[0];
        String[] ruleSubArr = ruleArr[1].split(":");
        value = Integer.parseInt(ruleSubArr[0]);
        output = ruleSubArr[1];
    }

    public Function<MachinePart, String> getFunction() {
        if (function == null) {
            function = calculateFunction();
        }
        return function;
    }

    public MachinePartRange getMachinePartRange() {
        if (machinePartRange == null) {
            machinePartRange = calculateRange();
        }
        return machinePartRange;
    }

    private Function<MachinePart, String> calculateFunction() {
        if (function != null) {
            return function;
        }
        switch (symbol) {
            case "<" -> {
                return switch (field) {
                    case "x" -> mp -> mp.x() < value ? output : null;
                    case "m" -> mp -> mp.m() < value ? output : null;
                    case "a" -> mp -> mp.a() < value ? output : null;
                    case "s" -> mp -> mp.s() < value ? output : null;
                    default -> throw new ServiceException("Rule field not found. Rule:" + rule);
                };
            }
            case ">" -> {
                return switch (field) {
                    case "x" -> mp -> mp.x() > value ? output : null;
                    case "m" -> mp -> mp.m() > value ? output : null;
                    case "a" -> mp -> mp.a() > value ? output : null;
                    case "s" -> mp -> mp.s() > value ? output : null;
                    default -> throw new ServiceException("Rule field not found. Rule:" + rule);
                };
            }
        }
        throw new ServiceException("Function not parsed. Rule: " + rule);
    }

    private MachinePartRange calculateRange() {
        return switch (symbol) {
            case "<" -> build(field, MIN_RANGE, value);
            case ">" -> build(field, value + 1, MAX_RANGE);
            default ->  throw new ServiceException("Range not parsed. Rule: " + rule);
        };
    }
}
