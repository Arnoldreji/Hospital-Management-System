import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;



public class Signup {
	
	Signup(){
	
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

      //*** PAGE FRAME ***//
      final JFrame homepageframe = new JFrame("Hospital Mangement System");
      homepageframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
      homepageframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //*** WRAPPER ***// 
      JPanel wrapper = new JPanel();
      wrapper.setLayout(null);
      
  
    //*** HEADERPANEL ***//
      JPanel headerpanel = new JPanel();
      headerpanel.setLayout(null);
      headerpanel.setBounds(10,10,screenSize.width-20,100);
      headerpanel.setBorder(new BevelBorder(BevelBorder.RAISED)); 
     
      JPanel headerpanel1 = new JPanel();
      headerpanel1.setLayout(null);
      headerpanel1.setBounds(16,16,screenSize.width-20,100);
      headerpanel1.setBackground(new Color(200, 200, 200));            
 
      JLabel heading = new JLabel("HOSPITAL MANAGEMENT SYSTEM");
      Font font = new Font("TimesNewRoman", Font.BOLD, 50);
      heading.setFont(font);
      heading.setForeground(new Color(0, 0, 128));            
      heading.setBounds(screenSize.width-1100,30,900,45);
    
      ImageIcon image = new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\NewProject\\img\\icon.png");
      JLabel label = new JLabel("", image, JLabel.CENTER);
      JPanel panel = new JPanel(new BorderLayout());
      panel.add( label, BorderLayout.CENTER );
      panel.setBounds(50,5,100,90);



      //*** BODY PANEL***//
      JPanel mainpanel = new JPanel();
      mainpanel.setLayout(null);
      mainpanel.setBounds(20, 130, 850, 450);

      JLabel mainpanel_pic = new JLabel(new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\NewProject\\img\\pic11.jpeg"));
      mainpanel_pic.setBounds(0,20,850,450);
      mainpanel.add(mainpanel_pic);

	
	JPanel signuppanel = new JPanel();
    signuppanel.setLayout(null);		
    signuppanel.setBounds(890, 130, 450, 450);
    signuppanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
 
     
    JLabel signuplabel = new JLabel("SIGN UP");
    signuplabel.setBounds(200,0,300,100);
    signuplabel.setFont(new Font("TimesNewRoman",Font.BOLD,28));                

    final JTextField username = new JTextField("Enter Username");
    username.setBounds(100,100,300,50);

    final JPasswordField password = new JPasswordField("Enter Password");
    password.setBounds(100,190,300,50);

    final JTextField retype = new JTextField("Retype Password");
    retype.setBounds(100,280,300,50);
    
    JButton registerbutton = new JButton("Register");
    registerbutton.setBounds(150,360,200,50);
    
    
    //*** REGISTER BUTTON ACTION ***//
    registerbutton.addActionListener(new ActionListener()
    {DBConn d = new DBConn();

        public void actionPerformed(ActionEvent ae)
        {
        	String user=username.getText();
			String pass=new String(password.getPassword());
			String retypepass=new String(retype.getText());

		

				
				
				if(user.isEmpty() || pass.isEmpty() || retypepass.isEmpty())
					JOptionPane.showMessageDialog(null,"All Fields Are Mandatry To Fill","Error",JOptionPane.ERROR_MESSAGE);
				
				else if(!pass.equals(retypepass))
					JOptionPane.showMessageDialog(null,"Password and Re Type must match","Error",JOptionPane.ERROR_MESSAGE);
				
				

	       		else
				
			           try        
			              {   
			         	   d.open();
		              	PreparedStatement pstmt = d.conn.prepareStatement("Insert into users(username, password) values (?,?)");
		      	         pstmt.setString(1, user);
		                 pstmt.setString(2, pass);
		                 pstmt.executeUpdate();
		                 JOptionPane.showMessageDialog(null,"User created Successfully.");
		                 homepageframe.setVisible(false);	
		                 new HomePage();
		      
				            }
			            catch (Exception e) {
	                     e.printStackTrace();
	                       }
			          	finally{
					    d.close();
				         }
				}   
      
		
	
	    });
    
    
    JButton backbutton = new JButton("Back to Login");
    backbutton.setBounds((screenSize.width/2)-70,650,200,50);
    backbutton.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ae)
        {
            new HomePage();
            homepageframe.setVisible(false);
        }
    });		
   
    headerpanel.add(panel);
    headerpanel.add(heading);
    wrapper.add(headerpanel);
    wrapper.add(headerpanel1);
    wrapper.add(mainpanel);	
  
    signuppanel.add(signuplabel);
    signuppanel.add(username);
    signuppanel.add(password);
    signuppanel.add(retype);
    signuppanel.add(registerbutton);
   
    wrapper.add(signuppanel);
    wrapper.add(backbutton);

    homepageframe.add(wrapper);	
    homepageframe.pack();

    //*** SCROLLBAR ***//
    JScrollPane pane = new JScrollPane(wrapper);
    homepageframe.add(pane);

    //*** HOMEPAGE VISIBILITY ***//
    homepageframe.setVisible(true);

}

	 public static void main(String args[])
	    {
	        SwingUtilities.invokeLater(new Runnable()
	        {
	                public void run()
	                {
	                        new Signup();
	                }
	        });
	    }
}
