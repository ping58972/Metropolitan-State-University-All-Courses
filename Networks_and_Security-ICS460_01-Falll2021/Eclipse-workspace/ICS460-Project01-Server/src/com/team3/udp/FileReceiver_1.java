/**
 * 
 */
package com.team3.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author ping58972
 *
 */
public class FileReceiver_1 {
	private final static int PORT = 58972;
	public final static String FILE_TO_RECEIVED = System.getProperty("user.dir") + "/receive-files/a.jpg";

	public final static int MAX_SIZE = 1024;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		DatagramSocket sock = null;
		FileOutputStream fos = null;
		int i = 0;

		byte[] myByte = new byte[MAX_SIZE];
		try {
			sock = new DatagramSocket(PORT);
			fos = new FileOutputStream(FILE_TO_RECEIVED);
			System.out.println("Wait...");
			while (true) {

				DatagramPacket recPack = new DatagramPacket(myByte, myByte.length);
				sock.receive(recPack);
				fos.write(recPack.getData(), 0, recPack.getLength());
				System.out.println("\n Packet length: " + recPack.getLength());

				System.out.println("\nPacket" + ++i + " written to file\n");

				fos.flush();
			}
		} finally {
			if (sock != null)
				sock.close();
			if (fos != null)
				fos.close();

		}

	}

}
