package com.arnis.konductor.archlifecycle;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;

import com.arnis.konductor.RestoreViewOnCreateController;

public abstract class LifecycleRestoreViewOnCreateController extends RestoreViewOnCreateController implements LifecycleRegistryOwner {

    private final ControllerLifecycleRegistryOwner lifecycleRegistryOwner = new ControllerLifecycleRegistryOwner(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistryOwner.getLifecycle();
    }

}
