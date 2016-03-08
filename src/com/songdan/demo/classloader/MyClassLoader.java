package com.songdan.demo.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;



public class MyClassLoader extends ClassLoader{

    private String path ;
    public MyClassLoader(String path) {
        super();
        this.path = path;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = className2Path(name);
        try {
            InputStream in = new FileInputStream(new File(path));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                CppherUtils.cypher(in, baos);
                byte[] byteArray = baos.toByteArray();
                return defineClass(name, byteArray, 0, byteArray.length);
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        catch (FileNotFoundException e) {
        }
        return null;
    }
    private  String className2Path(String name) {
        return "D:\\workplace\\songdanDemo\\"+path+name.replace(".", File.separator)+".class";
    }
    
    public static void main(String[] args) throws Exception {
        Class<?> loadClass = new MyClassLoader("testlib\\").loadClass("com.songdan.demo.atomic.AtomicIntegerDemo");
        loadClass.newInstance();
    }
}
