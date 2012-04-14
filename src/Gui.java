import javax.swing.*;

/** gui.java
 * By Samuel Baxter
 * COMP2911
 * 07/04/2012
 */

public class Gui {
    
    public static void main (String[] ARGV) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                Four442Gui gui = new Four442Gui();
                gui.setVisible(true);
                
            }
        });
        
    }

}
