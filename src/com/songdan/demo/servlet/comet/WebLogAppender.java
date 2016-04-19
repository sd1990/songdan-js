package com.songdan.demo.servlet.comet;

import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;

import javax.servlet.AsyncContext;
import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 基于AsyncContext支持的日志Appender
 * @author Songdan
 * @date 2016/4/19
 */
public class WebLogAppender extends WriterAppender {
    /**
     * 异步 Servlet 上下文队列
     */
    public static final Queue<AsyncContext> ASYNC_CONTEXT_QUEUE
            = new ConcurrentLinkedQueue<AsyncContext>();

    /**
     * AsyncContextQueue Writer
     */
    private Writer writer = new AsyncContextQueueWriter(ASYNC_CONTEXT_QUEUE);

    public WebLogAppender() {
        setWriter(writer);
    }

    public WebLogAppender(Layout layout) {
        this();
        super.layout = layout;
    }


}
