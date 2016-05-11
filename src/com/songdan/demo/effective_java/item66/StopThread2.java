package com.songdan.demo.effective_java.item66;

/**
 * 对数据同步访问的做法
 * Created by PC on 2016/5/7.
 */
public class StopThread2 {

    private static boolean stopRequested = false;

    private static synchronized boolean getStopRequested() {
        return stopRequested;
    }

    private static synchronized void setStopRequested(boolean stopRequested) {
        StopThread2.stopRequested = stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (!getStopRequested()) {
                    i++;
                }
            }
        });
        thread.start();
        Thread.sleep(1);
        setStopRequested(true);
    }


}
