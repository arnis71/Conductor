package com.arnis.konductor.demo.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.arnis.konductor.Controller;
import com.arnis.konductor.demo.R;
import com.arnis.konductor.demo.controllers.base.BaseController;

import butterknife.BindView;
import butterknife.OnClick;

public class TargetTitleEntryController extends BaseController {

    public interface TargetTitleEntryControllerListener {
        void onTitlePicked(String option);
    }

    @BindView(R.id.edit_text) EditText editText;

    public <T extends Controller & TargetTitleEntryControllerListener> TargetTitleEntryController(T targetController) {
        setTargetController(targetController);
    }

    public TargetTitleEntryController() { }

    @Override
    protected void onDetach(@NonNull View view) {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_target_title_entry, container, false);
    }

    @Override
    protected String getTitle() {
        return "Target Controller Demo";
    }

    @OnClick(R.id.btn_use_title) void optionPicked() {
        Controller targetController = getTargetController();
        if (targetController != null) {
            ((TargetTitleEntryControllerListener)targetController).onTitlePicked(editText.getText().toString());
            getRouter().popController(this);
        }
    }
}
