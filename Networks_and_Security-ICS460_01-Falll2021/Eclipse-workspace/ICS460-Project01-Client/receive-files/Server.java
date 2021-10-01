
//Server.java:
import java.io.*;
import java.net.*;

class Server
{
    public static void main(String args[]) throws IOException
    {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] recData = new byte[1024];
        int i =0;

        FileWriter file = new FileWriter("C:\\Users\\ayushi\\Documents\\Semester 2\\Misc\\setups\\eclipse\\ip_1\\ip_second\\src\\out.txt");
        PrintWriter out = new PrintWriter(file);


        //BufferedOutputStream bos = new BufferedOutputStream(fos);

        while(true)
        {
            //PrintWriter out = new PrintWriter(file);

            DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
            serverSocket.receive(recPacket);
            String line = new String(recPacket.getData());
            System.out.println("\n Data: " + line);
            out.println(line);
            System.out.println("\nPacket" + ++i + " written to file\n");
            out.flush();
        }
        
        while(true)
        {
            DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
            serverSocket.receive(recPacket);
            System.out.println("\n Packet length: " + recPacket.getLength());
            out.write((recPacket.getData(), 0, recPacket.getLength());
            System.out.println("\nPacket" + ++i + " written to file\n");
            out.flush();
        }
    }
}