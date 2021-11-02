package com.abc.io;

import java.io.*;

/**
 *
 */
public class DataInputOutputFun {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            String filename = "data.hex";
            createFile(filename);
            readFile(filename);
        } catch (IOException x) {
            x.printStackTrace();
        }

    }

    private static void createFile(String filename) throws IOException {

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        out.writeUTF("hello");
        out.writeInt(43);
        out.writeBoolean(false);
        out.writeDouble(0.56);
        out.flush();
        out.close();

        System.out.println("finished writing to " + filename);
    }

    private static void readFile(String filename) throws FileNotFoundException, IOException {

        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        System.out.println("in.readUTF()= " + in.readUTF());
        System.out.println("in.readUTF()= " + in.readInt());
        System.out.println("in.readUTF()= " + in.readDouble());
        // System.out.println("in.readUTF()= " + in.readBoolean());

        in.close();
    }

}
