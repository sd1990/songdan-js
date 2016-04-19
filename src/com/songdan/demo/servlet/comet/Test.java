package com.songdan.demo.servlet.comet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 8095181906918852254L;

    Logger log = Logger.getLogger(Test.class);

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        File logFile = new File("D:\\vpiaotong.log");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(logFile));
            while (reader.ready()) {
                log.info(reader.readLine());
                try {
                    Thread.sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            reader.close();
        }

    }
}

