package com.advent.code.models;

import java.util.Arrays;
import java.util.List;

public record MachinePartWorkFlow(String name, List<MachinePartWorkFlowRule> rules) {

    public static MachinePartWorkFlow parseWorkflow(String line) {
        String[] lineArr = line.split("\\{");
        String[] rules = lineArr[1].substring(0, lineArr[1].length() - 1).split(",");
        return new MachinePartWorkFlow(lineArr[0], Arrays.stream(rules).map(MachinePartWorkFlowRule::new).toList());
    }
}
