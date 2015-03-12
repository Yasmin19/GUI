
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class GUImain extends JFrame{

	//declaration
	public static CardLayout card = new CardLayout();
	public static JPanel container = new JPanel();
	public static JPanel homep = new JPanel();
	public static JPanel weekp = new JPanel();


        private JButton homeButt, weekButt, landscapeButt;
        private JPanel leftSide, rightSide, bottom;
        private JLabel header, back, advice;
        private JLabel temp, condition, question, imageLab;
        private Font CrayonCrumble;
        private JTextPane showFacts, showAdvice;
        private ImageIcon image, icon;
        
  

         //Get Weather info
         WeatherAPI weather = new WeatherAPI("44418");
    
	public GUImain() {
            
                //setting the null layout for all panels 
		//and set the cardlayout option in the layout of container
		container.setLayout(card);
		homep.setLayout(null);
		weekp.setLayout(null);
         
                
                homep = homePanel();
                weekp = weekPanel();
                
                //frame
		setSize(346, 535);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
		//add panels to the container
		//gave each one a name
		container.add(homep, "home panel");
		container.add(weekp, "week panel");

		//Show home
		card.show(container, "home panel");

	}
        
        
        
        public JPanel homePanel(){
            
        homep = new JPanel();
        homep.setBackground(new Color(224,243,240));
       
        /*********IMPORTING FONT**************/
        
        CrayonCrumble = getFont1();
        Font crayon80pt = CrayonCrumble.deriveFont(80f);
        Font crayon50pt = CrayonCrumble.deriveFont(50f);   
        Font crayon25pt = CrayonCrumble.deriveFont(25f);
     
        /**************************************/
        
        
        /********IMPORTING IMAGES TO BE USED**************/
   
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

      
        landscapeButt.setIcon(Rotate);
        weekButt.setIcon(Next);
        
        /*****************************************************/
   
        
        /**********RETRIEVING/SETTING WEATHER INFO*******************/
       
        temp = new JLabel(weather.weatherForecastList.get(0).highTemp);
        condition = new JLabel(weather.weatherForecastList.get(0).condition);
        
        
        temp.setFont(crayon80pt);
        temp.setText(temp.getText() + "c");
        condition.setFont(crayon25pt);
    
        //ImageIcon snow = new ImageIcon(getClass().getResource("snow.png"));
        
        image = conditions(condition.getText(), "home");
        imageLab = new JLabel(image);
        //image = new JLabel(snow);
        
        question = new JLabel("Did you know?", SwingConstants.CENTER);
        question.setFont(crayon50pt);
        
        String[] facts = getInfo();
    
        showFacts = new JTextPane();
      
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        showFacts.setParagraphAttributes(attribs, true);
        showFacts.setText(facts[0]);
        showFacts.setFont(crayon25pt);
 
        /********************************************************/
        
      
        
        /*********************BORDER LAYOUT***********************/
        
        homep.setLayout(new BorderLayout());
        
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
            homep.add(leftSide, BorderLayout.WEST);
            homep.add(rightSide, BorderLayout.EAST);
            homep.add(imageLab, BorderLayout.CENTER);
            homep.add(bottom, BorderLayout.SOUTH);
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
                public void actionPerformed(ActionEvent e) {
                    card.show(container, "week panel");
                    if (homep.getSize().width <= 350)
                        setSize(346,545);
                    else
                        setSize(545, 346);
                    }
                });
	    landscapeButt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //card.show(container, "land panel");
                    if (homep.getSize().width <= 350)
                        setSize(545,346);
                    else
                        setSize(346,545);
                    }
                });
              imageLab.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) 
                    {
                       imageLab.setBorder(BorderFactory.createLineBorder(Color.gray));
                       advice = new JLabel();
                        showAdvice = new JTextPane();
                        //showAdvice.setText("Don't forget your hat and stuff");
                       //homeView.add(showAdvice, BorderLayout.EAST);

                    }
                });

                imageLab.addMouseListener(new MouseAdapter() {
                public void mouseExited(MouseEvent e) 
                    {
                        imageLab.setBorder(null);
                        //advice.setText("");
                        //showAdvice.setText("");
                    }
                });
                
             /************************************************************/
                
            return homep;
        }
        
        
       
        
    //Weekly panel
    public JPanel weekPanel(){
        
        JPanel daysPanel = new JPanel();
        JPanel iconsPanel = new JPanel();
        JPanel tempPanel = new JPanel();
        JPanel headingPanel = new JPanel();
        
        daysPanel.setLayout(new BoxLayout(daysPanel, BoxLayout.Y_AXIS));
        iconsPanel.setLayout(new BoxLayout(iconsPanel, BoxLayout.Y_AXIS));;
        tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
        
        headingPanel.setLayout(new BorderLayout());
        weekp.setLayout(new BorderLayout());
        weekp.setBackground(new Color(224,243,240));
        
        Font crayon50pt = CrayonCrumble.deriveFont(50f);
        Font crayon30pt = CrayonCrumble.deriveFont(30f);
        
        Icon Back = new ImageIcon(getClass().getResource("back.png"));
        Icon Rotate = new ImageIcon(getClass().getResource("rotate.png"));
        
        
        homeButt = new JButton("   ");
        homeButt.setIcon(Back);
        homeButt.setOpaque(false);
        homeButt.setContentAreaFilled(false);
        homeButt.setBorderPainted(false);
        
        landscapeButt = new JButton("");
        landscapeButt.setOpaque(false);
        landscapeButt.setContentAreaFilled(false);
        landscapeButt.setBorderPainted(false);
        landscapeButt.setIcon(Rotate);
        
        
        //////COMBO OF BORDER LAYOUT AND BOX LAYOUT/////////

        header = new JLabel("  My Week");
        header.setFont(crayon50pt);
        
        
        JLabel[] days = new JLabel[5];
        JLabel[] daysT = new JLabel [5];
        String[] daysCond = new String[5];
        
           for (int i=0; i<days.length; i++) { 
               days[i] = new JLabel(weather.weatherForecastList.get(i).day); 
               daysCond[i] = weather.weatherForecastList.get(i).condition;
               daysT[i] = new JLabel(weather.weatherForecastList.get(i).highTemp); 
               
               days[i].setFont(crayon30pt);
               daysT[i].setFont(crayon30pt);
       
           }
                for(int y=1; y<=days.length; y++) {
                    //First Coloumn
                    
                    daysPanel.add(days[y-1]);
                    daysPanel.add(Box.createHorizontalGlue());
                    daysPanel.add(Box.createVerticalGlue());
                    daysPanel.add(Box.createRigidArea(new Dimension(20,20)));
                    
                    //Second Coloumn
                    icon = conditions(daysCond[y-1], "week");
                    iconsPanel.add(new JLabel(icon));
                    iconsPanel.add(Box.createHorizontalGlue());
                    iconsPanel.add(Box.createVerticalGlue());
                    iconsPanel.add(Box.createRigidArea(new Dimension(50,15)));
                    
                    //System.out.println(weather.weatherForecastList.get(y-1).condition);
                    
                    
                    //Third Coloumn
                    tempPanel.add(daysT[y-1]);
                    tempPanel.add(Box.createHorizontalGlue());
                    tempPanel.add(Box.createVerticalGlue());
                    tempPanel.add(Box.createRigidArea(new Dimension(20,20)));
                    
                }  

        
        headingPanel.add(header, BorderLayout.CENTER);
        headingPanel.add(landscapeButt, BorderLayout.WEST);
             
        
        weekp.add(headingPanel, BorderLayout.NORTH);
        weekp.add(daysPanel, BorderLayout.WEST);
        weekp.add(iconsPanel, BorderLayout.CENTER);
        weekp.add(tempPanel, BorderLayout.EAST);
        weekp.add(homeButt, BorderLayout.SOUTH);        
 
        //Set panels to transparent
        headingPanel.setOpaque(false);
        daysPanel.setOpaque(false);
        iconsPanel.setOpaque(false);
        tempPanel.setOpaque(false);
        
        homeButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.show(container, "home panel");
                if (weekp.getSize().width <= 350)
                        setSize(346,545);
                    else
                        setSize(545, 346);
                }
            
        });
        landscapeButt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //card.show(container, "land panel");
                    if (weekp.getSize().width <= 350)
                        setSize(545,346);
                    else
                        setSize(346,545);
                    }
        });

        return weekp;
    }
        
        
        //main function 
	public static void main(String[] args) {

		GUImain gui = new GUImain();
                
                //add container to the frame
		gui.add(container); 
		gui.setVisible(true);
                gui.setResizable(false);

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
    
        
    public ImageIcon conditions(String cond, String panLocation) {
        
        if (cond.contains("Showers") || cond.contains("Rain")) {
            if (panLocation.equals("home"))
                image = new ImageIcon(getClass().getResource("rain.png"));
            else
                image = new ImageIcon(getClass().getResource("rainicon.png"));
        }
        
        else if (cond.contains("Sunny")) {
            if (panLocation.equals("home"))
                image = new ImageIcon(getClass().getResource("sunny.png"));
            else
                image = new ImageIcon(getClass().getResource("sunicon.png"));  
        }
        
        else if (cond.contains("Snow")) {
            if (panLocation.equals("home"))
                image = new ImageIcon(getClass().getResource("snow.png"));
            else
                image = new ImageIcon(getClass().getResource("snowicon.png"));
        } 
        
        else {
            if (panLocation.equals("home"))
                image = new ImageIcon(getClass().getResource("cloud.png"));
            else
                image = new ImageIcon(getClass().getResource("cloudicon.png"));
        }  
       
        return image;    
    }
}