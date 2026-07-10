import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

class Edit 
{
	String name;
	String address;
	String phone;
	String age;
	String gender;
	String illness;
        
        JFrame editframe;
        JPanel formpanel, editpane, menubuttonpane;
        JButton newbutton, anotherbutton;
        
                
	Edit()
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
       
            //*** BODY PANEL ***//
            editpane = new JPanel();
            editpane.setLayout(null);
            editpane.setBounds(520,120,400,200);
            
            menubuttonpane = new JPanel();
            menubuttonpane.setLayout(null);
            menubuttonpane.setBounds(500,140,400,50);

                    
            final JTextField idfield = new JTextField("Enter ID");
            idfield.setBounds(10,10,150,30);
            JButton editbutton = new JButton("Edit");
            editbutton.setBounds(170,10,150,30);
            
            newbutton = new JButton("New Patient");
            newbutton.setBounds(30,15,150,30);
            menubuttonpane.add(newbutton);
            menubuttonpane.setVisible(false);
            
            anotherbutton = new JButton("Another Patient");
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
            formpanel.setBounds(400,170,600,450);		
            formpanel.setLayout(null);
        
                
		//*** EDIT BUTTON ACTION ***//
            editbutton.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent ae)
            {
                PreparedStatement pstmt = null;
                DBConn d = new DBConn();
                try {
                      	d.open();
	
                   int a = Integer.parseInt(idfield.getText());
                  String query = "select * FROM patients WHERE p_id = ?";
                  pstmt = d.conn.prepareStatement(query); // create a statement
                  pstmt.setInt(1, a); // set input parameter 1                  
                  ResultSet rs = pstmt.executeQuery(); // execute insert statement
                    rs.next();
                    name = rs.getString("p_nm");
                    address = rs.getString("p_add");
                    phone = rs.getString("p_contact");
                    age = rs.getString("p_age");
                    gender = rs.getString("p_gender");
                    illness = rs.getString("p_illness");

                    editform(name, address, phone, age, gender, illness, a);
				
                } catch (Exception e) {
                  e.printStackTrace();
                } 
                finally {
                	d.close();
                }
            }
        });
                
           editframe.add(formpanel);     
           editframe.setVisible(true);
	}
        
      
	
	public void editform(String name, String address, String phone, String age, String gender, String illness, final int a)
        {
                    //*** EDIT FORM ***//
                 	JTextField namefield = new JTextField(name);
                    namefield.setBounds(165,40,280,40);
                    formpanel.add(namefield);

                    JTextField addressfield = new JTextField(address);
                    addressfield.setBounds(165,90,280,40);
                    formpanel.add(addressfield);

                    JTextField contactfield = new JTextField(phone);
                    contactfield.setBounds(165,140,280,40);
                    formpanel.add(contactfield);

                    JTextField agefield = new JTextField(age);
                    agefield.setBounds(165,190,280,40);
                    formpanel.add(agefield);

                    JTextField genderfield = new JTextField(gender);
                    genderfield.setBounds(165,240,280,40);
                    formpanel.add(genderfield);

                    JTextField illnessfield = new JTextField(illness);
                    illnessfield.setBounds(165,290,280,40);
                    formpanel.add(illnessfield);
                    
                    
                   JComboBox<String> doc=new JComboBox<String>();
         	       doc.setBounds(165,340,280,40);
         	       doc.addItem("--Select Doctor--");
         	        try
         			{   DBConn d = new DBConn();
         	        	d.open();
         				Statement stmt = d.conn.createStatement();
         			
         				ResultSet rst=stmt.executeQuery("select * from doctors");
         				while(rst.next())
         				{
         					doc.addItem(rst.getString(2));
         				}
         			
         				d.close();
         			}
         				catch(SQLException e)
         				{
         					e.printStackTrace();
         				}

         	       formpanel.add(doc);
         	        
         	        JButton formeditbutton = new JButton("Submit");
                    formeditbutton.setBounds(185,390,230,40);
                    formeditbutton.setVisible(true);
                    formpanel.add(formeditbutton);
                    
                    formpanel.setBorder(new EtchedBorder(EtchedBorder.RAISED)); 
                    
                    editpane.setVisible(false);
                    menubuttonpane.setVisible(true);
                    formpanel.repaint();
                    
                    
                  //*** 2nd EDIT BUTTON ACTION ***//
                formeditbutton.addActionListener(new ActionListener()
                {
                public void actionPerformed(ActionEvent ae)
                {
                PreparedStatement pstmt = null;
              
                DBConn d = new DBConn();
                try {
                  d.open();

                  String query = "UPDATE patients SET p_nm=?, p_add=?, p_contact=?, p_age=?, p_gender=?, p_illness=?, doctor=? WHERE p_id=?";

                  pstmt = d.conn.prepareStatement(query); // create a statement
                  pstmt.setString(1, namefield.getText()); 
                  pstmt.setString(2, addressfield.getText()); 
                  pstmt.setString(3, contactfield.getText()); 
                  pstmt.setString(4, agefield.getText());
                  pstmt.setString(5, genderfield.getText());
                  pstmt.setString(6, illnessfield.getText());
                  pstmt.setString(7, doc.getSelectedItem().toString());
                  pstmt.setInt(8, a);
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
      
                
         //***BUTTON ACTIONS ***//       
        newbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Menu();
                editframe.setVisible(false);
            }
        });
        
        anotherbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Edit();
                editframe.setVisible(false);
            }
        });
                    
        }         
	
	public static void main(String args[])
	{
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Edit();
                
            }});
		
	}
}


