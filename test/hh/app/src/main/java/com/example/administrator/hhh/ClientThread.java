package com.example.administrator.hhh;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2018/7/3/003.
 */

public class ClientThread extends Thread {
    private OutputStream out=null;
    private InputStream en=null;
    private Handler handler;
    public Socket socket=null;
    public ClientThread(Handler handler){
        this.handler=handler;
    }
    public void run(){
        try {
            socket = new Socket("192.168.1.193", 8050);
            sendMessage("all****获取服务器全部数据");
            while (true) {
                en = socket.getInputStream();
                byte[] by = new byte[1024];
                int l1;
                String s = "";
                while ((l1 = en.read(by, 0, 1024)) != -1) {
                    s += new String(by, 0, l1, "utf8");
                   // Log.d("dsd", s);
                    String[] ss = s.split("\\*\\*\\*\\*\\*");
                    if (ss[ss.length - 1].equals("##end")) {
                        break;
                    }

                }
                Message m = new Message();
                Bundle b = new Bundle();
                b.putString("response", s);
                m.setData(b);
                handler.sendMessage(m);
            }
        }
         catch(Exception e){
                e.printStackTrace();
            }
    }
    public void sendMessage(final String sse){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    out=socket.getOutputStream();
                    out.write(sse.getBytes("utf8"));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
