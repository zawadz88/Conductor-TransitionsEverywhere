package com.github.zawadz88.conductor.sample.controller;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.RouterTransaction;
import com.github.zawadz88.conductor.sample.R;
import com.github.zawadz88.conductor.sample.changehandler.ArcFadeMoveChangeHandlerCompat;
import com.github.zawadz88.conductor.sample.util.BundleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copied from Conductor sample app, but showing only transitions
 */
public class TransitionDemoController extends ButterKnifeController {

    private static final String KEY_INDEX = "TransitionDemoController.index";

    private enum TransitionDemo {
        ARC_FADE("Arc/Fade Shared Element Transition Start", R.layout.controller_transition_demo_shared, R.color.colorAccent),
        ARC_FADE_RESET("Arc/Fade Shared Element Transition End", R.layout.controller_transition_demo, R.color.teal_500);

        private String title;
        private int layoutId;
        private int colorId;

        TransitionDemo(String title, @LayoutRes int layoutId, @ColorRes int colorId) {
            this.title = title;
            this.layoutId = layoutId;
            this.colorId = colorId;
        }

        private static TransitionDemo fromIndex(int index) {
            return TransitionDemo.values()[index];
        }
    }

    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.btn_next) FloatingActionButton btnNext;
    @BindView(R.id.transition_root) View containerView;

    private TransitionDemo transitionDemo;

    public TransitionDemoController(int index) {
        this(new BundleBuilder(new Bundle())
                .putInt(KEY_INDEX, index)
                .build());
    }

    public TransitionDemoController(Bundle args) {
        super(args);
        transitionDemo = TransitionDemo.fromIndex(args.getInt(KEY_INDEX));
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(transitionDemo.layoutId, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        View bgView = ButterKnife.findById(view, R.id.bg_view);
        if (transitionDemo.colorId != 0 && bgView != null) {
            bgView.setBackgroundColor(ContextCompat.getColor(getActivity(), transitionDemo.colorId));
        }

        final int nextIndex = transitionDemo.ordinal() + 1;
        int buttonColor = 0;
        if (nextIndex < TransitionDemo.values().length) {
            buttonColor = TransitionDemo.fromIndex(nextIndex).colorId;
        }
        if (buttonColor == 0) {
            buttonColor = TransitionDemo.fromIndex(0).colorId;
        }

        btnNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), buttonColor)));
        tvTitle.setText(transitionDemo.title);
    }

    @OnClick(R.id.btn_next)
    void onNextClicked() {
        final int nextIndex = transitionDemo.ordinal() + 1;

        if (nextIndex < TransitionDemo.values().length) {
            getRouter().pushController(getRouterTransaction(nextIndex));
        } else {
            getRouter().popToRoot();
        }
    }

    private ControllerChangeHandler getChangeHandler() {
        return new ArcFadeMoveChangeHandlerCompat();
    }

    private static RouterTransaction getRouterTransaction(int index) {
        TransitionDemoController toController = new TransitionDemoController(index);

        return RouterTransaction.with(toController)
                .pushChangeHandler(toController.getChangeHandler())
                .popChangeHandler(toController.getChangeHandler());
    }

}
