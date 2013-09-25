/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint;

import fingerprint.windows.MainFrame;

/**
 *
 * @author jaspertomas
 */
public class Fingerprint {

    MainFrame mainframe;
    
    
    public Fingerprint()
    {
        mainframe=new MainFrame();
        mainframe.setVisible(true);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Fingerprint fingerprint=new Fingerprint();
    }
}
