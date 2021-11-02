package com.abc.network;

import java.io.*;
import java.net.*;

public class SocketFun {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("www.programix.com", 80);

            PrintWriter pw = new PrintWriter(
                new BufferedOutputStream(
                    socket.getOutputStream()));

            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new BufferedInputStream(
                        socket.getInputStream())));

            pw.append("GET /index.html HTTP/1.0\r\n");
            pw.append("Host: www.programix.com\r\n");
            pw.append("\r\n");
            pw.flush();

            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(">>>>>>>>>> " + line);
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
