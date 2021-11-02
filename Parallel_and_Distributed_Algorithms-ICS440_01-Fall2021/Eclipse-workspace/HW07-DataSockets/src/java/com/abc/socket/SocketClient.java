package com.abc.socket;

import java.io.*;
import java.net.*;

import com.abc.log.*;

public class SocketClient {
    private final Log log;
    private final RequestResponseMapping requestResponseMapping;

    // OK to add more variables...
    private Socket sock;
    private DataInputStream in;
    private DataOutputStream out;

    public SocketClient(String host, int port, Log log) throws IOException {
        this.log = log;
        this.requestResponseMapping = RequestResponseMapping.getInstance();

        log.outln("Attempting to connect to " + host + ":" + port);

        // TODO - do more here...
        sock = new Socket(host, port);
        in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
        log.outln("Creating DataInputStream...");
        out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
        log.outln("Creating DataOutputStream...");
        int randomNumber = (int) (Math.random() * (15 - 5) + 5);
        for (int i = 0; i < randomNumber; i++ ) {
            String requestRandom = this.requestResponseMapping.getRandomRequest();
            this.chat(requestRandom);
        }

    }

    public String chat(String request) throws IOException {
        // TODO - fix this so that the request is sent to the server and
        // the response is returned.
        out.writeUTF(request);
        log.outln("Sending: " + request);
        out.flush();
        String response = in.readUTF();
        log.outln("Response: " + response);
        return response;
    }

    public void disconnect() throws IOException {
        // TODO - send the goodbye stuff and then close the connection
        String req = this.requestResponseMapping.getDisconnectRequest();
        this.chat(req);
        if (sock != null) {
            try {
                sock.close();
                in.close();
                out.close();
                log.outln("Closing connection (streams and socket)");
            } catch (IOException x) {
                // ignore
            } finally {
                sock = null;
            }
        }

    }
}
