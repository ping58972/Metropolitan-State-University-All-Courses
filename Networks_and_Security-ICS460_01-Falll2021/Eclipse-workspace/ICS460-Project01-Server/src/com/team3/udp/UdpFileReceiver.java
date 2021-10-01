/**
 * 
 */
package com.team3.udp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author ping58972
 *
 */
public class UdpFileReceiver {
	private final static int LOCAL_PORT = 58972;
//	public final static String FILE_TO_RECEIVED = System.getProperty("user.dir") + "/a.jpg";

	public final static int MAX_SIZE = 1024;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		DatagramSocket sock = null;
		FileOutputStream fos = null;
		File acceptFile = null;
		int i = 0;

		byte[] myByte = new byte[MAX_SIZE];
		try {
			sock = new DatagramSocket(LOCAL_PORT);

			System.out.println("Server is runing on IP Address: " + findIP()// InetAddress.getLocalHost().getHostAddress()
					+ " port:" + sock.getLocalPort() + ".\n==>Waiting for receive file...");

			DatagramPacket recFileNamePack = new DatagramPacket(new byte[4096], 4096);
			sock.receive(recFileNamePack);
			System.out.println("Connected from client IP address:" + recFileNamePack.getAddress().toString()
					+ " ,and port:" + recFileNamePack.getPort());
			String filePath = createFilePath(recFileNamePack);
			acceptFile = new File(filePath);
			fos = new FileOutputStream(acceptFile);
			System.out.println("Start to receive file: " + acceptFile.getName());
			int byteArrIndex = 0;
			while (true) {

				DatagramPacket recPack = new DatagramPacket(myByte, myByte.length);

				sock.receive(recPack);
				// print each packet.
				printEachPacket(recPack, i++, byteArrIndex, byteArrIndex + recPack.getLength() - 1);
				byteArrIndex += recPack.getLength();
				fos.write(recPack.getData(), 0, recPack.getLength());

				fos.flush();
			}
		} finally {
			System.out.println("Server Terminated!");
			if (sock != null)
				sock.close();
			if (fos != null)
				fos.close();

		}

	}

	private static void printEachPacket(DatagramPacket pack, int i, int beginByteIndex, int endByteIndex) {
		byte startByte = pack.getData()[0];
		byte endByte = pack.getData()[pack.getLength() - 1];
		System.out.println("[Packet#: " + i + "]-[start byte offset:" + beginByteIndex + "(" + String.valueOf(startByte)
				+ ")]-[end byte offset:" + endByteIndex + "(" + String.valueOf(endByte) + ")]");
	}

	private static String createFilePath(DatagramPacket fileNamePacket) {
		String filePath = null;
		try {
			filePath = System.getProperty("user.dir") + "/"
					+ new String(fileNamePacket.getData(), 0, fileNamePacket.getLength(), "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filePath;

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
//					System.out.println(iface.getDisplayName() + " " + ip);
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		return ip;
	}

}
