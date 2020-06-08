package com.keamike.chatroom.handlers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;

public class Reader implements Runnable {

    private BufferedReader in;

    public Reader(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        while (true) {
            String msg = readMsg(in);

            if (msg.length() >= 26) {
                if (msg.substring(14, 26).equals("disconnected")) {
                    System.out.println(msg);
                    System.exit(0);
                }
            }
            System.out.println(msg);
        }
    }
    private String readMsg(BufferedReader in) {
        String line = "";
        try {
            line = in.readLine();
            if (line != null) {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(line);
                return (String) jsonObj.get("message");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "Message couldn't be read";
    }
}
