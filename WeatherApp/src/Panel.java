import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Panel extends JPanel{
    
    private JButton homeButt, weekButt, landscapeButt;
    private JPanel homeView, weekView, landscapeView, leftSide, rightSide, bottom;;
    private JLayeredPane lp;
    private JLabel header, back, advice;
    private JLabel temp, condition, question, image;
    private Font CrayonCrumble;
    private JTextPane showFacts, showAdvice;
    
    //Get Weather info
    WeatherAPI weather = new WeatherAPI("44418");
    
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
       
        /*********IMPORTING FONT**************/
        
        CrayonCrumble = getFont1();
        Font crayon90pt = CrayonCrumble.deriveFont(90f);
        Font crayon50pt = CrayonCrumble.deriveFont(50f);   
        Font crayon25pt = CrayonCrumble.deriveFont(25f);
     
        /**************************************/
        
        
        /********IMPORTING IMAGES TO BE USED**************/
        
        ImageIcon snow = new ImageIcon(getClass().getResource("snow.png"));
        Icon Rotate = new ImageIcon(getClass().getResource("rotate.png"));
        Icon Next = new ImageIcon(getClass().getResource("next.png"));
        
        /**************************************************/  
        
        
        /********CREATING BUTTONS AND LABELS NEEDED********/
        
        weekButt = new JButton("");
        weekButt.setOpaque(false);
        weekButt.setContentAreaFilled(false);
        weekButt.setBorderPainted(false);
        
        landscapeButt = new JButton("");
        landscapeButt.setOpaque(false);
        landscapeButt.setContentAreaFilled(false);
        landscapeButt.setBorderPainted(false);

        image = new JLabel(snow);
        question = new JLabel("Did you know?", SwingConstants.CENTER);
         
        landscapeButt.setIcon(Rotate);
        weekButt.setIcon(Next);
        
        /*****************************************************/
   
        
        /**********RETRIEVING/SETTING WEATHER INFO*******************/
       
        temp = new JLabel(weather.weatherForecastList.get(0).highTemp);
        condition = new JLabel(weather.weatherForecastList.get(0).condition);
        question.setFont(crayon50pt);
        
        temp.setFont(crayon90pt);
        temp.setText(temp.getText() + "c");
        condition.setFont(crayon25pt);
    
        String[] facts = getInfo();
    
        showFacts = new JTextPane();
      
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        showFacts.setParagraphAttributes(attribs, true);
        showFacts.setText(facts[0]);
        showFacts.setFont(crayon25pt);
 
        /********************************************************/
        
      
        
        /*********************BORDER LAYOUT***********************/
        
        homeView.setLayout(new BorderLayout());
        
        //BorderLayout within BorderLayout
        
            leftSide = new JPanel(new BorderLayout());
            rightSide = new JPanel(new BorderLayout());
            bottom = new JPanel(new BorderLayout());
            
            
            leftSide.add(landscapeButt, BorderLayout.NORTH);
        
            rightSide.add(temp, BorderLayout.SOUTH);

            bottom.add(weekButt,BorderLayout.SOUTH); 
            bottom.add(showFacts,BorderLayout.CENTER);
            bottom.add(question,BorderLayout.NORTH);
        //
        
       
        //Outer BorderLayout
            homeView.add(leftSide, BorderLayout.WEST);
            homeView.add(rightSide, BorderLayout.EAST);
            homeView.add(image, BorderLayout.CENTER);
            homeView.add(bottom, BorderLayout.SOUTH);
        //
            
        //Set panels to transparent
            rightSide.setOpaque(false);
            leftSide.setOpaque(false);
            bottom.setOpaque(false);
            showFacts.setOpaque(false);
         //  
        /*************************************************/
            
        
        /****************ACTION LISTENERS*****************/
            
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
        image.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) 
            {
               image.setBorder(BorderFactory.createLineBorder(Color.gray));
               advice = new JLabel();
                showAdvice = new JTextPane();
                //showAdvice.setText("Don't forget your hat and stuff");
               //homeView.add(showAdvice, BorderLayout.EAST);
              
            }
        });
        
        image.addMouseListener(new MouseAdapter() {
        public void mouseExited(MouseEvent e) 
            {
                image.setBorder(null);
                //advice.setText("");
                //showAdvice.setText("");
            }
        });
        
        /****************************************************/
        
        homeView.setBounds(0,0,320,480);
        return homeView;
    }
    
    
    
    
    //Weekly panel
    public JPanel weekPanel(){
        
        weekView = new JPanel();
        homeButt = new JButton("Home");
        weekView.setBackground(new Color(224,243,240));
        

        Font crayon50pt = CrayonCrumble.deriveFont(50f);
        Font crayon30pt = CrayonCrumble.deriveFont(30f);
        
        
        ImageIcon cloud = new ImageIcon(getClass().getResource("cloudsun.png"));
        Icon Back = new ImageIcon(getClass().getResource("back.png"));
        Icon Rotate = new ImageIcon(getClass().getResource("rotate.png"));
        
        homeButt.setIcon(Back);
        homeButt.setOpaque(false);
        homeButt.setContentAreaFilled(false);
        homeButt.setBorderPainted(false);
        
        landscapeButt = new JButton("");
        landscapeButt.setOpaque(false);
        landscapeButt.setContentAreaFilled(false);
        landscapeButt.setBorderPainted(false);
        landscapeButt.setIcon(Rotate);
        
        //////GRIDBAG LAYOUT FOR WEEK VIEW/////////
        
        weekView.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        header = new JLabel("My Week");
        header.setFont(crayon50pt);
        
        /// Rotate Button ///
        gc.gridx = 0;
        gc.gridy = 0;
        //gc.anchor = GridBagConstraints.FIRST_LINE_START;
        weekView.add(landscapeButt, gc);
        
        
        
        /// Heading of Week Panel ///
        gc.ipady = 1;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weighty = 0.05;
        weekView.add(header, gc);
        
        
        /// Showing weather for the next 5 days ///
        
        JLabel[] days = new JLabel[5];
        JLabel[] daysT = new JLabel [5];
        
           for (int i=0; i<days.length; i++) { 
               days[i] = new JLabel(weather.weatherForecastList.get(i).day);  
               daysT[i] = new JLabel(weather.weatherForecastList.get(i).highTemp); 
               
               days[i].setFont(crayon30pt);
               daysT[i].setFont(crayon30pt);
       
           }
                for(int y=1; y<=days.length; y++) {
                    //First Coloumn
                    gc.gridx = 0;
                    gc.gridy = y;
                    weekView.add(days[y-1], gc);
                    
                    //Second Coloumn
                    gc.gridx = 1;
                    gc.gridy = y;
                    weekView.add(new JLabel(cloud), gc);
                    
                    //Third Coloumn
                    gc.gridx = 2;
                    gc.gridy = y;
                    weekView.add(daysT[y-1], gc);
                } 

 
        /// Adding home button ///
        
        gc.gridx = 1;
        gc.gridy = 8;
        gc.anchor = GridBagConstraints.PAGE_END;
        gc.insets = new Insets(10,0,0,0);
        weekView.add(homeButt, gc);

     
        
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
    
    
    public Font getFont1() {
        try{
            InputStream inputStream = new BufferedInputStream(getClass().getResourceAsStream("DKCrayonCrumble.ttf"));
            CrayonCrumble = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } 
        catch (IOException | FontFormatException e) {
            System.out.println("Crayon Crumble font could not be loaded");
        }
        return CrayonCrumble;
    }
    

     
    public String[] getInfo() {
        
       String[] facts = new String[10];
       
         facts[0] = "Snowflakes are made up of crystals of ice found in bits of dust in the air!"
                   + " They start out very small and grow.";
         facts[1] = "";
         facts[2] = "";
         facts[3] = "";
         facts[4] = "";
         facts[5] = "";
         
         
       return facts;
    }
}
