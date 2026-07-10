import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

class DeleteDoc 
{
	String name;
	String address;
	String contact;
	String specialization;	
        
    JFrame deleteframe;
    JPanel formpanel,deletepane;

        
                
	DeleteDoc()
	{   
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
            
            final JFrame deleteframe = new JFrame("Delete");
            deleteframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
            deleteframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            deleteframe.setLayout(null);
            
            
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
	               deleteframe.setVisible(true);
	                new HomePage();
	            }
	        });
	        logout.setBounds((screenSize.width/2)+30,650,100,30);
	       deleteframe.add(logout);
	        
	        JButton backbutton = new JButton("Back");
	        backbutton.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
	               deleteframe.setVisible(true);
	                new Menu();
	            }
	        });
	        backbutton.setBounds((screenSize.width/2)-170,650,100,30);
	       deleteframe.add(backbutton);
            
          
	       //***BODY PANEL***//
	        deletepane = new JPanel();
            deletepane.setLayout(null);
            deletepane.setBounds(500,120,400,200);
           
            final JTextField idfield = new JTextField("Enter ID");
            idfield.setBounds(10,10,150,30);
            JButton viewbutton = new JButton("View");
            viewbutton.setBounds(170,10,150,30);

            deletepane.add(idfield);
            deletepane.add(viewbutton);

            deleteframe.add(deletepane);
            deleteframe.add(panel);
            deleteframe.add(heading);
            deleteframe.add(headerpanel);
            deleteframe.add(headerpanel1);
   
         
            //***DELETE VIEW***//
            formpanel = new JPanel();
            formpanel.setBounds(410,150,450,480);		
            formpanel.setLayout(null);
        
		DBConn d = new DBConn();
            viewbutton.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent ae)
            {
            	d.open();
                PreparedStatement pstmt = null;
                try {
                  int a = Integer.parseInt(idfield.getText());
                  String query = "select * FROM doctors WHERE doc_id = ?";
                  pstmt = d.conn.prepareStatement(query); // create a statement
                  pstmt.setInt(1, a);                  
                  ResultSet rs = pstmt.executeQuery(); 
                    rs.next();
                    name = rs.getString("doc_nm");
                    specialization = rs.getString("doc_specialization");
                    contact = rs.getString("doc_contact");
                    address = rs.getString("doc_add");
                   

                    deleteform(name, specialization, address, contact, a);
					
                } catch (Exception e) {
                  e.printStackTrace();
                } 
                finally{
                	d.close();
                }
            }
        });
                
           deleteframe.add(formpanel);     
		
           deleteframe.setVisible(true);
	}
        
        public void deleteform(String name, String specialization, String address, String contact, final int a)
        {
             
                    
        			JTextField namefield = new JTextField(name);
                    namefield.setBounds(90,60,280,40);
                    formpanel.add(namefield);

                    JTextField specializationfield = new JTextField(specialization);
                    specializationfield.setBounds(90,110,280,40);
                    formpanel.add(specializationfield);

                    JTextField addressfield = new JTextField(address);
                    addressfield.setBounds(90,160,280,40);
                    formpanel.add(addressfield);

                    JTextField contactfield = new JTextField(contact);
                    contactfield.setBounds(90,210,280,40);
                    formpanel.add(contactfield);

                    JButton formdeletebutton = new JButton("Delete");
                    formdeletebutton.setBounds(115,380,230,40);
                    formdeletebutton.setVisible(true);
                    formpanel.add(formdeletebutton);
                    
                    formpanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
                 
                    deletepane.setVisible(false);
                  //  menubuttonpane.setVisible(true);
                    formpanel.repaint();
               
                    
                    
                    //***DELETE BUTTON ACTION***//
                    
                    DBConn d = new DBConn();
                formdeletebutton.addActionListener(new ActionListener()
                {
                public void actionPerformed(ActionEvent ae)
                {
                PreparedStatement pstmt = null;
                try {
               
                d.open();
                  String query = "DELETE from doctors WHERE doc_id=?";

                  pstmt = d.conn.prepareStatement(query); 
                  pstmt.setInt(1, a);
                  pstmt.executeUpdate(); 
                  JOptionPane.showMessageDialog(null, "Deletion successfull");
                  
                  
                } catch (SQLException e) {
                  e.printStackTrace();
                } 
                finally {
                	d.close();
                }
            }
        });
                     
     }         
	
	public static void main(String args[])
	{
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DeleteDoc();
                
            }});
		
	}
}
