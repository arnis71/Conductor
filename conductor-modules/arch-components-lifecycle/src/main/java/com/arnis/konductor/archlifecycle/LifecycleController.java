package com.arnis.konductor.archlifecycle;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arnis.konductor.Controller;

public abstract class LifecycleController extends Controller implements LifecycleRegistryOwner {

    private final ControllerLifecycleRegistryOwner lifecycleRegistryOwner = new ControllerLifecycleRegistryOwner(this);

    public LifecycleController() {
        super();
    }

    public LifecycleController(@Nullable Bundle args) {
        super(args);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistryOwner.getLifecycle();
    }

}
