
package utils.fileaccess;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * from: http://stackoverflow.com/questions/6135668/reading-integer-values-from-binary-file-using-java
 */
public final class BinaryFileWriter {


    private static final String OUTPUT_FILE_NAME = "test.bin";

    public static void main(String... aArgs){
        BinaryFileWriter test = new BinaryFileWriter();
        test.connectToFile(OUTPUT_FILE_NAME);
        test.writeString("  hello world-- ");//26 chars
        test.writeInt(200);
        test.writeDouble(5.5);
        test.close();
    }

    String filename;
    FileOutputStream fout;
    BufferedOutputStream buffOut;
    DataOutputStream out;
    
    public boolean connectToFile(String filename) {
        this.filename = filename;
        try {
            fout = new FileOutputStream(filename);
            buffOut = new BufferedOutputStream(fout);
            out = new DataOutputStream(fout);
            return (fout != null && buffOut != null && out != null);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
            return false;
        }
    }

    public boolean writeInt(Integer i) {
        try {
            out.writeInt(i);
            return true;
        } catch (IOException ex) {
            System.out.println("writeInt: error writing integer "+i.toString()+" to file");
            return false;
        }
    }
    public boolean writeDouble(Double d) {
        try {
            out.writeDouble(d);
            return true;
        } catch (IOException ex) {
            System.out.println("writeDouble: error writing double "+d.toString()+" to file");
            return false;
        }
    }

    public boolean writeString(String s) {
        try {
            out.writeUTF(s);
            return true;
        } catch (IOException ex) {
            System.out.println("writeInt: error writing string "+s+" to file");
            return false;
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
            out.close();
            fout.close();
            buffOut.close();
        } catch (IOException ex) {
            Logger.getLogger(BinaryFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
