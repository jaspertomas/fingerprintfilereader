
package utils.fileaccess;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * from: http://stackoverflow.com/questions/6135668/reading-integer-values-from-binary-file-using-java
 */
public final class BinaryFileReader {

    /**
     * Change these settings before running this class.
     */
    private static final String OUTPUT_FILE_NAME = "test.bin";

    /**
     * Run the example.
     */
    public static void main(String... aArgs){
        BinaryFileReader test = new BinaryFileReader();
        test.connectToFile(OUTPUT_FILE_NAME);
        System.out.println(test.readString());;
        System.out.println(test.readInt());;
        System.out.println(test.readDouble());;
        test.close();
    }

    String filename;
    FileInputStream fin;
    DataInputStream in;
    public boolean connectToFile(String filename) {
        this.filename = filename;
        eof=false;
        try {
            fin = new FileInputStream(filename);
            in = new DataInputStream(fin);
            return fin!=null;
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(BinaryFileReader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Integer readInt() {
        try {
            return in.readInt();
        } catch (IOException ex) {
            eof=true;
//            System.out.println("BinaryFileReader::readInt: end of file reached");
            return null;
        }
    }
    public Double readDouble() {
        try {
            return in.readDouble();
        } catch (IOException ex) {
            eof=true;
//            System.out.println("BinaryFileReader::readDouble: end of file reached");
            return null;
        }
    }    
    public String readString() {
        try {
            return in.readUTF();
        } catch (IOException ex) {
            eof=true;
//            System.out.println("BinaryFileReader::readString: end of file reached");
            return null;
        }
    }
//    private static void log(Object aThing) {
//        System.out.println(String.valueOf(aThing));
//    }
//
//    public static String padRight(String s, int n) {
//        return String.format("%1$-" + n + "s", s);
//    }
//
//    public static String padLeft(String s, int n) {
//        return String.format("%1$" + n + "s", s);
//    }
//
//    public static String bytesToStringUTFCustom(byte[] bytes) {
//        char[] buffer = new char[bytes.length];// >> 0
//        for (int i = 0; i < buffer.length; i++) {
//            int number = Byte.valueOf(bytes[i]).intValue();
//            buffer[i] = (char) number;
//        }
//        return new String(buffer);
//    }

    public void close() {
        try {
            in.close();
            fin.close();
        } catch (IOException ex) {
            Logger.getLogger(BinaryFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean eof=false;
    public boolean notEOF() {
        return !eof;
    }
}
