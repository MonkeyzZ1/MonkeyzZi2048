package com.example.monkeyzzi.day21_;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by MonkeyzZi on 2016/3/18.
 */
public class MultiDownload {
    static int ThreadCount =3 ;
    static int finishedThread = 0 ;
    //确定下载地址
    static String path = "http://192.168.3.73:8080/com.mp3";
    public static void main(String[] args){
       //发送get请求，请求这个地址的资源
        try {
            URL url = new URL(path);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            if(conn.getResponseCode() == 200){
                //拿到所请求资源文件的长度
                int length = conn.getContentLength();
                File file = new File("com.mp3");
                //生成临时文件
                RandomAccessFile raf = new RandomAccessFile(file,"rwd");
                //设置临时文件的大小
                raf.setLength(length);
                raf.close();
                //计算出每个线程应该下载多少字节
                int size = length / MultiDownload.ThreadCount;

                for (int i =0;i<ThreadCount;i++){
                    //计算线程下载的开始位置和结束位置
                    int startIndex = i * size ;
                    int endIndex = (i+1) * size -1 ;
                    //如果是最后一个线程，那么结束位置写死
                    if (i==ThreadCount -1 ){
                        endIndex = length -1 ;
                    }
                    new DownLoadThread(startIndex,endIndex,i).start();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
    class DownLoadThread extends Thread{
    int startIndex ;
    int endIndex ;
    int threadId ;
        private HttpsURLConnection conn;

        public DownLoadThread(int startIndex, int endIndex, int threadId) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        //再次发送http请求，下载原文件

        try {
            File progressFile = new File(threadId + ".txt");
            //判断进度临时文件是否存在
            if (progressFile.exists()){
                FileInputStream fis = new FileInputStream(progressFile);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                //从进度临时文件中读取出上一次下载的总进度，然后与原本的开始位置相加，得到新的开始位置
                startIndex += Integer.parseInt(br.readLine());
                fis.close();
            }
            URL url = new URL(MultiDownload.path);
            conn = (HttpsURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);

            //请求部分数据，响应码是206
            if (conn.getResponseCode()==206){
                //流里此时只有1/3的原文件数据
                InputStream is = conn.getInputStream();
                byte[] b = new byte[1024];
                int len = 0 ;
                int total =0;
                //拿到临时文件的输出流
                File file = new File("com.mp3");
                RandomAccessFile raf = new RandomAccessFile(file,"rwd");
                //把文件的写入位置移动至startIndex
                raf.seek(startIndex);
                while ((len=is.read(b))!=-1){
                    //每次读取流里数据之后，同步把数据写入临时文件
                    raf.write(b,0,len);
                    total +=len ;
                    //生成一个专门用来记录下载进度的临时文件
                    RandomAccessFile progressRaf = new RandomAccessFile(progressFile,"rwd") ;
                    //每次读取流里数据之后，同步把当前线程下载的总进度写入进度临时文件中
                    progressRaf.write((total + " ").getBytes());
                    progressRaf.close();
                }
                    raf.close();
                    MultiDownload.finishedThread++;
                    synchronized (MultiDownload.path){
                        if (MultiDownload.finishedThread==MultiDownload.ThreadCount){
                            for (int i =0;i<MultiDownload.ThreadCount;i++){
                                File f = new File( i +".txt");
                                f.delete();
                            }
                            MultiDownload.finishedThread = 0 ;
                        }

                    }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
