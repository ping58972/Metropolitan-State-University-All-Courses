package com.abc.socket;

import java.io.*;
import java.net.*;

import com.abc.log.*;

public class SocketServer {
    private final Log log;
    private ServerSocket serverSocket;

    private Thread internalThread;
    private volatile boolean noStopRequested;
    private int connectNum;

    public SocketServer(int port, Log log) throws IOException {
        this.log = log;
        connectNum = 0;
        log.outln("Attempting to begin accepting connections on port " + port);

        // TODO - setup a ServerSocket
        serverSocket = new ServerSocket(port);
        log.outln("SocketServer ready on port: " + port);
        // TODO - spawn an internal thread to wait for connections
        noStopRequested = true;
        internalThread = new Thread(() -> runWork());
        internalThread.start();
    }

    private void runWork() {
        while (noStopRequested) {
            try {
                // TODO - Accept socket connections for clients...
                Socket socket = serverSocket.accept();

                // TODO - For each accepted socket, create a new thread (possibly
                // in another self-running object) to wait for client requests
                // to be sent up and then send back the appropriate response.
                SocketServerWorker ssWorker = new SocketServerWorker(socket, log, connectNum++ );
            } catch (IOException x) {
                stopRequest();
                if (noStopRequested) {
                    x.printStackTrace();
                }
            }

        }

    }

    // TODO - Be ready to handle multiple simultaneous conversations
    // with different clients.
    private void stopRequest() {
        noStopRequested = false;
        internalThread.interrupt();
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException x) {
                // ignore
            } finally {
                serverSocket = null;
            }
        }
    }

    public boolean isAlive() {
        return internalThread.isAlive();
    }
}
