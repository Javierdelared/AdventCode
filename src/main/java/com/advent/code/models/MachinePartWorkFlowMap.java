package com.advent.code.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MachinePartWorkFlowMap {

    private final Map<String, MachinePartWorkFlow> workFlowMap = new HashMap<>();
    public void add(MachinePartWorkFlow machinePartWorkFlow) {
        workFlowMap.put(machinePartWorkFlow.name(), machinePartWorkFlow);
    }

    public MachinePart executeWorkflow(MachinePart machinePart, String output) {
        if (machinePart == null) {
            return null;
        }
        MachinePartWorkFlow currentWorkFlow = workFlowMap.get(output);
        for (MachinePartWorkFlowRule rule : currentWorkFlow.rules()) {
            String ruleOutput = rule.getFunction().apply(machinePart);
            if (ruleOutput != null) {
                if ("R".equals(ruleOutput)) {
                    return null;
                }
                if ("A".equals(ruleOutput)) {
                    return machinePart;
                } else {
                    return executeWorkflow(machinePart, ruleOutput);
                }
            }
        }
        return null;
    }

    public List<MachinePartRange> executeWorkflowRange(List<MachinePartRange> ranges, String output) {
        MachinePartWorkFlow currentWorkFlow = workFlowMap.get(output);
        List<MachinePartRange> approvedRanges = new ArrayList<>();
        List<MachinePartRange> currentRanges = ranges;
        for (MachinePartWorkFlowRule rule : currentWorkFlow.rules()) {
            MachinePartRange ruleRange = rule.getMachinePartRange();
            String ruleOutput = rule.getOutput();
            if (!"R".equals(ruleOutput)) {
                List<MachinePartRange> combinedRange = combineRanges(currentRanges, ruleRange);
                if ("A".equals(ruleOutput)) {
                    approvedRanges.addAll(combinedRange);
                } else {
                    approvedRanges.addAll(executeWorkflowRange(combinedRange, ruleOutput));
                }
            }
            currentRanges = combineRanges(currentRanges, ruleRange.reverse(rule.getField()));
        }
        return approvedRanges;
    }

    private static List<MachinePartRange> combineRanges(List<MachinePartRange> ranges, MachinePartRange returnRange) {
        return ranges.stream().map(r -> r.combine(returnRange)).toList();
    }
}
