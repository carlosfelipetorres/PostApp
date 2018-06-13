package com.carlostorres.test.cleanarchitecture.util.bus.observers;


public abstract class IntegerBusObserver extends BusObserver<Integer> {
    public IntegerBusObserver() {
        super(Integer.class);
    }
}