package com.advent.code.models;

import java.util.List;

public class FlipFlopModule extends CommunicationModule {

    private boolean state = false;

    public FlipFlopModule(String name, List<String> outputModules) {
        super(name, outputModules);
    }

    @Override
    public Boolean receivePulse(String inputModule, boolean pulse) {
        if (pulse) {
            return null;
        }
        state = !state;
        return state;
    }
}
