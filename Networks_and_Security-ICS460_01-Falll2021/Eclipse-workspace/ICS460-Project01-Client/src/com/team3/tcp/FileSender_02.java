package com.team3.tcp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FileSender_02 {
	public final static int SOCKET_PORT = 5897; // you may change this
	public final static String SERVER = "localhost"; // localhost
	public final static String FILE_TO_SEND = System.getProperty("user.dir") + "/send-files/zak.png"; // you
																										// may

	public static void main(String[] args) throws IOException {

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		Socket sock = null;
		try {
			sock = new Socket(SERVER, SOCKET_PORT);
			System.out.println("Connecting...");

			// send file
			File myFile = new File(FILE_TO_SEND);
			byte[] byteArray = new byte[(int) myFile.length()];
			fis = new FileInputStream(myFile);
			bis = new BufferedInputStream(fis);
			bis.read(byteArray, 0, byteArray.length);
			os = sock.getOutputStream();
			System.out.println("Sending " + FILE_TO_SEND + "(" + byteArray.length + " bytes)");
			os.write(byteArray, 0, byteArray.length);
			os.flush();
			System.out.println("Done.");

		} finally {
			if (bis != null)
				bis.close();
			if (os != null)
				os.close();
			if (sock != null)
				sock.close();
		}
	}

}
