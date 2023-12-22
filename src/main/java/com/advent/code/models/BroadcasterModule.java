package com.advent.code.models;

import java.util.List;

public class BroadcasterModule extends CommunicationModule {

    public BroadcasterModule(String name, List<String> outputModules) {
        super(name, outputModules);
    }

    @Override
    public Boolean receivePulse(String inputModule, boolean pulse) {
        return pulse;
    }
}
