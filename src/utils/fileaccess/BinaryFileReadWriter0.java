/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.fileaccess;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Converting binary data into different forms.
 *
 * <P>Reads binary data into memory, and writes it back out. (If your're
 * actually copying a file, there are better ways to do this.)
 *
 * <P>Buffering is used when reading and writing files, to minimize the number
 * of interactions with the disk.
 */
public final class BinaryFileReadWriter0 {
//
//    /**
//     * Change these settings before running this class.
//     */
//    private static final String INPUT_FILE_NAME = "/Users/jaspertomas/1.db";
//    private static final String OUTPUT_FILE_NAME = "/Users/jaspertomas/1a.db";
//
//    /**
//     * Run the example.
//     */
//    public static void main(String... aArgs) throws IOException {
//        BinaryFileReadWriter0 test = new BinaryFileReadWriter0();
//
////        String a = "hello World";
////        a = padLeft(a, 20);
//        
//        boolean connected=test.connectToFile(OUTPUT_FILE_NAME);
//        if(connected)
//        {
//            System.out.println(test.readString(10));
//            System.out.println(test.readString(10));
//        }
//        test.close();
////    Integer i=1666661;
//        
//
//    }
//    
//    InputStream input = null;
//    String filename;
//    boolean connectToFile(String filename)
//    {
//        this.filename=filename;
//        try {
//            input = new BufferedInputStream(new FileInputStream(filename));
//            return (input!=null);
//        } catch (FileNotFoundException ex) {
//            log("File not found.");
//            return false;
//        }           
//    }
//    String readString(int length) {
//        try {
//            byte[] bytes = new byte[length];
//            String s="";
//            input.read(bytes);
//            return bytesToStringUTFCustom(bytes);
//        } catch (IOException ex) {
//            log("myReadString: IOException");
//            return null;
//        }
//    }
//    String readInt() {
//        try {
//            byte[] bytes = new byte[4];
//            String s="";
//            input.read(bytes);
//            return bytesToStringUTFCustom(bytes);
//        } catch (IOException ex) {
//            log("myReadString: IOException");
//            return null;
//        }
//    }    
//    
//
//    /**
//     * Read the given binary file, and return its contents as a byte array.
//     */
////    byte[] read(String aInputFileName) {
////        log("Reading in binary file named : " + aInputFileName);
////        File file = new File(aInputFileName);
////        log("File size: " + file.length());
////        byte[] result = new byte[(int) file.length()];
////        try {
////            InputStream input = null;
////            try {
////                int totalBytesRead = 0;
////                input = new BufferedInputStream(new FileInputStream(file));
////                while (totalBytesRead < result.length) {
////                    int bytesRemaining = result.length - totalBytesRead;
////                    //input.read() returns -1, 0, or more :
////                    int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
////                    if (bytesRead > 0) {
////                        totalBytesRead = totalBytesRead + bytesRead;
////                    }
////                }
////
////                log("Num bytes read: " + totalBytesRead);
////            } finally {
////                log("Closing input stream.");
////                input.close();
////            }
////        } catch (FileNotFoundException ex) {
////            log("File not found.");
////        } catch (IOException ex) {
////            log(ex);
////        }
////        return result;
////    }
//
//    /**
//     * Write a byte array to the given file. Writing binary data is
//     * significantly simpler than reading it.
//     */
//    void write(byte[] aInput, String aOutputFileName) {
//        log("Writing binary file...");
//        try {
//            OutputStream output = null;
//            try {
//                output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
//                output.write(aInput);
//            } finally {
//                output.close();
//            }
//        } catch (FileNotFoundException ex) {
//            log("File not found.");
//        } catch (IOException ex) {
//            log(ex);
//        }
//    }
//
//    /**
//     * Read the given binary file, and return its contents as a byte array.
//     */
////    byte[] readAlternateImpl(String aInputFileName) {
////        log("Reading in binary file named : " + aInputFileName);
////        File file = new File(aInputFileName);
////        log("File size: " + file.length());
////        byte[] result = null;
////        try {
////            InputStream input = new BufferedInputStream(new FileInputStream(file));
////            result = readAndClose(input);
////        } catch (FileNotFoundException ex) {
////            log(ex);
////        }
////        return result;
////    }
//
//    /**
//     * Read an input stream, and return it as a byte array. Sometimes the source
//     * of bytes is an input stream instead of a file. This implementation closes
//     * aInput after it's read.
//     */
////    byte[] readAndClose(InputStream aInput) {
////        //carries the data from input to output :    
////        byte[] bucket = new byte[32 * 1024];
////        ByteArrayOutputStream result = null;
////        try {
////            try {
////                //Use buffering? No. Buffering avoids costly access to disk or network;
////                //buffering to an in-memory stream makes no sense.
////                result = new ByteArrayOutputStream(bucket.length);
////                int bytesRead = 0;
////                while (bytesRead != -1) {
////                    //aInput.read() returns -1, 0, or more :
////                    bytesRead = aInput.read(bucket);
////                    if (bytesRead > 0) {
////                        result.write(bucket, 0, bytesRead);
////                    }
////                }
////            } finally {
////                aInput.close();
////                //result.close(); this is a no-operation for ByteArrayOutputStream
////            }
////        } catch (IOException ex) {
////            log(ex);
////        }
////        return result.toByteArray();
////    }
//
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
//
//    private void close() {
//        try {
//            input.close();
//        } catch (IOException ex) {
//            Logger.getLogger(BinaryFileReadWriter0.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
