package com.songdan.demo.wsdl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.songdan.demo.property.PropertiesManager;

/**
 * webservice工具类
 * 
 * @author SONGDAN
 */
public final class WebServiceUtils {

    /**
     * 私有化构造方法
     */
    private WebServiceUtils() {

    }

    /** wsdl解析地址 */
    private static String WSURL;

    /** wsdl命名空间 */
    private static String WSDLNS;

    /** 响应的开标签 */
    private static final String RETURN_OPEN_TAG = "<return>";

    /** 响应的闭合标签 */
    private static final String RETURN_CLOSE_TAG = "</return>";

    /**
     * 请求webservice
     * 
     * @param methodName
     *            方法名
     * @param args
     *            参数列表
     * @return 服务端相应的结果
     */
    public static String request(String methodName, String... args) {
        String result = "";
        URL url;
        WSURL = PropertiesManager.getConfigItem("general.properties", "wsdlUrl", null);
        WSDLNS = PropertiesManager.getConfigItem("general.properties", "wsdlNS",null);
        //创建url
        try {
            url = new URL(WSURL);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(WSDLNS + ",此url有问题");
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setConnectTimeout(60*1000);
        try {
            con.setRequestMethod("POST");
        }
        catch (ProtocolException e1) {
            e1.printStackTrace();
        }
        con.setRequestProperty("content-type", "text/xml;charset=UTF-8");
        StringBuffer requestBody =
                new StringBuffer("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
                        + " xmlns:q0=\"" + WSDLNS + "\" " + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema \" "
                        + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "<soapenv:Body>" + "<q0:"
                        + methodName + ">");
        for (int i = 0; i < args.length; i++) {
            requestBody.append("<arg" + i + ">" + args[i] + "</arg" + i + ">");
        }
        requestBody.append("</q0:" + methodName + ">");
        requestBody.append("</soapenv:Body>");
        requestBody.append("</soapenv:Envelope>");
        OutputStream out = null;
        BufferedReader br = null;
        try {
            out = con.getOutputStream();
            //向请求体中写入请求参数
            out.write(requestBody.toString().getBytes());
            int code = con.getResponseCode();
            if (code == 200) {// 服务端返回正常
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\r\n");
                }
                //截取需要的部分
                result = sb.substring(sb.indexOf(RETURN_OPEN_TAG) + RETURN_OPEN_TAG.length(),
                        sb.lastIndexOf(RETURN_CLOSE_TAG));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            con.disconnect();
            try {
                br.close();
                out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static void main(String[] args) {
        // PermissionsAuthorizationFilter
//        String request = request("forceLogout", "ycathena");
        request("noticeSysteResource");
    }
}
