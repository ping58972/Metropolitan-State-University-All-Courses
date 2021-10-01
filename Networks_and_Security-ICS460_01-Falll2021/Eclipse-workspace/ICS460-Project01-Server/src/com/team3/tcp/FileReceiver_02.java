package com.team3.tcp;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceiver_02 {
	public final static int SOCKET_PORT = 5897; // you may change this

	public final static String FILE_TO_RECEIVED = System.getProperty("user.dir") + "/receive-files/a.png";

	public final static int FILE_SIZE = 6022386;
	// file size temporary hard coded
	// should bigger than the file to be downloaded

	public static void main(String[] args) throws IOException {
		int bytesRead;
		int current = 0;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		InputStream is = null;
		ServerSocket servSock = null;
		Socket sock = null;
		try {
			servSock = new ServerSocket(SOCKET_PORT);
			System.out.println("Waiting...");
			try {
				sock = servSock.accept();
				System.out.println("Accepted connection : " + sock);
				// receive file
				byte[] byteArray = new byte[FILE_SIZE];
				is = sock.getInputStream();
				fos = new FileOutputStream(FILE_TO_RECEIVED);
				bos = new BufferedOutputStream(fos);
				bytesRead = is.read(byteArray, 0, byteArray.length);
				current = bytesRead;

				do {
					bytesRead = is.read(byteArray, current, (byteArray.length - current));
					if (bytesRead >= 0)
						current += bytesRead;
				} while (bytesRead > -1);
				bos.write(byteArray, 0, current);
				bos.flush();
				System.out.println("File " + FILE_TO_RECEIVED + " downloaded (" + current + " bytes read)");

			} finally {
				if (fos != null)
					fos.close();
				if (bos != null)
					bos.close();
				if (sock != null)
					sock.close();
			}
		} finally {
			if (servSock != null)
				servSock.close();
		}

	}

}
