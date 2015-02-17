import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel{
    
    private JButton b1;
    private JButton b2;
    
    public Panel(){
        
        b1 = homeButton("!!!!!");    
        b2 = weeklyButton("222222");
        
        add(b1);
        add(b2);
    }
    
    public JButton homeButton(String text) {
        JButton b = new JButton(text);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("Show home");
            }
        });
        return b;
    }
    
    public JButton weeklyButton(String text) {
        JButton b = new JButton(text);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("Show weekly");
            }
        });
        return b;
    }
}
