import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GUImain extends Panel{
    
    public static void main(String[] args) {
        //Create Frame
        JFrame mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(320, 480);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and add new panel
        Panel panelP = new Panel();
        mainFrame.add(panelP);
        
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(panelP, BorderLayout.CENTER);
        mainFrame.pack();
        
        //set visible
        mainFrame.setVisible(true);
    }

}
