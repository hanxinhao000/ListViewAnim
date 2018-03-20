package com.example.xhlistviewlib;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.xhlistviewlib.data.BooleanClass;
import com.example.xhlistviewlib.data.ViewObject;
import com.example.xhlistviewlib.data.XHBaseAdapter;
import com.example.xhlistviewlib.data.XHViewHolder;

import java.util.ArrayList;

/**
 * XINHAO_ListView控件
 */

public class XHListView extends ViewGroup{

    private Context context;
    private XHListViewAndroid listView;
    private XHBaseAdapter xhBaseAdapter;

    public static Handler handler;
    //保存上一个View
    private View upView;
    public XHListView(Context context) {
        super(context);
        initContext(context);
    }

    public XHListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContext(context);
    }

    public XHListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initContext(context);
    }
    //初始化
    private void initContext(Context context){
        this.context = context;
        handler = new Handler();
        ViewGroup.LayoutParams vl = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        listView = new XHListViewAndroid(context);
        listView.setSelector(android.R.color.transparent);
        listView.setLayoutParams(vl);
        addView(listView);



    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        getChildAt(0).layout(l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChild(getChildAt(0),widthMeasureSpec,heightMeasureSpec);
    }


    public <T> void setXHAdapter(XHBaseAdapter<T> xhAdapter){
        xhBaseAdapter = xhAdapter;
        startSetAdapter();
    }


    private int startX = 0;
    private int startY = 0;


    private void startSetAdapter(){

        listView.setAdapter(xhBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                ArrayList<BooleanClass> arrayList = xhBaseAdapter.getArrayList();
            /*    if(arrayList.get(position).getPosition() == position){
                    arrayList.get(position).setShow(false);
                    arrayList.get(position).setPosition(-1);
                    //关闭
                }else{
                    arrayList.get(position).setShow(true);
                    arrayList.get(position).setPosition(position);
                    //展开
                }
*/
         /*   if(upView == view){
                XHViewHolder tag = (XHViewHolder) view.getTag();
                tag.getViewTwo().setVisibility(GONE);
                Log.e("执行书序", "onItemClick: " + "1" );
                upView = null;
                return;
            }

            if(upView!= null){
                XHViewHolder tag = (XHViewHolder) upView.getTag();
                tag.getViewTwo().setVisibility(GONE);
                Log.e("执行书序", "onItemClick: " + "2" );

            }

            //开始执行操作本次View
                XHViewHolder tag = (XHViewHolder) view.getTag();
                tag.getViewTwo().setVisibility(VISIBLE);


                //保存上一个View
                upView = view;*/

            //表示已经点击过了
            if(arrayList.get(position).isShow()){
                visiAll();
            }else{
                visiAll();
                arrayList.get(position).setShow(!arrayList.get(position).isShow());
            }
                xhBaseAdapter.notifyDataSetChanged();
            }



        });


    }


    private void visiAll(){
        ArrayList<BooleanClass> arrayList = xhBaseAdapter.getArrayList();

        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).setShow(false);
            arrayList.get(i).setClick(false);
          //  arrayList.get(i).setPosition(-1);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
