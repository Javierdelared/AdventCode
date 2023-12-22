package com.advent.code.models;

import java.util.*;

public class ConjunctionModule extends CommunicationModule {

    private final Map<String, Boolean> memoryMap = new HashMap<>();

    public ConjunctionModule(String name, List<String> outputModules) {
        super(name, outputModules);
    }

    @Override
    public void setInputModules(List<String> inputModules) {
        inputModules.forEach(i -> memoryMap.put(i, false));
    }

    @Override
    public Boolean receivePulse(String inputModule, boolean pulse) {
        memoryMap.put(inputModule, pulse);
        return memoryMap.values().stream().anyMatch(p -> !p);
    }
}
