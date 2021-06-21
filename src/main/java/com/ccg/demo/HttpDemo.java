package com.ccg.demo;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author chenchonggui
 * @version 1.0
 * @date_time 2021/6/21 11:02
 */
public class HttpDemo {


    public static void main(String[] args) {
        sendHttp();
    }

    public static void sendHttp(){
        try {
            URL url = new URL("");
            InputStream in = null;
            PrintWriter out = null;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestMethod("POST");
            // todo 需要确定方式
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setRequestProperty("ccg", "hhhhhhhhhh");

            out = new PrintWriter(connection.getOutputStream());
            //out.print("{\"tag\":\"ksks\"}");
            out.flush();
            out.close();

            boolean ok = false;

            in = connection.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufReader = new BufferedReader(reader);

            String line;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            System.out.println("line = " + line);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
