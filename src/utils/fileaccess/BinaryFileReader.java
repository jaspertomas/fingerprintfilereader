
package utils.fileaccess;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Adjustments;

/**
 * from: http://stackoverflow.com/questions/6135668/reading-integer-values-from-binary-file-using-java
 */
public final class BinaryFileReader {

    /**
     * Change these settings before running this class.
     */
    private static final String OUTPUT_FILE_NAME = "test.bin";
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

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
    public Date readDate() throws ParseException {        
        return dateFormat.parse(readString());
    }    
    public Time readTime() throws ParseException {        
        return new Time(timeFormat.parse(readString()).getTime());
    }    

    
    public Boolean readBoolean() {
        return Boolean.valueOf(readString());
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
