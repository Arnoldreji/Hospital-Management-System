import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

class HomePage {
 
    
    HomePage()
    {   
    	//*** DRIVER CLASS LOADING ***//
    	DBConn d = new DBConn();
    	d.connect();
 
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
        
   
        
        //*** LOGIN PANEL ***//
        JPanel loginpanel = new JPanel();
        loginpanel.setLayout(null);		
        loginpanel.setBounds(890, 130, 450, 450);
        loginpanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
     
         
        JLabel loginlabel = new JLabel("LOGIN");
        loginlabel.setBounds(200,0,300,100);
        loginlabel.setFont(new Font("TimesNewRoman",Font.BOLD,28));                

        final JTextField username = new JTextField("Enter Username");
        username.setBounds(100,120,300,50);

        final JPasswordField password = new JPasswordField("Enter Password");
        password.setBounds(100,210,300,50);

        JButton loginbutton = new JButton("LOGIN");
        loginbutton.setBounds(145,320,100,50);

        //*** LOGIN BUTTON ACTION ***//
        loginbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(username.getText().length()==0)
                    JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
                else if(password.getPassword().length == 0)
                    JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
                else 
                {
                    String user = username.getText();   
                    char[] pass = password.getPassword(); 
                    String pwd = String.copyValueOf(pass);  
                    if(validate_login(user,pwd))
                    {
                        JOptionPane.showMessageDialog(null, "Correct Login Credentials");
                        homepageframe.setVisible(false);
                        new Menu();
                    }
                    else
                       JOptionPane.showMessageDialog(null, "Incorrect Login Credentials","Error",JOptionPane.ERROR_MESSAGE);        

                }
            }
        });
        
              
        JButton signupbutton = new JButton("SIGN UP");
        signupbutton.setBounds(265,320,100,50);
        
        signupbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
		 homepageframe.setVisible(false);
               new Signup();
            }
        });
        
        
      //*** ADDING DESIGN ELEMENTS ***//
        headerpanel.add(panel);
        headerpanel.add(heading);
        wrapper.add(headerpanel);
        wrapper.add(headerpanel1);
        wrapper.add(mainpanel);	
      
        loginpanel.add(loginlabel);
        loginpanel.add(username);
        loginpanel.add(password);
        loginpanel.add(loginbutton);
        loginpanel.add(signupbutton);
        wrapper.add(loginpanel);

        homepageframe.add(wrapper);	
        homepageframe.pack();

        //*** SCROLLBAR ***//
        JScrollPane pane = new JScrollPane(wrapper);
        homepageframe.add(pane);

        //*** HOMEPAGE VISIBILITY ***//
        homepageframe.setVisible(true);
    }

    private boolean validate_login(String username, String password) {
    	
    	DBConn d = new DBConn();
    	
    	try {
            d.open();

        	PreparedStatement pst = d.conn.prepareStatement("Select * from users where username=? and password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally
        {
        	d.close();
        }
    }


    public static void main(String args[]) {
        new HomePage();
    }

}

