package com.abc.stringcalc.server;

import java.io.*;
import java.net.*;

import com.abc.stringcalc.dto.*;
import com.abc.stringcalc.util.*;
import com.programix.io.*;

public class StringCalcServer {
    private ServerSocket ss;
    private Log log;
    // private volatile boolean noStopRequested;
    private Thread internalThread;
    public StringCalcServer(int port, Log log) throws IOException {
        this.log = log;

        log.outln("Attempting to begin accepting connections on port " + port);

        //
        // TODO - Create ServerSocket and start an internal thread...
        //
        ss = new ServerSocket(port);
        // noStopRequested = true;
        internalThread = new Thread(() -> runWork());
        internalThread.start();
    }

    private void runWork() {

        try {
            log.outln("StringCalcServer ready on port: " + ss.getLocalPort());

            for (int count = 0; true; count++ ) {
                //
                // TODO - Each time through the loop, accept socket connections
                // from a client and hand off that socket to a new
                // instance of a self-running object dedicated to the
                // conversation.
                //
                Socket sock = ss.accept();
                Session ses = new Session(sock, count);
                // Thread thread = new Thread(ses);
                // thread.start();
            }
        } catch (Exception x) {
            log.out(x);
        } finally {
            IOTools.closeQuietly(ss);
            log.outln("StringCalcServer exiting.");
        }

    }

    private class Session implements Runnable {
        private final Socket sock;
        private final int id;
        private boolean dontExpectClientToCloseSocket;

        public Session(Socket sock, int id) {
            this.sock = sock;
            this.id = id;
            this.dontExpectClientToCloseSocket = true;

            Thread t = new Thread(this, "Session-" + id);
            t.start();
        }

        @Override
        public void run() {
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;

            log.outln("Starting conversation #" + id + ". Talking with " + sock.getInetAddress().getHostAddress());

            //
            // TODO - Setup object streams with the client.
            //

            // while (true) {
            try {

                log.outln("Attempting to create ObjectInputStream");
                ois = new ObjectInputStream(sock.getInputStream());
                oos = new ObjectOutputStream(sock.getOutputStream());
                oos.flush();
                //
                // TODO - Receive client requests, process, and send the
                // responses. If a DisconnectRequest is received
                // prepare for a graceful shutdown.
                //
                while (true) {
                    Object obj = ois.readObject();
                    if (obj instanceof StringCalcRequest) {
                        StringCalcRequest req = (StringCalcRequest) obj;

                        StringCalcResponse res = process(req);

                        oos.writeObject(res);
                        oos.flush();
                        // System.out.println("server line :88 " + res.getAverageLength());
                    } else if (obj instanceof DisconnectRequest) {
                        oos.writeObject(new DisconnectResponse());
                        oos.flush();

                        break;
                    }
                }

                // Thread.sleep(100);

            } catch (IOException | ClassNotFoundException x) {
                // TODO: handle exception
                x.printStackTrace();
            } finally {
                // TODO - cleanup
                log.outln("Completed conversation #" + id);
                IOTools.closeQuietly(sock, ois, oos);
                // if (sock != null) {
                // try {
                // sock.close();
                // oos.close();
                // ois.close();
                // } catch (Exception x) {
                // // TODO: handle exception
                // }
                // }
            }
            // }

        }

        private StringCalcResponse process(StringCalcRequest req) {
            StringCalculator sc = new StringCalculator(req.getData(), req.isIgnoreNulls());

            StringCalcResponse res = new StringCalcResponse();
            res.setCount(sc.getCount());
            res.setTotalLength(sc.getTotalLength());
            res.setMaxLength(sc.getMaxLength());
            res.setMinLength(sc.getMinLength());
            res.setAverageLength(sc.getAverageLength());
            return res;
        }
    } // class Session
}
