package com.carlostorres.test.cleanarchitecture.util.bus.observers;

public abstract class FloatBusObserver extends BusObserver<Float> {

    public FloatBusObserver() {
        super(Float.class);
    }
}
