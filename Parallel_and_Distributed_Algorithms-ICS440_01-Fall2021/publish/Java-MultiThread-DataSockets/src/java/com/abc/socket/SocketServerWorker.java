package com.abc.socket;

import java.io.*;
import java.net.*;

import com.abc.log.*;

/*
 * By Nalongsone Danddank. ICS 440-01, Metropolitan State University.
 *
 */

/**
 * self-running object to wait for client requests to be sent up and then send back the appropriate
 * response.
 */
public class SocketServerWorker {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket sock;
    private RequestResponseMapping requestResponseMapping;

    private Thread internalThread;
    private volatile boolean noStopRequested;
    private Log log;
    private int connectNum;

    public SocketServerWorker(Socket sock, Log log, int connectNum) throws IOException {
        this.requestResponseMapping = RequestResponseMapping.getInstance();
        this.log = log;
        this.connectNum = connectNum;
        log.outln("Accepted socket connection #" + connectNum);
        log.outln("Starting conversation #" + connectNum + ". Talking with " + sock.getInetAddress().getHostAddress());
        in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
        out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
        noStopRequested = true;
        internalThread = new Thread(() -> runWork());
        internalThread.start();
    }

    private void runWork() {
        while (noStopRequested) {
            try {
                String recieve = in.readUTF();

                if ( !recieve.equals(this.requestResponseMapping.getDisconnectRequest())) {
                    String response = this.requestResponseMapping.getResponse(recieve);
                    out.writeUTF(response);
                    out.flush();
                } else {
                    out.writeUTF(this.requestResponseMapping.getDisconnectResponse());
                    out.flush();
                    stopRequest();
                }
            } catch (InterruptedIOException iiox) {
                // ignore
            }

            catch (IOException x) {
                if (noStopRequested) {
                    x.printStackTrace();
                    stopRequest();
                }
            }
        }

    }

    public void stopRequest() {
        noStopRequested = false;

        internalThread.interrupt();
        log.outln("Completed conversation #" + connectNum);
        if (sock != null) {

            try {
                sock.close();
                in.close();
                out.close();
            } catch (IOException x) {
                // ignore
            }

            finally {
                sock = null;
            }
        }
    }

    public boolean isAlive() {
        return internalThread.isAlive();
    }
}
