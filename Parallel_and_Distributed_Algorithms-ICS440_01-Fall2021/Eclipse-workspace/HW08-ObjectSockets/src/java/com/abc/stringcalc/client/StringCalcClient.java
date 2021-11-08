package com.abc.stringcalc.client;

import java.io.*;
import java.net.*;

import com.abc.stringcalc.dto.*;
import com.abc.stringcalc.util.*;

public class StringCalcClient {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Log log;

    public StringCalcClient(String hostname, int port, Log log) throws IOException {

        this.log = log;

        log.outln("Attempting to connect to " + hostname + ":" + port);

        //
        // TODO - Create socket and object streams...
        socket = new Socket(hostname, port);

        log.outln("Attempting to create ObjectOutputStream");

        oos = new ObjectOutputStream(socket.getOutputStream());
        StringCalcRequest request = new StringCalcRequest();
        String[] dataStrings = { "z", "yuy", "ha" };
        request.setData(dataStrings);
        request.setIgnoreNulls(false);
        StringCalcResponse res = calculate(request);
        System.out.println("server line :88 " + res.getAverageLength());
        log.outln("Response resutl: " + res.getCount());
        String[] data2 = { "7", "ypy", "hyy" };
        StringCalcRequest request2 = new StringCalcRequest();
        request2.setData(data2);
        request2.setIgnoreNulls(false);
        StringCalcResponse res2 = calculate(request);
        log.outln("Response resutl: " + res2.getCount());
        try {
            Thread.sleep(100);
        } catch (InterruptedException x) {
            x.printStackTrace();
        }
        log.outln("Attempting to create ObjectInputStream");
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public void disconnect() throws IOException, ClassNotFoundException {
        //
        // TODO - Disconnect from the server in a graceful manner
        oos.writeObject(new DisconnectRequest());
        oos.flush();
        log.outln("Sending disconnect request...");
        Object obj = ois.readObject();
        if (obj instanceof DisconnectResponse) {
            log.outln("Client disconnect complete.");
            if (socket != null) {
                try {
                    socket.close();
                    oos.close();
                    ois.close();
                } catch (Exception x) {
                    // TODO: handle exception
                }
            }
        }

    }

    public StringCalcResponse calculate(StringCalcRequest req) throws IOException {

        //
        // TODO - Send the request to the server and then receive the response.
        //
        oos.writeObject(req);
        oos.flush();
        log.outln("Processing a StringCalcRequest...  done.");
        // try {
        // Thread.sleep(100);
        // } catch (InterruptedException x1) {
        // x1.printStackTrace();
        // }
        StringCalcResponse res = null;
        System.out.println("server line :88 " + res.getAverageLength());
        try {
            res = (StringCalcResponse) ois.readObject();

            return res;
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
        return null;
    }
}
