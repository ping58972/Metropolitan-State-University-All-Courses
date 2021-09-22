/**
 * 
 */
package com.team3.tcp.solution1.server;

import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author Nalongsone Danddank.
 *
 */
public class Receiver {
	
	public final static int PORT = 13;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT)) {
			while(true) {
				try (Socket connection = server.accept()) {
					
					Writer out = new OutputStreamWriter(connection.getOutputStream());
					Date now = new Date();
					out.write(now.toString()+"\r\n"+"hahah");
					out.flush();
					connection.close();
				} catch (IOException ex) {}
			} 
		} catch (IOException ex) {}

	}

}
