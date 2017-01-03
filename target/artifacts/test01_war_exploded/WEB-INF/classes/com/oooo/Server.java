package com.oooo;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/9/18.
 */
public class Server {
    private void init(int port){
        System.out.println(port);
        final ServerSocket serverSocket;
        ExecutorService servers = Executors.newFixedThreadPool(5);
        try {
            serverSocket = new ServerSocket(port);
            int i = 0;
            while (true) {
                //System.out.println(i++);
                servers.execute(new Runnable() {
                    public void run() {
                        Socket socket = null;
                        try {
                            socket = serverSocket.accept();
                            InputStream input = null;
                            try {
                                input = socket.getInputStream();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            byte[] bytes = new byte[input.available()];
                            input.read(bytes);
                            String str = new String(bytes, "utf-8");
                            System.out.println(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        new Server().init(Integer.parseInt(args[0]));
    }
}
