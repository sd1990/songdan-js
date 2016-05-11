package com.songdan.demo.effective_java.item66;

/**
 * 通过一个互斥变量，阻止一个线程的执行<br>
 * effective java里面说这个例子中的thread是停止不下来的，因为HopSpot Server VM对代码进行了提升，
 * 可是我运行结果是没问题的
 * Created by PC on 2016/5/7.
 */
public class StopThread {

    private static boolean stopRequested = false;

    public static void main(String[] args) {
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
        try {
            Thread.sleep(1);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopRequested = true;
    }

}
