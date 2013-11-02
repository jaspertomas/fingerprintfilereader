/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.windows;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
public final class NewClass {

    /**
     * Change these settings before running this class.
     */
    private static final String INPUT_FILE_NAME = "/Users/jaspertomas/1.db";
    private static final String OUTPUT_FILE_NAME = "/Users/jaspertomas/1a.db";

    /**
     * Run the example.
     */
    public static void main(String... aArgs) throws IOException {
        NewClass test = new NewClass();
//    //read in the bytes
//    byte[] fileContents = test.read(INPUT_FILE_NAME);
//    //test.readAlternateImpl(INPUT_FILE_NAME);
//    //write it back out to a different file name
//    test.write(fileContents, OUTPUT_FILE_NAME);

        String a = "hello World";
        a=padLeft(a, 20);
//    Integer i=1666661;
    byte[] bytes=new byte[20];
//    fileContents[0]=i.byteValue();
//    filecontents[1]=a.getBytes();
//    test.write(fileContents, OUTPUT_FILE_NAME);

//        OutputStream output = null;
//        try {
//            output = new BufferedOutputStream(new FileOutputStream(OUTPUT_FILE_NAME));
//            output.write(a.getBytes());
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            output.close();
//        }
        
        
//        char[] chars = new char[20];
//        BufferedReader brdr;
//        FileInputStream din;
//        try {
//            InputStream input = null;
//            try {
//                int totalBytesRead = 0;
//                din = new FileInputStream(OUTPUT_FILE_NAME);
//                brdr = new BufferedReader(new InputStreamReader(din), 300);
//                totalBytesRead=brdr.read(chars);
//                System.out.println(chars);
//
//                log("Num bytes read: " + totalBytesRead);
//            } finally {
//                log("Closing input stream.");
////                din.close();
//            }
//        } catch (FileNotFoundException ex) {
//            log("File not found.");
//        } catch (IOException ex) {
//            log(ex);
//        }

        try {
            InputStream input = null;
            try {
                int totalBytesRead = 0;
                input = new BufferedInputStream(new FileInputStream(OUTPUT_FILE_NAME));
                totalBytesRead=input.read(bytes);
                System.out.println(bytesToStringUTFCustom(bytes));
//                while (totalBytesRead < result.length) {
//                    int bytesRemaining = result.length - totalBytesRead;
//                    //input.read() returns -1, 0, or more :
//                    int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
//                    if (bytesRead > 0) {
//                        totalBytesRead = totalBytesRead + bytesRead;
//                    }
//                }
                /*
                 the above style is a bit tricky: it places bytes into the 'result' array; 
                 'result' is an output parameter;
                 the while loop usually has a single iteration only.
                 */
                log("Num bytes read: " + totalBytesRead);
            } finally {
                log("Closing input stream.");
                input.close();
            }
        } catch (FileNotFoundException ex) {
            log("File not found.");
        } catch (IOException ex) {
            log(ex);
        }
        
    }

    /**
     * Read the given binary file, and return its contents as a byte array.
     */
    byte[] read(String aInputFileName) {
        log("Reading in binary file named : " + aInputFileName);
        File file = new File(aInputFileName);
        log("File size: " + file.length());
        byte[] result = new byte[(int) file.length()];
        try {
            InputStream input = null;
            try {
                int totalBytesRead = 0;
                input = new BufferedInputStream(new FileInputStream(file));
                while (totalBytesRead < result.length) {
                    int bytesRemaining = result.length - totalBytesRead;
                    //input.read() returns -1, 0, or more :
                    int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
                    if (bytesRead > 0) {
                        totalBytesRead = totalBytesRead + bytesRead;
                    }
                }
                /*
                 the above style is a bit tricky: it places bytes into the 'result' array; 
                 'result' is an output parameter;
                 the while loop usually has a single iteration only.
                 */
                log("Num bytes read: " + totalBytesRead);
            } finally {
                log("Closing input stream.");
                input.close();
            }
        } catch (FileNotFoundException ex) {
            log("File not found.");
        } catch (IOException ex) {
            log(ex);
        }
        return result;
    }

    /**
     * Write a byte array to the given file. Writing binary data is
     * significantly simpler than reading it.
     */
    void write(byte[] aInput, String aOutputFileName) {
        log("Writing binary file...");
        try {
            OutputStream output = null;
            try {
                output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
                output.write(aInput);
            } finally {
                output.close();
            }
        } catch (FileNotFoundException ex) {
            log("File not found.");
        } catch (IOException ex) {
            log(ex);
        }
    }

    /**
     * Read the given binary file, and return its contents as a byte array.
     */
    byte[] readAlternateImpl(String aInputFileName) {
        log("Reading in binary file named : " + aInputFileName);
        File file = new File(aInputFileName);
        log("File size: " + file.length());
        byte[] result = null;
        try {
            InputStream input = new BufferedInputStream(new FileInputStream(file));
            result = readAndClose(input);
        } catch (FileNotFoundException ex) {
            log(ex);
        }
        return result;
    }

    /**
     * Read an input stream, and return it as a byte array. Sometimes the source
     * of bytes is an input stream instead of a file. This implementation closes
     * aInput after it's read.
     */
    byte[] readAndClose(InputStream aInput) {
        //carries the data from input to output :    
        byte[] bucket = new byte[32 * 1024];
        ByteArrayOutputStream result = null;
        try {
            try {
                //Use buffering? No. Buffering avoids costly access to disk or network;
                //buffering to an in-memory stream makes no sense.
                result = new ByteArrayOutputStream(bucket.length);
                int bytesRead = 0;
                while (bytesRead != -1) {
                    //aInput.read() returns -1, 0, or more :
                    bytesRead = aInput.read(bucket);
                    if (bytesRead > 0) {
                        result.write(bucket, 0, bytesRead);
                    }
                }
            } finally {
                aInput.close();
                //result.close(); this is a no-operation for ByteArrayOutputStream
            }
        } catch (IOException ex) {
            log(ex);
        }
        return result.toByteArray();
    }

    private static void log(Object aThing) {
        System.out.println(String.valueOf(aThing));
    }

    public static String padRight(String s, int n) {
         return String.format("%1$-" + n + "s", s);  
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);  
    }
public static String bytesToStringUTFCustom(byte[] bytes) {
 char[] buffer = new char[bytes.length];// >> 0
 for(int i = 0; i < buffer.length; i++) {
     int number=Byte.valueOf(bytes[i]).intValue();
     buffer[i]=(char)number;
 }
 return new String(buffer);
}
}
