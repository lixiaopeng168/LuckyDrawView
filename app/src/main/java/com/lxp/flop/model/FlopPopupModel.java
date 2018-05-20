package com.lxp.flop.model;


import com.lxp.flop.custom.FlopTextView;

/**
 * Created by lxp  on 2018/5/7.
 */
public class FlopPopupModel {
    private FlopTextView.Builder builder;
    private boolean isStart;//为true是开始动画

    public FlopTextView.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(FlopTextView.Builder builder) {
        this.builder = builder;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }
}
