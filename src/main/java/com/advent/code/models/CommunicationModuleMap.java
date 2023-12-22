package com.advent.code.models;

import java.util.*;

public class CommunicationModuleMap {

    private final Map<String, CommunicationModule> communicationModuleMap;
    private long lowPulses = 0;
    private long highPulses = 0;
    private final Map<String, Integer> highPulseCycle = new HashMap<>();
    private final  Map<String, Integer> highPulseStart = new HashMap<>();

    public long getLowPulses() {
        return lowPulses;
    }

    public long getHighPulses() {
        return highPulses;
    }

    public Map<String, Integer> getHighPulseStart() {
        return highPulseStart;
    }

    public CommunicationModuleMap(Map<String, CommunicationModule> communicationModuleMap) {
        this.communicationModuleMap = communicationModuleMap;
    }

    public void sendPulses(int cycle, String inputModule, List<String> outputModulesPulses, boolean pulse) {
        if (pulse) {
            highPulses += outputModulesPulses.size();
        } else {
            lowPulses += outputModulesPulses.size();
        }
        Map<String, List<String>> newOutputModules = new HashMap<>();
        Map<String, Boolean> newOutputPulses = new HashMap<>();
        outputModulesPulses.stream().filter(k -> communicationModuleMap.get(k) != null).forEach(k -> {
            Boolean newPulse = communicationModuleMap.get(k).receivePulse(inputModule, pulse);
            if (newPulse != null) {
                savePulse(cycle, k, newPulse);
                newOutputModules.put(k, communicationModuleMap.get(k).getOutputModules());
                newOutputPulses.put(k, newPulse);
            }
        });
        newOutputModules.forEach((k, v) -> sendPulses(cycle, k, v, newOutputPulses.get(k)));
    }

    private void savePulse(int cycle, String k, Boolean pulse) {
        if (!pulse) {
            return;
        }
        if (highPulseStart.get(k) == null) {
            highPulseStart.put(k, cycle);
        } else if (highPulseCycle.get(k) == null) {
            highPulseCycle.put(k, cycle - highPulseStart.get(k));
        }
    }

    public void cleanCounters() {
        highPulses = 0;
        lowPulses = 0;
    }

    public static CommunicationModuleMap parse(List<String> lines) {
        Map<String, CommunicationModule> map = new HashMap<>();
        Map<String, List<String>> inputModules = new HashMap<>();
        lines.forEach(line -> {
            String[] lineArr = line.split(" -> ");
            List<String> outputModules = Arrays.stream(lineArr[1].split(", ")).toList();
            CommunicationModule module = CommunicationModule.parse(lineArr[0], outputModules);
            map.put(module.getName(), module);
            outputModules.forEach(o -> {
                inputModules.computeIfAbsent(o, k -> new ArrayList<>());
                inputModules.get(o).add(module.getName());
            });
        });
        map.values().forEach(m -> m.setInputModules(inputModules.get(m.getName())));
        return new CommunicationModuleMap(map);
    }

    public CommunicationModuleMap copy() {
        Map<String, CommunicationModule> map = new HashMap<>();
        for (CommunicationModule communicationModule : communicationModuleMap.values()) {
            map.put(communicationModule.getName(), communicationModule.clone());
        }
        return new CommunicationModuleMap(map);
    }
}
