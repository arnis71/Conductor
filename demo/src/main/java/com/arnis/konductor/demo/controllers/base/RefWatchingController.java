package com.arnis.konductor.demo.controllers.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.arnis.konductor.ControllerChangeHandler;
import com.arnis.konductor.ControllerChangeType;
import com.arnis.konductor.demo.DemoApplication;

public abstract class RefWatchingController extends ButterKnifeController {

    protected RefWatchingController() { }
    protected RefWatchingController(Bundle args) {
        super(args);
    }

    private boolean hasExited;

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (hasExited) {
            DemoApplication.refWatcher.watch(this);
        }
    }

    @Override
    protected void onChangeEnded(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        super.onChangeEnded(changeHandler, changeType);

        hasExited = !changeType.isEnter;
        if (isDestroyed()) {
            DemoApplication.refWatcher.watch(this);
        }
    }
}
