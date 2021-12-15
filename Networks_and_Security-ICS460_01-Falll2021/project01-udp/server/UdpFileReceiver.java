
/**
 * ICS460-01 Fall2021, Project 1 receiver program -  server side.
 * Instructor: Damodar Chetty
 * Write by Team #3: 
 * 		Asha Hassan, Ahmad Elsaid, Chitakhone Siharath, Nalongsone Danddank, Seth Prokop, Kou Yang, 		
 */

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
 * a receiver program, The receiver will concatenate the pieces it receives and
 * will store it to a file on its end. by using UDP
 */
public class UdpFileReceiver {
	// set local port for server.
	private final static int LOCAL_PORT = 58972;
	// set defeat maximum size to each packet that receive from sender.
	public final static int MAX_SIZE = 1024;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		DatagramSocket sock = null;
		FileOutputStream fos = null;
		File acceptFile = null;
		int i = 0;
		// create byte to receive for each packet.
		byte[] myByte = new byte[MAX_SIZE];
		try {
			// create a UDP socket for a server.
			sock = new DatagramSocket(LOCAL_PORT);

			System.out.println("Server is runing on IP Address: " + findIP() + " port:" + sock.getLocalPort()
					+ ".\n==>Waiting for receive file...");

			// create a packet to receive the file name.
			DatagramPacket recFileNamePack = new DatagramPacket(new byte[4096], 4096);
			sock.receive(recFileNamePack);
			System.out.println("Connected from client IP address:" + recFileNamePack.getAddress().toString()
					+ " ,and port:" + recFileNamePack.getPort());

			// after receive the file name String to create a file path and empty file.
			String filePath = createFilePath(recFileNamePack);
			acceptFile = new File(filePath);
			fos = new FileOutputStream(acceptFile);
			System.out.println("Start to receive file: " + acceptFile.getName());

			// loop to accept and collect all the packet to build the file.
			int byteArrIndex = 0;
			while (true) {
				// create a packet for receive each packet from client.
				DatagramPacket recPack = new DatagramPacket(myByte, myByte.length);

				sock.receive(recPack);
				// print each packet.
				printEachPacket(recPack, i++, byteArrIndex, byteArrIndex + recPack.getLength() - 1);
				byteArrIndex += recPack.getLength();
				// write each pack to file.
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

	// print each packet number order and byte offset start and end.
	private static void printEachPacket(DatagramPacket pack, int i, int beginByteIndex, int endByteIndex) {
		byte startByte = pack.getData()[0];
		byte endByte = pack.getData()[pack.getLength() - 1];
		System.out.println("[Packet#: " + i + "]-[start byte offset:" + beginByteIndex + "(" + String.valueOf(startByte)
				+ ")]-[end byte offset:" + endByteIndex + "(" + String.valueOf(endByte) + ")]");
	}

	// create a file path by received packet.
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
					// System.out.println(iface.getDisplayName() + " " + ip);
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		return ip;
	}

}
