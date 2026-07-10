import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

class EditDoc 
{
	String name;
	String address;
	String contact;
	String specialization;	
        
    JFrame editframe;
    JPanel formpanel, editpane, menubuttonpane;
    JButton newbutton,anotherbutton;
        
                
	EditDoc()
	{   
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
            
            final JFrame editframe = new JFrame("Edit");
            editframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
            editframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editframe.setLayout(null);
            
            
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
	               editframe.setVisible(true);
	                new HomePage();
	            }
	        });
	        logout.setBounds((screenSize.width/2)+30,650,100,30);
	       editframe.add(logout);
	        
	        JButton backbutton = new JButton("Back");
	        backbutton.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
	               editframe.setVisible(true);
	                new Menu();
	            }
	        });
	        backbutton.setBounds((screenSize.width/2)-170,650,100,30);
	       editframe.add(backbutton);

            /***BODY PANEL***/
            editpane = new JPanel();
            editpane.setLayout(null);
            editpane.setBounds(500,120,400,200);
            
            menubuttonpane = new JPanel();
            menubuttonpane.setLayout(null);
            menubuttonpane.setBounds(480,140,400,50);

            final JTextField idfield = new JTextField("Enter ID");
            idfield.setBounds(10,10,150,30);
            JButton editbutton = new JButton("Edit");
            editbutton.setBounds(170,10,150,30);
         
            newbutton = new JButton("Add Doctor");
            newbutton.setBounds(30,15,150,30);
            menubuttonpane.add(newbutton);
            menubuttonpane.setVisible(false);
            
            anotherbutton = new JButton("Another Doctor");
            anotherbutton.setBounds(210,15,150,30);
            menubuttonpane.add(anotherbutton);
            menubuttonpane.setVisible(false);
            
            editpane.add(idfield);
            editpane.add(editbutton);

            editframe.add(editpane);
            editframe.add(menubuttonpane);
            editframe.add(panel);
            editframe.add(heading);
            editframe.add(headerpanel);
            editframe.add(headerpanel1);
         
  
            formpanel = new JPanel();
            formpanel.setBounds(420,170,500,450);		
            formpanel.setLayout(null);
                
		DBConn d = new DBConn();
            editbutton.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent ae)
            {d.open();
                PreparedStatement pstmt = null;
                try {
            
                   int a = Integer.parseInt(idfield.getText());
                  String query = "select * FROM doctors WHERE doc_id = ?";
                  pstmt = d.conn.prepareStatement(query); // create a statement
                  pstmt.setInt(1, a); // set input parameter 1                  
                  ResultSet rs = pstmt.executeQuery(); 
                    rs.next();
                    name = rs.getString("doc_nm");
                    specialization = rs.getString("doc_specialization");
                    contact = rs.getString("doc_contact");
                    address = rs.getString("doc_add");
                   

                    editform(name, specialization, address, contact, a);
					
                } catch (Exception e) {
                  e.printStackTrace();
                } 
                finally{
                	d.close();
                }
            }
        });
                
           editframe.add(formpanel);     
		
            editframe.setVisible(true);
	}
        
       
	  //***EDIT FORM***//
	public void editform(String name, String specialization, String address, String contact, final int a)
        {
           
                    
        			JTextField namefield = new JTextField(name);
                    namefield.setBounds(120,60,280,40);
                    formpanel.add(namefield);

                    JTextField specializationfield = new JTextField(specialization);
                    specializationfield.setBounds(120,110,280,40);
                    formpanel.add(specializationfield);

                    JTextField addressfield = new JTextField(address);
                    addressfield.setBounds(120,160,280,40);
                    formpanel.add(addressfield);

                    JTextField contactfield = new JTextField(contact);
                    contactfield.setBounds(120,210,280,40);
                    formpanel.add(contactfield);

                    JButton formeditbutton = new JButton("Submit");
                    formeditbutton.setBounds(165,380,200,40);
                    formeditbutton.setVisible(true);
                    formpanel.add(formeditbutton);
                    
                    formpanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
                    
                    editpane.setVisible(false);
                    menubuttonpane.setVisible(true);
                    formpanel.repaint();
               
                    
                    
                    /***2nd BUTTON ACION***/
                    
                    DBConn d = new DBConn();
                formeditbutton.addActionListener(new ActionListener()
                {
                public void actionPerformed(ActionEvent ae)
                {
                PreparedStatement pstmt = null;
                try {
               
                d.open();
                  String query = "UPDATE doctors SET doc_nm=?, doc_specialization=?, doc_contact=?, doc_add=? WHERE doc_id=?";

                  pstmt = d.conn.prepareStatement(query); // create a statement
                  pstmt.setString(1, namefield.getText()); 
                  pstmt.setString(2, specializationfield.getText());
                  pstmt.setString(3, contactfield.getText());
                  pstmt.setString(4, addressfield.getText()); 
                  pstmt.setInt(5, a);
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
                
        newbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new AddDoc();
                editframe.setVisible(false);
            }
        });
        
        anotherbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new EditDoc();
                editframe.setVisible(false);
            }
        });
                    
        }         
	
	public static void main(String args[])
	{
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EditDoc();
                
            }});
		
	}
}
