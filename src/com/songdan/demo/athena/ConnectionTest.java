package com.songdan.demo.athena;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicInteger;

import com.songdan.demo.util.HttpUtils;


public class ConnectionTest {
    public static void main(String[] args) {
        Connect con=new Connect();
        /*for (int i = 0; i < 1000; i++) {
        }*/
        Thread t1=new Thread(con);
        t1.start();
        Thread t2=new Thread(con);
        t2.start();
        Thread t3=new Thread(con);
        t3.start();
        Thread t4=new Thread(con);
        t4.start();
        Thread t5=new Thread(con);
        t5.start();
        Thread t6=new Thread(con);
        t6.start();
        Thread t7=new Thread(con);
        t7.start();
        Thread t8=new Thread(con);
        t8.start();
        Thread t9=new Thread(con);
        t9.start();
        Thread t10=new Thread(con);
        t10.start();
    }
    static class Connect implements Runnable{
        private static AtomicInteger ai = new AtomicInteger(200);
        @Override
        public void run() {
            while (ai.getAndDecrement()>0) {
//                    HttpURLConnection connection = HttpUtils.prepareHttpConnection("http://localhost:8080/Athena", HttpUtils.GET_METHOD);
                        System.out.println(Thread.currentThread().getName()+"========"+ai.get());
            }
        }
        
    }
}

