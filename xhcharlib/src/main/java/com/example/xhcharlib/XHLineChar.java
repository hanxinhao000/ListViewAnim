package com.example.xhcharlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

/**
 * 图标控件  XINHAO_HAN
 */

public class XHLineChar extends View {

    private Context context;

    //最大值
    private float max = 0;

    //最小值
    private float min = 0;
    //控件高度
    private int viewH = 0;

    //控件宽度

    private int viewW = 0;

    private List<XHData> list;

    private Paint paint;

    //宽的每等份
    private float midW = 0;

    //高的每等分
    private float midH = 0;

    //横向为60各等分
    private float lineW = (50 + 100);

    //滚动器
    private Scroller scroller;

    //保存点的分布
    private ArrayList<DrawData> arrayLis;
    private float move = 30000;
    private float moveL;
    private float moveR;

    public XHLineChar(Context context) {
        super(context);
        initView(context);
    }

    public XHLineChar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XHLineChar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 开始初始化
     */

    private void initView(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        paint.setStrokeWidth(5);
        arrayLis = new ArrayList<>();
        scroller = new Scroller(context);
        lineNumArry = new ArrayList<>();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewH = h;
        viewW = w;
        Log.e("每等份的值", "h" + h);
        maxNum();
    }


    //获取最高抵值

    public void setMaxTable(float max) {
        this.max = max;
        startDraw();
    }

    //获取最低值

    public void setMinTable(float min) {
        this.min = min;
        startDraw();

    }


    private void startDraw() {


        if (max != 0 && min != 0)
            invalidate();

    }

    //计算等分值

    private void maxNum() {



        int i = (viewH - 200) / 18;

        // float midDown = 100 / 18;



        for (int j = 0; j < 18 ; j++) {

            if(j == 4)
                viewHigh = (i * j) + 110;

            if(j == 12)
                viewMin = (i * j) + 110;


        }

        midH = (viewHigh - viewMin) / (max - min);

        if (midH < 0) {
            midH *= -1;
        }


        // Log.e("每等份的值", "maxNum: " + midH + "  viewH:" + viewH);

        maxDian();
    }

    //闯入数据集合
    public void setDataArray(List<XHData> list) {

        this.list = list;


    }


    private int shuxianIndex = 120;

    private int henxianIndexstartX = 120;
    private int henxianIndexEndX = viewW;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        henxianIndexEndX = viewW;

        paint.setColor(Color.parseColor("#35ADA7"));


        //画竖线
        canvas.drawLine(shuxianIndex, 50, shuxianIndex, viewH - 100, paint);
        //画横线
        canvas.drawLine(henxianIndexstartX, viewH - 100, henxianIndexEndX, viewH - 100, paint);

        drawD(canvas);

        drawLine(canvas);

        drawCR(canvas);

        drawD(canvas);
        drawHLLine(canvas);
        //画刻度线
        drawLineNum(canvas);
        paint.setColor(Color.parseColor("#35ADA7"));
       // canvas.drawText("" + max, 50, 100, paint);
        //canvas.drawText("" + min, 50, viewH - 100, paint);


    }


    private float midLineNum = 0;
    //计算刻度线

    private void numLine(){

        float max = 0.0f;
        float min = 0.0f;
        max = list.get(0).getData();
        min = list.get(0).getData();
        for (int i = 0; i < list.size(); i++) {

            if(max< list.get(i).getData()){
                max = list.get(i).getData();
            }

            if(min > list.get(i).getData()){
                min = list.get(i).getData();
            }



        }

        midLineNum = max / min;



        float midNum = ((max - min) / 8);
        lineNumArry.add( min + "");
        for (int i = 1; i <=8 ; i++) {
            lineNumArry.add( formatNum((i * (midNum)) +min +  ""));









        }


        Log.e("大笑之", "numLine: " + "最大值:" + max + ":最小值:" + min );

    }

    //格式化小数点
    private String formatNum(String num){


        String substring = num.substring(0, num.indexOf(".") +2);



        return substring;
    }

    private ArrayList<String> lineNumArry;

    private void drawHLLine(Canvas canvas) {

        if (hLine != 0 && lLine != 0) {
            paint.setColor(Color.parseColor("#55F54131"));
            canvas.drawLine(shuxianIndex, (max - hLine) * midH + viewHigh, shuxianIndex + 30000, (max - hLine) * midH + viewHigh, paint);
            //(((max - list.get(i).getData())) * midH) + 100

            canvas.drawLine(shuxianIndex, ((max - lLine) * midH) + viewHigh, shuxianIndex + 30000, ((max - lLine) * midH) + viewHigh, paint);

            //画字
/*
            canvas.drawText(hLine + "", 50, (max - hLine) * midH + 100, paint);
            canvas.drawText(lLine + "", 50, (max - lLine) * midH + 100, paint);*/
        }

    }

    //刻度线的最高坐标
    private int viewHigh = 0;
    //刻度线的最低坐标
    private int viewMin = 0;
    /**
     * 开始画刻度线
     */

    private float moveIndex_Line = 0;
    private void drawLineNum(Canvas canvas){

        paint.setColor(Color.parseColor("#35ADA7"));
         //canvas.drawLine(120, 100, 120, viewH - 100, paint);

        float i = (viewH - 200) / 18;

       // float midDown = 100 / 18;


        //画大刻度线

        for (int j = 0; j < 18 ; j++) {

            paint.setColor(Color.parseColor("#35ADA7"));
            canvas.drawLine(shuxianIndex,(i * j) + 110,shuxianIndex + 60,( i * j )+110 ,paint);

            if(j == 8){
                paint.setColor(Color.parseColor("#8835ADA7"));
                canvas.drawLine(shuxianIndex,(i * j) + 110,shuxianIndex + 30000,( i * j )+110 ,paint);
            }

            paint.setColor(Color.parseColor("#88000000"));
            if(j >=4 && j <=12){

                canvas.drawText(lineNumArry.get((lineNumArry.size() - 2) - (j - 5)),shuxianIndex - 110,(i * j) + 110,paint);

            }



        }

        //画小刻度线

        float minLine = i / 5;

       // float iMin = ((((viewH - 200) - i))) / 68.0f ;
        paint.setColor(Color.parseColor("#5535ADA7"));
        for (int j = 0; j < 85 ; j++) {


            canvas.drawLine(shuxianIndex,(minLine * j) + 111,shuxianIndex + 30,(minLine * j) + 111 ,paint);

        }



    }


    //计算点的位置

    private void maxDian() {

        for (int i = 0; i < list.size(); i++) {

            DrawData drawData = new DrawData();

            drawData.setDianX(lineW);

            drawData.setDianY((((max - list.get(i).getData())) * midH) + viewHigh);

            if(drawData.getDianX() > hLine &&drawData.getDianX() < lLine){
                drawData.setColor(Color.parseColor("#F54131"));
            }

            drawData.setNum(list.get(i).getData());
            drawData.setDate(list.get(i).getDate());

            drawData.setColor(list.get(i).getColor());
            arrayLis.add(drawData);
            lineW += 150;
        }

        numLine();
        invalidate();

    }

    //画点
    private void drawD(Canvas canvas) {

        boolean dataUser = list.get(0).isDataUser();
        if (dataUser) {

            paint.setColor(Color.parseColor("#35ADA7"));
            for (int i = 0; i < arrayLis.size(); i++) {
                paint.setColor(arrayLis.get(i).getColor());

                if(arrayLis.get(i).getDianY() > ((max - hLine) * midH + viewHigh)  )
                {

                    paint.setColor(Color.parseColor("#306CF5"));
                }

                if(arrayLis.get(i).getDianY() <  ((max - lLine) * midH + viewHigh)){
                    paint.setColor(Color.parseColor("#F54131"));
                }
                canvas.drawCircle(arrayLis.get(i).getDianX(), arrayLis.get(i).getDianY(), 10, paint);

                //开始画远点旁边的Text
                paint.setColor(Color.parseColor("#88000000"));
                if(arrayLis.get(i).getDianY() > ((max - hLine) * midH + viewHigh)  )
                {
                    paint.setColor(Color.parseColor("#306CF5"));
                }

                if(arrayLis.get(i).getDianY() <  ((max - lLine) * midH + viewHigh)){
                    paint.setColor(Color.parseColor("#F54131"));
                }
                paint.setTextSize(35);
                canvas.drawText(arrayLis.get(i).getNum() + "",arrayLis.get(i).getDianX() + 30,arrayLis.get(i).getDianY() + 5,paint);
                //画底部Text

                paint.setColor(Color.parseColor("#55000000"));

                String date = arrayLis.get(i).getDate();

                String[] split = date.split("/");


                paint.setColor(Color.parseColor("#35ADA7"));
                canvas.drawLine(arrayLis.get(i).getDianX() + 50,viewH - 100,arrayLis.get(i).getDianX() + 50,viewH -150,paint);

                paint.setColor(Color.parseColor("#55000000"));

                canvas.drawText(split[0], arrayLis.get(i).getDianX(), viewH - 60 , paint);
                canvas.drawText(split[1], arrayLis.get(i).getDianX() , viewH - 25 , paint);

            }

        } else {
            for (int i = 0; i < list.size(); i++) {
                canvas.drawCircle(list.get(i).getX(), list.get(i).getY(), 5, paint);

            }
        }


    }

    //开始－ ＋数值
    private float updateData(float num) {
        moveIndex_Line = num;
        for (int i = 0; i < arrayLis.size(); i++) {

            //arrayLis.get(i).setDianY(arrayLis.get(i).getDianY() + num);
            move = arrayLis.get(i).getDianX() + num;


            arrayLis.get(i).setDianX(move);
        }
        shuxianIndex+=num;
        henxianIndexEndX+=num;
        henxianIndexstartX+=num;
        return move;
    }

    private float hLine = 0;

    private float lLine = 0;

    //高底线
    public void setLineHOrLineW(float hLine, float lLine) {

        this.hLine = hLine;
        this.lLine = lLine;
        invalidate();

    }

    //画方块
    private void drawCR(Canvas canvas) {


        paint.setColor(Color.parseColor("#fAfAfA"));
        //canvas.drawRect(0, 0, 118, viewH, paint);

    }


    private int startX = 0;



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getX();

                break;

            case MotionEvent.ACTION_UP:

                if (move > moveL) {
                    moveLine((int) move, (int) moveL);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                int endY = (int) event.getX();
                int mid = endY - startX;



                //右边界
                moveR = lineW - (lineW - viewW) - 90;
                //左边界
                moveL = lineW;

                if (move + mid < moveR) {
                    return true;

                }
                //防止右边越界
              /*  if (move > moveR || mid > 0) {
                    updateData(mid);
                }
*/

                //防止左边越界

                if ((move + 150) < moveL || mid < 0) {
                    if ((move + 140) + mid > moveL && move != 30000)
                        return true;
                    updateData(mid);


                }




               /* if(move <(lineW - (lineW - viewW)) && mid < 0){
                    updateData(mid);
                }*/

               // Log.e("触摸数据:", "move: " + move + " : lineW : " + lineW + ": (lineW - (lineW - viewW)) :" + (lineW - (lineW - viewW)) + " : mid :" + mid + ":(move > moveL):" + (moveL - move));


                startX = endY;
                invalidate();
                break;

        }
        return true;
    }


    //还原线

    private void moveLine(int startLine, int endLine) {

        scroller.startScroll(startLine, 0, endLine - startLine, 0, 300);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (scroller.computeScrollOffset()) {


           // Log.e("数据看法", "computeScroll: " + scroller.getCurrX());
        }

        invalidate();
    }


    //画线
    private void drawLine(Canvas canvas) {
        paint.setColor(Color.parseColor("#35ADA7"));
        //画线
        boolean dataUser = list.get(0).isDataUser();
        if (dataUser) {


            for (int i = 0; i < arrayLis.size(); i++) {

                canvas.drawLine(arrayLis.get(i).getDianX(), arrayLis.get(i).getDianY(), arrayLis.size() - 1 == i ? arrayLis.get(i).getDianX() : arrayLis.get(i + 1).getDianX(), arrayLis.size() - 1 == i ? arrayLis.get(i).getDianY() : arrayLis.get(i + 1).getDianY(), paint);


                //画线


            }

        } else {
            for (int i = 0; i < list.size(); i++) {
                canvas.drawCircle(list.get(i).getX(), list.get(i).getY(), 5, paint);

            }
        }


    }
}
