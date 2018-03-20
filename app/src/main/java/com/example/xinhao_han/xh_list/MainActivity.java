package com.example.xinhao_han.xh_list;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.xhcharlib.XHData;
import com.example.xhcharlib.XHLineChar;
import com.example.xhlistviewlib.XHListView;
import com.example.xhlistviewlib.data.XHBaseAdapter;
import com.example.xhlistviewlib.data.XHViewHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     *
     * Start Project 10:00
     *
     * @param savedInstanceState
     */

    private XHLineChar xhLineChar;
    public static Handler handler;

    private ArrayList<String> arrayList;
    private XHListView xhList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        arrayList = new ArrayList<>();
        xhList = findViewById(R.id.xhList);

        for (int i = 0; i < 50; i++) {
            arrayList.add(i + "");
        }

        xhList.setXHAdapter(new XHBaseAdapter<String>(arrayList) {
            @Override
            public XHViewHolder getXHViewHolder() {
                View inflate = View.inflate(MainActivity.this, R.layout.test_2, null);
                xhLineChar = inflate.findViewById(R.id.xhchar);
                xhLineChar.setMaxTable(10.5f);
                xhLineChar.setMinTable(-8.5f);

                ArrayList<XHData> xhData = new ArrayList<>();

        /*xhData.add(new XHData(20,0));
        xhData.add(new XHData(50,80));
        xhData.add(new XHData(60,30));
        xhData.add(new XHData(80,26));
        xhData.add(new XHData(17,26));
        xhData.add(new XHData(80,65));
        xhData.add(new XHData(26,74));
        xhData.add(new XHData(20,86));*/


                xhData.add(new XHData(true,5.6f,"2018/12-12"));
                xhData.add(new XHData(true,9.5f,"2018/12-12"));
                xhData.add(new XHData(true,8.5f,"2018/12-12"));
                xhData.add(new XHData(true,10.5f,"2018/12-12"));
                xhData.add(new XHData(true,7.5f,"2018/12-12"));
                xhData.add(new XHData(true,6.5f,"2018/12-12"));
                xhData.add(new XHData(true,-6.5f,"2018/12-12/15"));
                xhData.add(new XHData(true,5.6f,"2018/12-12"));
                xhData.add(new XHData(true,9.5f,"2018/12-12"));
                xhData.add(new XHData(true,8.5f,"2018/12-12"));
                xhData.add(new XHData(true,10.5f,"2018/12-12"));
                xhData.add(new XHData(true,7.5f,"2018/12-12"));
                xhData.add(new XHData(true,6.5f,"2018/12-12"));
                xhData.add(new XHData(true,-6.5f,"2018/12-12/15"));
                xhData.add(new XHData(true,5.6f,"2018/12-12"));
                xhData.add(new XHData(true,9.5f,"2018/12-12"));
                xhData.add(new XHData(true,8.5f,"2018/12-12"));
                xhData.add(new XHData(true,10.5f,"2018/12-12"));
                xhData.add(new XHData(true,7.5f,"2018/12-12"));
                xhData.add(new XHData(true,6.5f,"2018/12-12"));
                xhData.add(new XHData(true,-6.5f,"2018/12-12/15"));
                xhLineChar.setLineHOrLineW(2.0f,10.0f);
                xhLineChar.setDataArray(xhData);
                return new MyXhViewHolder(View.inflate(MainActivity.this,R.layout.test_layout,null),inflate);
            }

            @Override
            public void initView(int position, XHViewHolder xhViewHolder, String o) {

                MyXhViewHolder myXhViewHolder = (MyXhViewHolder) xhViewHolder;
                myXhViewHolder.textView.setText(o);

            }
        });
    }

    class MyXhViewHolder extends XHViewHolder{


        public TextView textView;
        public MyXhViewHolder(View view,View view2) {
            super(view,view2);
            textView = (TextView) findViewById(R.id.textView);
        }
    }
}
