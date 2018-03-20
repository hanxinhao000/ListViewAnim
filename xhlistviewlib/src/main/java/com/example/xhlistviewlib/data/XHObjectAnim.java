package com.example.xhlistviewlib.data;

import android.os.Handler;
import android.os.Message;

/**
 * Created by XINHAO_HAN on 2018/3/19.
 */

public class XHObjectAnim {







    public static void startAnim(final int start, final int end, final int l, final Move move){






            new Thread(new Runnable() {
                @Override
                public void run() {

                    int mid = end - start;

                    int fen = mid / l;

                    for (int i = start; i < end; i+=fen) {

                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        move.move(i);

                    }

                    move.animEnd();
                }

            }).start();




    }








    public interface Move{
        void move(int move);
        void animEnd();
    }
}
