/**
 * 
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
 * @author ping58972
 *
 */
public class UdpFileSender {
	private final static int SERVER_PORT = 58972;
	private final static int LOCAL_PORT = 10000;
	private static final String SERVER_IP = "192.168.1.16"; // "192.168.1.16";//"127.0.0.1";//"localhost"
	private final static int MAX_SIZE = 1024;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		DatagramSocket sock = null;
		FileInputStream fis = null;
		FileInputStream fis1 = null;
		BufferedInputStream bis = null;

		InetAddress address = InetAddress.getByName(SERVER_IP);
		File myFile = null;
		byte[] myByteArr = new byte[MAX_SIZE];
		int totalLength = 0;
		int count = 0;
		final String filePath = getFilePath(args);
		if (filePath == null) {
			System.out.println("No such file in this folder.");
			return;
		}

		try {
			sock = new DatagramSocket(LOCAL_PORT);
			System.out.println("Client is runing on IP Address: " + findIP() + ",and port# " + sock.getLocalPort());
			try {
				myFile = new File(filePath);
				fis = new FileInputStream(myFile);
			} catch (FileNotFoundException fx) {
				System.err.println(fx.getMessage());
				return;
			}

			String fileName = myFile.getName();
			DatagramPacket sendFileNamePacket = new DatagramPacket(fileName.getBytes(), fileName.getBytes().length,
					address, SERVER_PORT);
			System.out.println("Connected to server IP address:" + sendFileNamePacket.getAddress().toString()
					+ " ,and port:" + sendFileNamePacket.getPort());
			sock.send(sendFileNamePacket);
			System.out.println("Sent file: " + fileName);

			while ((count = fis.read(myByteArr)) != -1) {
				totalLength += count;
			}
			System.out.println("Total number of bytes:" + totalLength);
			if (totalLength <= MAX_SIZE) {
				DatagramPacket sendPack = new DatagramPacket(myByteArr, myByteArr.length, address, SERVER_PORT);

				sock.send(sendPack);
				printEachPacket(sendPack, 0, 0, sendPack.getLength() - 1);
				return;
			}
			int numPack = totalLength / MAX_SIZE;
			if (totalLength % MAX_SIZE != 0) {
				System.out.println("Total number of packets: " + (numPack + 1));
			} else {
				System.out.println("Number of packets : " + numPack);
			}
			int offset = numPack * MAX_SIZE;
			fis1 = new FileInputStream(myFile);
			int i = 0;
			int byteArrIndex = 0;
			while ((count = fis1.read(myByteArr)) != -1) {
				if (numPack <= 0)
					break;
				DatagramPacket sendPack = new DatagramPacket(myByteArr, myByteArr.length, address, SERVER_PORT);

				sock.send(sendPack);
				printEachPacket(sendPack, i++, byteArrIndex, byteArrIndex + sendPack.getLength() - 1);
				byteArrIndex += sendPack.getLength();

				numPack--;

			}
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

	private static void printEachPacket(DatagramPacket pack, int i, int beginByteIndex, int endByteIndex) {
		byte startByte = pack.getData()[0];
		byte endByte = pack.getData()[pack.getLength() - 1];
		System.out.println("[Packet#: " + i + "]-[start byte offset:" + beginByteIndex + "(" + String.valueOf(startByte)
				+ ")]-[end byte offset:" + endByteIndex + "(" + String.valueOf(endByte) + ")]");
	}

	private static String getFilePath(String[] args) {
		if (args.length > 0) {
			return System.getProperty("user.dir") + "/" + args[0];
		}
		return null;

	}

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
