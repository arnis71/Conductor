package com.arnis.konductor.support.util;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arnis.konductor.Controller;

public class TestController extends Controller {

    @NonNull @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return new FrameLayout(inflater.getContext());
    }

}
