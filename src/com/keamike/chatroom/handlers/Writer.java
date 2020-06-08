package com.keamike.chatroom.handlers;

import org.json.simple.JSONObject;

import java.io.PrintWriter;
import java.util.Scanner;

public class Writer implements Runnable {

    private Scanner sc = new Scanner(System.in);
    private PrintWriter out;

    public Writer(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        while (true) {
            writeMsg(out);
        }
    }

    private void writeMsg(PrintWriter out) {
        String msg = sc.nextLine();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", msg);
        out.println(jsonObject.toJSONString());
    }
}
