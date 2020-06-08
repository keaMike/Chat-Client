package com.keamike.chatroom.client;

import com.keamike.chatroom.handlers.Reader;
import com.keamike.chatroom.handlers.Writer;

import java.io.*;
import java.net.Socket;

public class Client {

    private BufferedReader in;
    private PrintWriter out;

    public void run() {
        try {
            int PORT = 1234;
            String IP = "127.0.0.1";
            Socket socket = new Socket(IP, PORT);

            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Reader reader = new Reader(in);
            Writer writer = new Writer(out);
            Thread readerThread = new Thread(reader);
            Thread writerThread = new Thread(writer);

            readerThread.start();
            writerThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
