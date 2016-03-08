package com.songdan.demo.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class Test6 {

    public static void main(String[] args) {
        try {
            throw new MyException();
        }
        catch (MyException e) {
        }
    }
}

class MyException extends Exception {

    private static Logger log = Logger.getLogger(MyException.class);

    public MyException() {
        super();
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        log.info(trace.toString());
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

}