package com.bluelinelabs.conductor.autodispose;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.bluelinelabs.conductor.Controller;
import com.uber.autodispose.OutsideLifecycleException;

import io.reactivex.subjects.BehaviorSubject;

public class ControllerLifecycleSubjectHelper {
    private ControllerLifecycleSubjectHelper() { }

    @NonNull
    public static BehaviorSubject<ControllerEvent> create(Controller controller) {
        ControllerEvent initialState;
        if (controller.isBeingDestroyed() || controller.isDestroyed()) {
            throw new OutsideLifecycleException("Cannot bind to Controller lifecycle when outside of it.");
        } else if (controller.isAttached()) {
            initialState = ControllerEvent.ATTACH;
        } else if (controller.getView() != null) {
            initialState = ControllerEvent.CREATE_VIEW;
        } else if (controller.getActivity() != null) {
            initialState = ControllerEvent.CONTEXT_AVAILABLE;
        } else {
            initialState = ControllerEvent.CREATE;
        }

        final BehaviorSubject<ControllerEvent> subject = BehaviorSubject.createDefault(initialState);

        controller.addLifecycleListener(new Controller.LifecycleListener() {
            @Override
            public void onContextAvailable(@NonNull Controller controller, @NonNull Context context) {
                subject.onNext(ControllerEvent.CONTEXT_AVAILABLE);
            }

            @Override
            public void preCreateView(@NonNull Controller controller) {
                subject.onNext(ControllerEvent.CREATE_VIEW);
            }

            @Override
            public void preAttach(@NonNull Controller controller, @NonNull View view) {
                subject.onNext(ControllerEvent.ATTACH);
            }

            @Override
            public void preDetach(@NonNull Controller controller, @NonNull View view) {
                subject.onNext(ControllerEvent.DETACH);
            }

            @Override
            public void preDestroyView(@NonNull Controller controller, @NonNull View view) {
                subject.onNext(ControllerEvent.DESTROY_VIEW);
            }

            @Override
            public void onContextUnavailable(@NonNull Controller controller) {
                subject.onNext(ControllerEvent.CONTEXT_UNAVAILABLE);
            }

            @Override
            public void preDestroy(@NonNull Controller controller) {
                subject.onNext(ControllerEvent.DESTROY);
            }
        });

        return subject;
    }
}
