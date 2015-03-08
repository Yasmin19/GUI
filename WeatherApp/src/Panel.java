import com.teknikindustries.yahooweather.WeatherDisplay;
import com.teknikindustries.yahooweather.WeatherDoc;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Panel extends JPanel{
    
    private JButton homeButt, weekButt, landscapeButt, rotate;
    private JPanel homeView, weekView, landscapeView;
    private JLayeredPane lp;
    private JLabel mon, tue, wed, thur, fri, sat, sun, header, monT, tueT, wedT, thurT, friT, satT, sunT, back;
    private JLabel temp, unit, city, condition;
    private Font CrayonCrumble;
    
    public Panel() {
        Graphics g;
        
        lp = new JLayeredPane();
        lp.setPreferredSize(new Dimension(320, 480));
        
        homeView = homePanel();
        weekView = weekPanel();
        landscapeView = landscapePanel();
        
        add(lp);
        lp.add(homeView, new Integer(2));
        lp.add(weekView, new Integer(1));
        lp.add(landscapeView, new Integer(0));   
    }     
    
    //Home panel
    public JPanel homePanel(){
        homeView = new JPanel();
        homeView.setBackground(new Color(224,243,240));
        
        weekButt = new JButton("Week");
        landscapeButt = new JButton("Landscape");
        rotate = new JButton("");
        rotate.setOpaque(false);
        rotate.setContentAreaFilled(false);
        rotate.setBorderPainted(false);

        
        temp = new JLabel();
        unit = new JLabel();
        city = new JLabel();
        condition = new JLabel();
        
        //Get Weather info
        WeatherDoc doc = new WeatherDoc("44418", "c");
        WeatherDisplay disp = new WeatherDisplay();
        
        temp.setText(disp.getTemperature());
        unit.setText(disp.getTemperatureUnit() + "\n");
        city.setText(disp.getCity() + "\n");
        condition.setText(disp.getCondition() + "\n");

        /////Home View////////////////////////////
        
        homeView.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        
        ImageIcon snow = new ImageIcon(getClass().getResource("snow.png"));
        //ImageIcon rotate = new ImageIcon(getClass().getResource("rotate4.png"));
        Icon Rotate = new ImageIcon(getClass().getResource("rotate4.png"));
        
        /// First Coloumn ////
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NORTHWEST;
       // gc.fill = GridBagConstraints.HORIZONTAL;
        //homeView.add(new JLabel(rotate), gc);
        rotate.setIcon(Rotate);
        homeView.add(rotate, gc);
        
        /// Second Coloumn ////
        gc.gridx = 1;
        gc.gridy = 0;
        homeView.add(new JLabel(snow), gc);
        
        gc.gridx = 1;
        gc.gridy = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        homeView.add(temp, gc);
        homeView.add(unit, gc);
        homeView.add(city, gc);
        homeView.add(condition, gc);
        
        gc.gridx = 1;
        gc.gridy = 3;
        //gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.PAGE_END;
        homeView.add(weekButt, gc);
        homeView.add(landscapeButt, gc);
        
        weekButt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("Show Week");
                lp.setLayer(weekView, 2);
                lp.setLayer(homeView, 1);
                lp.setLayer(landscapeView, 0);
            }
        });
        landscapeButt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("Show Landscape");
                lp.setLayer(landscapeView, 2);
                lp.setLayer(homeView, 1);
                lp.setLayer(weekView, 0);
            }
        });
        rotate.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                rotate.setBackground(Color.GRAY);
            }
        });
        
        

        homeView.setBounds(0,0,320,480);
        return homeView;
    }
    
    //Weekly panel
    public JPanel weekPanel(){
        weekView = new JPanel();
 
        homeButt = new JButton("Home");
        weekView.setBackground(new Color(224,243,240));
        


        ///// Changing Background to gradient ////
        try{
            CrayonCrumble = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("DKCrayonCrumble"));
            GraphicsEnvironment ge =  GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("DKCrayonCrumble")));
        }
        catch (IOException | FontFormatException e) {
            //Handle exception
        }
         //f = font.deriveFont(12f);
        
        //////GRIDBAG LAYOUT FOR WEEK VIEW/////////
        weekView.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        header = new JLabel("My Week!");
        mon = new JLabel("Monday");
        tue = new JLabel("Tuesday");
        wed = new JLabel("Wednesday");
        thur = new JLabel("Thursday");
        fri = new JLabel("Friday");
        sat = new JLabel("Saturday");
        sun = new JLabel("Sunday");
        
        header.setFont(CrayonCrumble);
       // header.setFont(new Font("Arial", Font.PLAIN, 24));
        mon.setFont(new Font("Arial", Font.PLAIN, 20));
        tue.setFont(new Font("Arial", Font.PLAIN, 20));
        wed.setFont(new Font("Arial", Font.PLAIN, 20));
        thur.setFont(new Font("Arial", Font.PLAIN, 20));
        fri.setFont(new Font("Arial", Font.PLAIN, 20));
        sat.setFont(new Font("Arial", Font.PLAIN, 20));
        sun.setFont(new Font("Arial", Font.PLAIN, 20));
        
        monT = new JLabel("17");
        tueT = new JLabel("14");
        wedT = new JLabel("13");
        tueT = new JLabel("14");
        wedT = new JLabel("15");
        thurT = new JLabel("11");
        friT = new JLabel("12");
        satT = new JLabel("11");
        sunT = new JLabel("10");
       
        monT.setFont(new Font("Arial", Font.PLAIN, 28));
        tueT.setFont(new Font("Arial", Font.PLAIN, 28));
        wedT.setFont(new Font("Arial", Font.PLAIN, 28));
        thurT.setFont(new Font("Arial", Font.PLAIN, 28));
        friT.setFont(new Font("Arial", Font.PLAIN, 28));
        satT.setFont(new Font("Arial", Font.PLAIN, 28));
        sunT.setFont(new Font("Arial", Font.PLAIN, 28));
          
        
        ImageIcon cloud = new ImageIcon(getClass().getResource("cloudsun.png"));

       
        //// First Coloumn ///
        
        gc.gridx = 0;
        gc.gridy = 1;
        gc.ipady = 10;
        weekView.add(mon, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        weekView.add(tue, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        weekView.add(wed, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        weekView.add(thur, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        weekView.add(fri, gc);
        gc.gridx = 0;
        gc.gridy = 6;
        weekView.add(sat, gc);
        gc.gridx = 0;
        gc.gridy = 7;
        weekView.add(sun, gc);

        
        
        /// Second Coloumn ///
        gc.ipady = 1;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weighty = 0.05;
        weekView.add(header, gc);
        
        gc.gridx = 1;
        gc.gridy = 1;
        weekView.add(new JLabel(cloud), gc);
        
        gc.gridx = 1;
        gc.gridy = 2;
        weekView.add(new JLabel(cloud), gc);
        
        gc.gridx = 1;
        gc.gridy = 3;
        weekView.add(new JLabel(cloud), gc);
    
        gc.gridx = 1;
        gc.gridy = 4;
        weekView.add(new JLabel(cloud), gc);
        
        gc.gridx = 1;
        gc.gridy = 5;
        weekView.add(new JLabel(cloud), gc);
        
        gc.gridx = 1;
        gc.gridy = 6;
        weekView.add(new JLabel(cloud), gc);
        
        gc.gridx = 1;
        gc.gridy = 7;
        weekView.add(new JLabel(cloud), gc);

        
        gc.gridx = 1;
        gc.gridy = 8;
        gc.anchor = GridBagConstraints.PAGE_END;
        gc.insets = new Insets(10,0,0,0);
        weekView.add(homeButt, gc);

        
        /// Third Coloumn ///
        gc.weightx = 0.5;
        
        gc.gridx = 2;
        gc.gridy = 1;
        gc.ipady = 10;
        weekView.add(monT, gc);
        gc.gridx = 2;
        gc.gridy = 2;
        weekView.add(tueT, gc);
        gc.gridx = 2;
        gc.gridy = 3;
        weekView.add(wedT, gc);
        gc.gridx = 2;
        gc.gridy = 4;
        weekView.add(thurT, gc);
        gc.gridx = 2;
        gc.gridy = 5;
        weekView.add(friT, gc);
        gc.gridx = 2;
        gc.gridy = 6;
        weekView.add(satT, gc);
        gc.gridx = 2;
        gc.gridy = 7;
        weekView.add(sunT, gc);
        
        
        
   
        
        homeButt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("Show Home");
                lp.setLayer(weekView, 1);
                lp.setLayer(homeView, 2);
                lp.setLayer(landscapeView, 0);
            }
        });
        
     
        weekView.setBounds(0,0,320,480);
        return weekView;
    }

    //Landscape view
    public JPanel landscapePanel(){
        lp.setPreferredSize(new Dimension(320, 480));

        landscapeView = new JPanel();
        landscapeView.setLayout(new FlowLayout(FlowLayout.LEFT));
        landscapeView.setBackground(Color.GRAY);
        
        homeButt = new JButton("Home");
        homeButt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("Show Home");
                lp.setLayer(weekView, 1);
                lp.setLayer(homeView, 2);
                lp.setLayer(landscapeView, 0);
            }
        });
        
        landscapeView.add(homeButt);
        landscapeView.setBounds(0,0,480,320);
        return landscapeView;
    }
}
