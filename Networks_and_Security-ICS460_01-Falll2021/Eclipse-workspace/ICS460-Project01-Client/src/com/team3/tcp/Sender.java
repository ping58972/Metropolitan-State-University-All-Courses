/**
 * 
 */
package com.team3.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.InetAddress;

/**
 * @author Nalongsone Danddank.
 *
 */
public class Sender {
	public final static int PORT = 34567;
	public final static String hostname = "localhost";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Socket socket = null;
		try {

			socket = new Socket(hostname, PORT);
			System.out.println("connected!");
			socket.setSoTimeout(5000);
			InputStream in = socket.getInputStream();
			StringBuilder time = new StringBuilder();
			InputStreamReader reader = new InputStreamReader(in, "ASCII");
			for(int c = reader.read(); c != -1; c = reader.read()) {
				time.append((char) c);
			}
			System.out.println(time);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			System.out.println("close!");

			try {
				socket.close();
			} catch (IOException ex) {}
		}

	}

}
