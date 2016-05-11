package com.songdan.demo.effective_java.item66;

/**
 * 对数据同步访问的做法
 * Created by PC on 2016/5/7.
 */
public class StopThread3 {

    private static volatile boolean stopRequested = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (!stopRequested) {
                    i++;
                }
            }
        });
        thread.start();
        Thread.sleep(1);
        stopRequested = true;
    }


}
