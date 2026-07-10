import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class AddDoc {
	String name;
	String address;
	String contact;
	String specialization;	

                
	AddDoc()
	{   
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
            
            final JFrame addframe = new JFrame("Add");
            addframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
            addframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            addframe.setLayout(null);
            
            
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
	        heading.setBounds(screenSize.width-1090,40,900,45);
            
 	        ImageIcon image = new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\NewProject\\img\\icon.png");
	        JLabel label = new JLabel("", image, JLabel.CENTER);
	        JPanel panel = new JPanel(new BorderLayout());
	        panel.add( label, BorderLayout.CENTER );
	        panel.setBounds(60,15,100,90);
	        
            
            //*** FOOTER PANEL ***// 
            JButton logout = new JButton("Log out");
            logout.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    addframe.setVisible(true);
                    new HomePage();
                }
            });
            logout.setBounds((screenSize.width/2)+30,650,100,30);
            addframe.add(logout);
            
            JButton backbutton = new JButton("Back");
            backbutton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    addframe.setVisible(true);
                    new Menu();
                }
            });
            backbutton.setBounds((screenSize.width/2)-170,650,100,30);
            addframe.add(backbutton);

            
            addframe.add(panel);
            addframe.add(heading);
            addframe.add(headerpanel);
            addframe.add(headerpanel1);
 
            addframe.setVisible(true);
	
           
            //*** BODY PANEL***//
           JPanel panel1 = new JPanel();
	        panel1.setOpaque(true);
	        panel1.setBounds(5,110,screenSize.width-10,screenSize.height-(screenSize.height/4));
	        panel1.setLayout(null);
	        
	 
	        JPanel newform = new JPanel();
	        newform.setLayout(null);
	        newform.setBounds(410,25,450,480);
	        newform.setBorder(new EtchedBorder(EtchedBorder.RAISED)); 
	        
	        JLabel newlabel = new JLabel("Enter Doctor Details");
	        newlabel.setBounds(170,15,300,40);
	        JTextField newname = new JTextField("Enter Name");
	        newname.setBounds(80,65,300,40);
	        JTextField newspecialization = new JTextField("Enter Specialization");
	        newspecialization.setBounds(80,115,300,40);
	        JTextField newcontact = new JTextField("Enter Contact no.");
	        newcontact.setBounds(80,165,300,40);
	        JTextField newaddress = new JTextField("Enter Address");
	        newaddress.setBounds(80,215,300,40);
	        JButton formaddbutton = new JButton("Submit");
            formaddbutton.setBounds(125,380,200,40);
            formaddbutton.setVisible(true);
                   
                    
             newform.add(newlabel);
        	 newform.add(newname);
        	 newform.add(newspecialization);
             newform.add(newcontact);
        	 newform.add(newaddress);
        	 newform.add(formaddbutton);
        	      
        	 panel1.add(newform);
             addframe.add(panel1);     
         		
                    
                    
           //*** SUBMIT BUTTON ACTION ***//
                    
                    DBConn d = new DBConn();
                formaddbutton.addActionListener(new ActionListener()
                {
                public void actionPerformed(ActionEvent ae)
                {
                PreparedStatement pstmt = null;
                try {
               
                d.open();
                  String query = "INSERT into doctors (doc_nm, doc_specialization, doc_contact, doc_add) values(?,?,?,?)";

                  pstmt = d.conn.prepareStatement(query); // create a statement
                  pstmt.setString(1, newname.getText()); 
                  pstmt.setString(2, newspecialization.getText());
                  pstmt.setString(3, newcontact.getText());
                  pstmt.setString(4, newaddress.getText()); 
          
                  pstmt.executeUpdate(); // execute insert statement
                  JOptionPane.showMessageDialog(null, "Successfully entered details");
                  
                  
                } catch (Exception e) {
                  e.printStackTrace();
                } 
                finally {
                	d.close();
                }
            }
        });
      
                    
        }         
	
	public static void main(String ar[])
	{
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddDoc();
                
            }});
		
	}
}


