package com.cskaoyn.mobile.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by MonkeyzZi on 2016/3/25.
 */
public class HTTPUtils {

    public static String getTextFromStream(InputStream is){
        String result ="";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//往内存输出

        byte[] bytes = new byte[1024];
        int len = -1 ;
        try {
            while ((len=is.read(bytes,0,1024))!=-1){
                baos.write(bytes,0,len);


            }
            baos.close();
            baos.toString("GBK");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result ;


    }
}
