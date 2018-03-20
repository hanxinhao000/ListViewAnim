package com.example.xhlistviewlib;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by XINHAO_HAN on 2018/3/19.
 */

public class XHListViewAndroid extends ListView {

    public XHListViewAndroid(Context context) {
        super(context);
    }

    public XHListViewAndroid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XHListViewAndroid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int startX = 0;
    private int startY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean isTrue = false;
      //  Log.e("----", "onInterceptTouchEvent: "  + ev.getAction() );
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:

                    startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();

                int endY = (int) ev.getY();


                int midY = endY - startY;

                int mid = endX - startX;

              //  Log.e("----", "onInterceptTouchEvent: " + mid  +": midY :" + midY);

                if(Math.abs(midY) > Math.abs(mid)){
                    Log.e("---", "onInterceptTouchEvent: " + "上下" );
                    isTrue =  true;

                }else{
                    Log.e("---", "onInterceptTouchEvent: " + "左右" );
                    isTrue =  false;
                }


                break;




            case MotionEvent.ACTION_UP:
                isTrue = false;
                startX = 0;
                break;

        }
        return isTrue;
    }

}
