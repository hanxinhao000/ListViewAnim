package com.example.xhlistviewlib.data;

import android.view.View;
import android.widget.LinearLayout;

import com.example.xhlistviewlib.R;

/**
 * XINHAO_VIEWHOlder
 */

public class XHViewHolder {

    private View view;
    private View baseView;
    private LinearLayout layout;
    private View viewTwo;

    public XHViewHolder(View view,View viewTwo){
        this.view = view;
        this.viewTwo = viewTwo;

        baseView = View.inflate(view.getContext(), R.layout.base_layout,null);
        layout = baseView.findViewById(R.id.layout);
       if(layout.getChildCount() <=0) {
           layout.addView(view);
           layout.addView(viewTwo);

       }
        //viewTwo.setVisibility(View.GONE);
    }

    public View getViewTwo(){

        return viewTwo;
    }





    public View getView(){
        return baseView;
    }

    public View findViewById(int id){
        return view.findViewById(id);
    }
}
