/**
 * 
 */
package com.team3.udp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

/**
 * @author ping58972
 *
 */
public class FileSender_1 {
	private final static int PORT = 58972;
	private static final String HOSTNAME = "localhost";
	private final static String FILE_TO_SEND = System.getProperty("user.dir") + "/send-files/panda.jpg"; // you
	private final static int MAX_SIZE = 1024;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		DatagramSocket sock = null;
		FileInputStream fis = null;
		FileInputStream fis1 = null;
		BufferedInputStream bis = null;

		InetAddress address = InetAddress.getByName(HOSTNAME);
		File myFile = null;
		byte[] myByteArr = new byte[MAX_SIZE];
		int totalLength = 0;
		int count = 0;
		try {
			sock = new DatagramSocket(5897);
			myFile = new File(FILE_TO_SEND);

			fis = new FileInputStream(myFile);
			while ((count = fis.read(myByteArr)) != -1) {
				totalLength += count;
			}
			System.out.println("Total Length :" + totalLength);
			int numPack = totalLength / MAX_SIZE;
			System.out.println("No of packets : " + numPack);
			int offset = numPack * MAX_SIZE;
			int lastPackLen = totalLength - offset;
			byte[] lastPack = new byte[lastPackLen - 1];
			System.out.println("\nLast packet Length : " + lastPackLen);
			fis1 = new FileInputStream(myFile);
			while ((count = fis1.read(myByteArr)) != -1) {
				if (numPack <= 0)
					break;
				System.out.println("Number of Packet sent: " + numPack);
				DatagramPacket sendPack = new DatagramPacket(myByteArr, myByteArr.length, address, PORT);
				sock.send(sendPack);
				System.out.println("========");
				System.out.println("last pack sent: " + sendPack);
				numPack--;
			}
			System.out.println("\nlast packet\n");
//			System.out.println(new String(myByteArr));
			lastPack = Arrays.copyOf(myByteArr, lastPackLen);
			System.out.println("\nActual last packet\n");
//			System.out.println(new String(lastPack));
			DatagramPacket sendPack1 = new DatagramPacket(lastPack, lastPack.length, address, PORT);
			sock.send(sendPack1);
			System.out.println("last Pack sent: " + sendPack1);
		} finally {
			if (sock != null)
				sock.close();
			if (fis != null)
				fis.close();
			if (fis1 != null)
				fis1.close();
			if (bis != null)
				bis.close();

		}

	}

}
