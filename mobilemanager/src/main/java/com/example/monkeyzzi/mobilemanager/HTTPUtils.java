package com.example.monkeyzzi.mobilemanager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by MonkeyzZi on 2016/3/25.
 */
public class HTTPUtils {



    public static String getTextFromStream(InputStream is ){
        String result ="";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] bytes = new byte[1024];
        int len =-1 ;
        try {
            while((len=is.read(bytes,0,1024))!=-1){

            }
            baos.close();

          result =  baos.toString("GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result ;
    }
}
