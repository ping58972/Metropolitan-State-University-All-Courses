/**
 * ICS460-01 Fall2021, Project 1 sender program - client side.
 * Instructor: Damodar Chetty
 * Write by Team #3: 
 * 		Asha Hassan, Ahmad Elsaid, Chitakhone Siharath, Nalongsone Danddank, Seth Prokop, Kou Yang, 		
 */
package com.team3.udp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * a sender program, the sender accepts a file as a command line parameter (any
 * binary file on your hard disk), breaks it into smaller chunks, and sends it
 * to the receiver using UDP.
 */
public class UdpFileSender {
	// set server port which want to connect.
	private final static int SERVER_PORT = 58972;
	private final static int LOCAL_PORT = 10000;
	// set server IP address which want to connect.
	private static final String SERVER_IP = "localhost"; // "127.0.0.1";
	// set maximum size of packet that want to sent.
	private final static int MAX_SIZE = 1024;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		DatagramSocket sock = null;
		FileInputStream fis = null;
		FileInputStream fis1 = null;
		BufferedInputStream bis = null;
		File myFile = null;

		// get server IP address.
		InetAddress address = InetAddress.getByName(SERVER_IP);

		// create bytes for build packet to send.
		byte[] myByteArr = new byte[MAX_SIZE];
		int totalLength = 0;
		int count = 0;

		// get a file path from command line that we want to sent.
		final String filePath = getFilePath(args);
		if (filePath == null) {
			System.out.println("No such file in this folder.");
			return;
		}

		try {
			// create a UDP socket to send a packet.
			sock = new DatagramSocket(LOCAL_PORT);
			System.out.println("Client is runing on IP Address: " + findIP() + ",and port# " + sock.getLocalPort());
			try {
				// get the file
				myFile = new File(filePath);
				// put the file to stream.
				fis = new FileInputStream(myFile);
			} catch (FileNotFoundException fx) {
				System.err.println(fx.getMessage());
				return;
			}
			// send the file name string to server there first.
			String fileName = myFile.getName();
			DatagramPacket sendFileNamePacket = new DatagramPacket(fileName.getBytes(), fileName.getBytes().length,
					address, SERVER_PORT);
			System.out.println("Connected to server IP address:" + sendFileNamePacket.getAddress().toString()
					+ " ,and port:" + sendFileNamePacket.getPort());
			sock.send(sendFileNamePacket);
			System.out.println("Sent file: " + fileName);

			// then count the total number of byte from file by MAX_SIZE of packet
			while ((count = fis.read(myByteArr)) != -1) {
				totalLength += count;
			}
			System.out.println("Total number of bytes:" + totalLength);

			// if the file size small than MAX_SIZE of packet, then just send all file in
			// one packet.
			if (totalLength <= MAX_SIZE) {
				DatagramPacket sendPack = new DatagramPacket(myByteArr, myByteArr.length, address, SERVER_PORT);

				sock.send(sendPack);
				printEachPacket(sendPack, 0, 0, sendPack.getLength() - 1);
				return;
			}

			// otherwise separate to many packet
			int numPack = totalLength / MAX_SIZE;
			if (totalLength % MAX_SIZE != 0) {
				System.out.println("Total number of packets: " + (numPack + 1));
			} else {
				System.out.println("Number of packets : " + numPack);
			}

			// sending the packets of file that only set to MAX_SIZE
			int offset = numPack * MAX_SIZE;
			fis1 = new FileInputStream(myFile);
			int i = 0;
			int byteArrIndex = 0;
			while ((count = fis1.read(myByteArr)) != -1) {
				if (numPack <= 0)
					break;
				// send each packet until the end or the last.
				DatagramPacket sendPack = new DatagramPacket(myByteArr, myByteArr.length, address, SERVER_PORT);

				sock.send(sendPack);
				printEachPacket(sendPack, i++, byteArrIndex, byteArrIndex + sendPack.getLength() - 1);
				byteArrIndex += sendPack.getLength();

				numPack--;

			}
			// send the last pack when the total length is not fit to the file.
			if (totalLength % MAX_SIZE != 0) {
				int lastPackLen = totalLength - offset;
				byte[] lastPack = new byte[lastPackLen - 1];

				lastPack = Arrays.copyOf(myByteArr, lastPackLen);

				DatagramPacket sendPack1 = new DatagramPacket(lastPack, lastPack.length, address, SERVER_PORT);
				printEachPacket(sendPack1, i, byteArrIndex, byteArrIndex + sendPack1.getLength() - 1);
				sock.send(sendPack1);
			}

			System.out.println("Sent file complete.");
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

	// print each packet number order and byte offset start and end.
	private static void printEachPacket(DatagramPacket pack, int i, int beginByteIndex, int endByteIndex) {
		byte startByte = pack.getData()[0];
		byte endByte = pack.getData()[pack.getLength() - 1];
		System.out.println("[Packet#: " + i + "]-[start byte offset:" + beginByteIndex + "(" + String.valueOf(startByte)
				+ ")]-[end byte offset:" + endByteIndex + "(" + String.valueOf(endByte) + ")]");
	}

	// get a file path from command line.
	private static String getFilePath(String[] args) {
		if (args.length > 0) {
			return System.getProperty("user.dir") + "/" + args[0];
		}
		return null;

	}

	// find out the client IP and server IP.
	private static String findIP() {
		String ip = null;
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface iface = interfaces.nextElement();
				// filters out 127.0.0.1 and inactive interfaces
				if (iface.isLoopback() || !iface.isUp())
					continue;

				Enumeration<InetAddress> addresses = iface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();
					ip = addr.getHostAddress();
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		return ip;
	}

}
