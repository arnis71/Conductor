package com.arnis.konductor.demo.controllers;

import android.annotation.TargetApi;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arnis.konductor.demo.R;
import com.arnis.konductor.demo.changehandler.ScaleFadeChangeHandler;
import com.arnis.konductor.demo.controllers.base.BaseController;
import com.arnis.konductor.demo.widget.ElasticDragDismissFrameLayout;
import com.arnis.konductor.demo.widget.ElasticDragDismissFrameLayout.ElasticDragDismissCallback;

@TargetApi(VERSION_CODES.LOLLIPOP)
public class DragDismissController extends BaseController {

    private final ElasticDragDismissCallback dragDismissListener = new ElasticDragDismissCallback() {
        @Override
        public void onDragDismissed() {
            overridePopHandler(new ScaleFadeChangeHandler());
            getRouter().popController(DragDismissController.this);
        }
    };

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_drag_dismiss, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        ((ElasticDragDismissFrameLayout)view).addListener(dragDismissListener);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);

        ((ElasticDragDismissFrameLayout)view).removeListener(dragDismissListener);
    }

    @Override
    protected String getTitle() {
        return "Drag to Dismiss";
    }
}
