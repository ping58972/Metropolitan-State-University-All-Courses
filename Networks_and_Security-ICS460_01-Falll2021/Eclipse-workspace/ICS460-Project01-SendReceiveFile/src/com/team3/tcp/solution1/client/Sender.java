/**
 * 
 */
package com.team3.tcp.solution1.client;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String hostname = "google.com";
		Socket socket = null;
		try {
			// "192.168.1.13"
//			hostname = "127.0.0.1";
			hostname = "192.168.1.22";
//			hostname = "192.168.1.16";
			InetAddress IP = InetAddress.getLocalHost();
			
			System.out.println( //IP.getHostAddress());
					InetAddress.getLocalHost().toString());
			socket = new Socket(hostname, 13);
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
