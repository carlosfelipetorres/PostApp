package com.carlostorres.test.cleanarchitecture.util.bus.observers;

public abstract class BooleanBusObserver extends
    com.carlostorres.test.cleanarchitecture.util.bus.observers.BusObserver<Boolean> {
    public BooleanBusObserver() {
        super(Boolean.class);
    }
}