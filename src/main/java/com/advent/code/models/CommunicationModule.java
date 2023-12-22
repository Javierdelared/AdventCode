package com.advent.code.models;

import com.advent.code.exception.ServiceException;

import java.util.List;

public class CommunicationModule implements Cloneable {

    private final String name;

    private final List<String> outputModules;

    public String getName() {
        return name;
    }

    public List<String> getOutputModules() {
        return outputModules;
    }

    public void setInputModules(List<String> inputModules) {
    }

    public CommunicationModule(String name, List<String> outputModules) {
        this.name = name;
        this.outputModules = outputModules;
    }

    public Boolean receivePulse(String inputModule, boolean pulse) {
        return null;
    }

    public static CommunicationModule parse(String name, List<String> outputModules) {
        CommunicationModule module;
        if (name.charAt(0) == '%') {
            name = name.substring(1);
            module = new FlipFlopModule(name, outputModules);
        } else if (name.charAt(0) == '&') {
            name = name.substring(1);
            module = new ConjunctionModule(name, outputModules);
        } else {
            module = new BroadcasterModule(name, outputModules);
        }
        return module;
    }

    @Override
    public CommunicationModule clone() {
        try {
            return (CommunicationModule) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new ServiceException("Object cannot be cloned");
        }
    }
}
