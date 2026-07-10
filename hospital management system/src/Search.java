import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.border.BevelBorder;



public class Search {

        
        JFrame searchframe;
        JPanel  searchpane;
        JComboBox<String> jcbselect;
        
                
	Search()
	{   

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
            
            final JFrame searchframe = new JFrame("Search");
            searchframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
            searchframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            searchframe.setLayout(null);
            
            
            //*** HEADER PANEL ***// 
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
	              searchframe.setVisible(true);
	                new HomePage();
	            }
	        });
	        logout.setBounds((screenSize.width/2)+30,650,100,30);
	      searchframe.add(logout);
	        
	        JButton backbutton = new JButton("Back");
	        backbutton.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
	              searchframe.setVisible(true);
	                new Menu();
	            }
	        });
	        backbutton.setBounds((screenSize.width/2)-170,650,100,30);
	      searchframe.add(backbutton);
        
                    
            searchpane = new JPanel();
            searchpane.setLayout(null);
            searchpane.setBounds(450,120,600,200);
            
            //*** COMBO BOX ***//
            jcbselect=new JComboBox<String>();
    	    jcbselect.setBounds(0,10,100,30);
    		jcbselect.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	    jcbselect.addItem("p_id");
    	    jcbselect.addItem("p_nm");
    	    jcbselect.addItem("p_illness");
    	    jcbselect.addItem("p_add");
    	  
                    
            final JTextField field = new JTextField("Enter ID,Name,Illness or Address");
            field.setBounds(115,10,200,30);
            JButton searchbutton = new JButton("Search");
            searchbutton.setBounds(325,10,150,30);
            
       
           //*** ADDING DESIGN ELEMENTS ***//
            searchpane.add(jcbselect);
            searchpane.add(field);
            searchpane.add(searchbutton);

            searchframe.add(searchpane);
            searchframe.add(panel);
            searchframe.add(heading);
            searchframe.add(headerpanel);
            searchframe.add(headerpanel1);
            
                 
		//*** SEARCH BUTTON ACTION ***//
            searchbutton.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent ae)
            {
            	DefaultTableModel patientmodel = new DefaultTableModel();
      	        patientmodel.addColumn("<html><h3>Id</h3></html>");   
      	        patientmodel.addColumn("<html><h3>Name</h3></html>");   
      	        patientmodel.addColumn("<html><h3>Address</h3></html>");   
      	        patientmodel.addColumn("<html><h3>Contact</h3></html>");   
      	        patientmodel.addColumn("<html><h3>Age</h3></html>");   
      	        patientmodel.addColumn("<html><h3>Gender</h3></html>");   
      	        patientmodel.addColumn("<html><h3>Illness</h3></html>");  
      	        patientmodel.addColumn("<html><h4>Doctor Consulted</h4></html>");  
   	            patientmodel.addColumn("<html><h3>Admit Status</h3></html>");   
   	            patientmodel.addColumn("<html><h3>Ward</h3></html>");
   	            patientmodel.addColumn("<html><h3>Admit date</h3></html>");
   	            patientmodel.addColumn("<html><h4>Discharge date</h4></html>");
   
            	
                PreparedStatement pstmt = null;
               
                DBConn d = new DBConn();
                try {
                	d.open();
  
       	    	  String select = (String)jcbselect.getSelectedItem();
       	       	  String value =field.getText();
                  String query = "select * FROM patients WHERE " +select+" = '"+value+"'";
                  pstmt = d.conn.prepareStatement(query); 
                  ResultSet rs = pstmt.executeQuery(); 
                    rs.next();

    	                String id = rs.getString("p_id");
    	                String name = rs.getString("p_nm");
    	                String address = rs.getString("p_add");
    	                String phone = rs.getString("P_contact");
    	                String age = rs.getString("p_age");
    	                String gender = rs.getString("p_gender");
    	                String illness = rs.getString("p_illness");
    	                String doc = rs.getString("doctor");	
    	                String ward = rs.getString("ward");	
    	                String admitstatus = rs.getString("admitstatus");	
    	                String doa = rs.getString("doa");	
    	                String dod = rs.getString("dod");	
  
     	                patientmodel.addRow(new Object[]{id, name, address, phone, age, gender, illness, doc, admitstatus, ward, doa, dod});
				
                } catch (Exception e) {
                  e.printStackTrace();
                } 
                finally {
                	d.close();
                }
            
            JTable patienttable = new JTable(patientmodel);
	        JScrollPane patientpane = new JScrollPane(patienttable);
	        patientpane.setBounds(30,200,1240,420);
	        searchframe.add(patientpane);  
       
            }
            });
 
            searchframe.setVisible(true);  
	}
        

	
	public static void main(String[] args)
	{
            SwingUtilities.invokeLater(new Runnable() {
            	public void run() {
              	new Search();
              }});
           
	}
	
}
