/**
 * 
 */
package com.team3.tcp;

import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author Nalongsone Danddank.
 *
 */
public class Receiver {
	
	public final static int PORT = 34567;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT)) {
			System.out.println( //IP.getHostAddress());
					InetAddress.getLocalHost().toString());
			while(true) {
				try (Socket sock = server.accept()) {
					
					Writer out = new OutputStreamWriter(sock.getOutputStream());
					Date now = new Date();
					out.write(now.toString()+"\r\n"+"From MacOS");
					out.flush();
					sock.close();
				} catch (IOException ex) {}
			} 
		} catch (IOException ex) {}

	}

}
