package com.carlostorres.test.cleanarchitecture.util.bus.observers;

public abstract class CallServiceButtonObserver extends
    BusObserver<CallServiceButtonObserver.CallServiceButtonPressed> {
    public CallServiceButtonObserver() {
        super(CallServiceButtonPressed.class);
    }

    public static class CallServiceButtonPressed {
    }
}