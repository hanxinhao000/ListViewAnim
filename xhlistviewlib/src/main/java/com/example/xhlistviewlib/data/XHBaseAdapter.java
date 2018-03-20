package com.example.xhlistviewlib.data;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * XINHAO_ADAPTER
 */

public abstract class XHBaseAdapter<T> extends BaseAdapter{

    private List<T> list;

    private ArrayList<BooleanClass> arrayList;
    private ObjectAnimator addup;


    public XHBaseAdapter(List<T> list){
        this.list = list;
        arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BooleanClass booleanClass = new BooleanClass();
            booleanClass.setShow(false);
            arrayList.add(booleanClass);
        }

    }

    @Override
    public int getCount() {
        return list == null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        XHViewHolder xhViewHolder;


        if(convertView == null){
            xhViewHolder = getXHViewHolder();
            convertView = xhViewHolder.getView();
            convertView.setTag(xhViewHolder);
        }else{
            xhViewHolder = (XHViewHolder) convertView.getTag();
        }

        if(arrayList.get(position).isShow()){
            xhViewHolder.getViewTwo().setVisibility(View.VISIBLE);
            if(arrayList.get(position).getPosition() == -1){
                startAnim(xhViewHolder.getViewTwo());
                arrayList.get(position).setPosition(position);
                arrayList.get(position).setClick(true);
            }

        }else{
           // closeAnim(xhViewHolder.getViewTwo());
            if(arrayList.get(position).getPosition() != -1) {
                closeAnim(xhViewHolder.getViewTwo());
                arrayList.get(position).setPosition(-1);
                arrayList.get(position).setClick(false);

            }else if(arrayList.get(position).isClick()){
                xhViewHolder.getViewTwo().setVisibility(View.VISIBLE);
                //if(position == 0)
            }else{
                xhViewHolder.getViewTwo().setVisibility(View.GONE);
            }
            //xhViewHolder.getViewTwo().setVisibility(View.GONE);
        }

        initView(position,xhViewHolder,list.get(position));

        if(arrayList.get(position).getPosition() != -1 && arrayList.get(position).isClick()){
            //xhViewHolder.getViewTwo().setVisibility(View.GONE);
            xhViewHolder.getViewTwo().setVisibility(View.VISIBLE);



        }


        Log.e("数组集合", "getView: " + arrayList.get(position).getPosition() + " : " + position + arrayList.get(position).isClick() + " : isShow() :" +arrayList.get(position).isShow());

        return convertView;
    }


    public ArrayList<BooleanClass> getArrayList(){
        return arrayList;
    }

    //设置动画 展开

    private void startAnim(View view){


        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w,h);

        ViewObject viewObject = new ViewObject(view);
        ObjectAnimator addup = ObjectAnimator.ofFloat(viewObject, "addup", 0,view.getMeasuredHeight());
        addup.setDuration(300);
        addup.start();

        Log.e("坐标", "startAnim: " +  view.getMeasuredHeight());

    }

    //设置动画 闭合

    private void closeAnim(final View view){


        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w,h);


        ViewObject viewObject = new ViewObject(view);
        final int measuredHeight = view.getMeasuredHeight();



        addup = ObjectAnimator.ofFloat(viewObject, "addup", measuredHeight,0);
        addup.setDuration(300);
        addup.start();
        addup.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = measuredHeight;
                view.setLayoutParams(layoutParams);
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    public abstract XHViewHolder getXHViewHolder();

    public abstract void initView(int position,XHViewHolder xhViewHolder,T t);





}
