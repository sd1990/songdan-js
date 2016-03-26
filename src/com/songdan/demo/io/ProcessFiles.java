package com.songdan.demo.io;

import java.io.File;

/**
 * 处理File的类，核心方法是start()
 * Created by PC on 2016/3/26.
 */
public class ProcessFiles {

    private Strategy strategy;

    public ProcessFiles(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 处理文件的策略接口
     */
    public interface Strategy{

        void process(File file);
    }

    public void start(String path) {
        File targetFile = new File(path);
        if (targetFile.isFile()){
            //处理文件
            strategy.process(targetFile);
        }else {
            processDirectoryTree(targetFile);
        }
    }

    private void processDirectoryTree(File root) {
        for (File file : Directory.walk(root)) {
            strategy.process(file);
        }
    }

    public static void main(String[] args) {
        new ProcessFiles(new Strategy() {

            private String regex = ".*\\.java";

            @Override
            public void process(File file) {
                if(file.getName().matches(regex)){
                    System.out.println(file.getName());
                }
            }
        }).start("D:\\workplace\\songdanDemo\\src\\com\\songdan\\demo\\io");
    }

}
