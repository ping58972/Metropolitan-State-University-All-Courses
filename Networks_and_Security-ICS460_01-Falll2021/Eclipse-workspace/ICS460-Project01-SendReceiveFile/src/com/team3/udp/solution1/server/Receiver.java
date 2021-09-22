/**
 * 
 */
package com.team3.udp.solution1.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nalongsone Danddank.
 *
 */
public class Receiver {
	private final static int PORT = 13;
	private final static Logger audit = Logger.getLogger("requests");
	private final static Logger errors = Logger.getLogger("errors");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (DatagramSocket socket = new DatagramSocket(PORT)) {
			while(true) {
				try {
					DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
					socket.receive(request);
					String daytime = new Date().toString() + "haha";
					byte[] data = daytime.getBytes("US-ASCII");
					DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
					socket.send(response);
					audit.info("audit::: "+daytime + " " + request.getAddress());
				} catch(IOException | RuntimeException ex) {
					errors.log(Level.SEVERE, ex.getMessage(), ex);
				}
			}
		} catch(IOException ex) {
			errors.log(Level.SEVERE, ex.getMessage(), ex);
		}

	}

}
