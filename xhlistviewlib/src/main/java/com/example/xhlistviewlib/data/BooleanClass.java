package com.example.xhlistviewlib.data;

/**
 * Created by XINHAO_HAN on 2018/3/19.
 */

public class BooleanClass {

    private boolean isShow = false;

    private int position = -1;
    //当前是否点击
    private boolean isClick = false;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
