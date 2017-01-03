package com.oooo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Administrator on 2016/9/18.
 */
public class client {
    private void init(int port){
        Socket socket = new Socket();

        try {
            socket.connect(new InetSocketAddress(port));
            OutputStream out = socket.getOutputStream();
            out.write("ddddbbb".getBytes());
            out.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new client().init(8088);
    }
}
