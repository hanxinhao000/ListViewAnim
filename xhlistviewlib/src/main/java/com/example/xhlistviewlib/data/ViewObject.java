package com.example.xhlistviewlib.data;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * XINHAO_HAN View包装类
 */

public class ViewObject {

    private View view;

    public ViewObject(View view){
        this.view = view;
    }


    /**
     * 增加类
     * @param move
     */
    public void setAddup(float move){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) move;
        view.setLayoutParams(layoutParams);

        Log.e("坐标", "setAddup: " + move );
    }

}
