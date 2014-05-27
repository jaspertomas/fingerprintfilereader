/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.fileaccess;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author jaspertomas
 */
public class PdfWriter {
  //private static String FILE = "./FirstPdf.pdf";
//  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
//      Font.BOLD);
//  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
//      Font.NORMAL, BaseColor.RED);
//  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
//      Font.BOLD);
//  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
//      Font.BOLD);

  public static void write(String path, String text) {
    try {
//      Document document = new Document(PageSize.LETTER);
//      document.setMargins(36, 36, 0, 0);

        Rectangle pagesize = new Rectangle(612f, 936f);//8x13 inches
        Document document = new Document(pagesize, 36f, 36f, 72f, 36f);        
        //Document document = new Document(PageSize.LEGAL);
      com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(path));
      document.open();
      addMetaData(document);
      //addTitlePage(document);
      addContent(document,text);
      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void addMetaData(Document document) {
    document.addTitle("Payroll");
    document.addAuthor("Jasper Tomas");
    document.addCreator("Jasper Tomas");
  }

  private static void addContent(Document document, String text) throws DocumentException {

      text=text.replace("\t", "        ");
      //text=text.replace("\r", "");
      text=text.replace("\n\n", "\n");
      String[] segments=text.split("==============================");
      
      //analyze each segment: count the number of lines in each
      //if 23 lines or less, put in short list and print 2 at a time
      //if more than 23 lines, print in separate page
      
      ArrayList<String> shortlist=new ArrayList<String>();
      ArrayList<String> longlist=new ArrayList<String>();
      String[] lines;
      int linecount=0;
      String padding;
        for(String segment:segments)
        {
            segment=segment.trim();
            lines=segment.split("\n");
            if(lines.length<=1);
            else if(lines.length<=28)
            {
                linecount=lines.length;
                for(padding="";linecount<28;linecount++)padding+="\n";
                shortlist.add(segment+padding);
            }
            else
                longlist.add(segment);
        }
      
        Paragraph paragraph;
        for(String segment:longlist)
        {
            paragraph = new Paragraph(segment);
            //thanks to http://itext-general.2136553.n4.nabble.com/Line-spacing-in-IText-td2149280.html
            paragraph.setLeading(0f, 1.2f); 
            document.add(paragraph);
            document.newPage();
        }

        for(int i=0;i<shortlist.size();i+=2)
        {
            if(i+1>=shortlist.size())
            {
                paragraph = new Paragraph(
                        shortlist.get(i)
                        );
            }
            else
            {
                paragraph = new Paragraph(
                        shortlist.get(i)
                        +"\n==============================\n"
                        +shortlist.get(i+1)
                        );
            }
            
            //thanks to http://itext-general.2136553.n4.nabble.com/Line-spacing-in-IText-td2149280.html
            paragraph.setLeading(0f, 1.2f); 
            document.add(paragraph);
            document.newPage();
        }
  }
}
