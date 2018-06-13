package com.carlostorres.test.cleanarchitecture.util.bus.observers;

public abstract class ResetButtonObserver extends BusObserver<ResetButtonObserver.ResetButtonPressed> {
    public ResetButtonObserver() {
        super(ResetButtonPressed.class);
    }

    public static class ResetButtonPressed {
    }
}