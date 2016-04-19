package com.songdan.demo.servlet.comet;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * 异步上下文队列Writer
 * @author Songdan
 * @date 2016/4/19
 */
public class AsyncContextQueueWriter extends Writer {

    private Queue<AsyncContext> queue;

    /**
     * 构造函数，执行线程
     * @param queue
     */
    public AsyncContextQueueWriter(Queue<AsyncContext> queue) {
        this.queue = queue;
        Thread thread = new Thread(notifierRunnable);
        thread.start();
    }

    private static final BlockingQueue<String> MESSAGE_QUEUE = new LinkedBlockingQueue<>();

    private Runnable notifierRunnable = new Runnable() {

        @Override
        public void run() {
            boolean done = false;
            while (!done) {
                String message = null;
                try {
                    message = MESSAGE_QUEUE.take();
                    for (AsyncContext asyncContext : queue) {
                        try {
                            PrintWriter writer = asyncContext.getResponse().getWriter();
                            writer.println(htmlEscape(message));
                            writer.flush();
                        }
                        catch (IOException e) {
                            System.out.println(e);
                            queue.remove(asyncContext);
                        }
                    }
                }
                catch (InterruptedException e) {
                    done = true;
                    System.out.println(e);
                }
            }
        }
    };

    /**
     * @param message
     * @return
     */
    private String htmlEscape(String message) {
        return "<script type='text/javascript'>\nwindow.parent.update(\""
                + message.replaceAll("\n", "").replaceAll("\r", "") + "\");</script>\n";
    }

    private static final Writer DEFAUL_WRITER = new OutputStreamWriter(System.out);



    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        DEFAUL_WRITER.write(cbuf,off,len);
        sendMessage(cbuf, off, len);
    }

    private void sendMessage(char[] cbuf, int off, int len) throws IOException {
        try {
            MESSAGE_QUEUE.put(new String(cbuf, off, len));
        } catch (Exception ex) {
            IOException t = new IOException();
            t.initCause(ex);
            throw t;
        }
    }

    @Override
    public void flush() throws IOException {
        DEFAUL_WRITER.flush();
    }

    @Override
    public void close() throws IOException {
        DEFAUL_WRITER.close();
    }
}
