
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import javax.swing.table.DefaultTableModel;
	import java.sql.*;
	import javax.swing.border.BevelBorder;
	import javax.swing.border.EtchedBorder;
	

	
	class Menu
	{
	    JTabbedPane tabbedPane;			
	    JPanel mainbodypanel, billdisplaypanel;
	    int i = 1;
	    
	    JTextArea billtextarea;
	    JLabel totaldisplaylabel;
	    JTextField patient_name_label;
	    JComboBox<String> roomcb,doc;
	    
	     JLabel patient_name;
	    
	    
	    DefaultTableModel billtablemodel;
	    JTable billtable;
	    JScrollPane billdisplayscroll;
	    

	    String name;
	    String address;
	    String phone;
	    String age;
	    String gender;
	    String illness;
	
	    
	    int billamountint;
	    double totalsum1=0;

	    Menu()
	    {
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


	    
	        final JFrame menupageframe = new JFrame("Menu Page");
	        menupageframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        menupageframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        menupageframe.setVisible(true);
	        menupageframe.setLayout(null);

	      
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
	        heading.setBounds(screenSize.width-1100,30,900,45);

	        ImageIcon image = new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\NewProject\\img\\icon.png");
	        JLabel label = new JLabel("", image, JLabel.CENTER);
	        JPanel panel = new JPanel(new BorderLayout());
	        panel.add( label, BorderLayout.CENTER );
	        panel.setBounds(50,5,100,90);
	        

	        //add headerpanel
	        menupageframe.add(headerpanel);
	        menupageframe.add(headerpanel1);
	        headerpanel.add(heading);
	        headerpanel.add(panel);
	        
	        
	        //*** FOOTER PANEL ***//
	        JButton logout = new JButton("Log out");
	        logout.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
	                menupageframe.setVisible(true);
	                new HomePage();
	            }
	        });
	        logout.setBounds((screenSize.width/2)-80,650,100,30);
	        menupageframe.add(logout);
	
	        
	        //*** BODY PANEL ***//
	        mainbodypanel = new JPanel();
	        mainbodypanel.setLayout(null);
	        mainbodypanel.setBounds(5,110,screenSize.width-10,screenSize.height-(screenSize.height/4));
	        mainbodypanel.setBackground(new Color(255,255,255,255));


	        //*** TABBED MENU PANE ***// 
	        JTabbedPane tabpane = new JTabbedPane();
	        tabpane.setBounds(5,115,screenSize.width-10,screenSize.height-(screenSize.height/4));
	        menupageframe.add(tabpane);

	        //*** NEW PATIENT FORM ***//
	     
	        DBConn d = new DBConn(); 
	        
	        JPanel panel1 = new JPanel();
	        panel1.setOpaque(true);
	        panel1.setBounds(5,110,screenSize.width-10,screenSize.height-(screenSize.height/4));
	        panel1.setLayout(null);
	        
	 
	        JPanel newform = new JPanel();
	        newform.setLayout(null);
	        newform.setBounds(410,25,450,480);
	        newform.setBorder(new EtchedBorder(EtchedBorder.RAISED)); 
	        
	        JLabel newlabel = new JLabel("Enter Details of New Patient");
	        newlabel.setBounds(140,5,300,40);
	        JTextField newname = new JTextField("Enter Name");
	        newname.setBounds(80,55,300,40);
	        JTextField newaddress = new JTextField("Enter Address");
	        newaddress.setBounds(80,105,300,40);
	        JTextField newcontact = new JTextField("Enter Contact no.");
	        newcontact.setBounds(80,155,300,40);
	        JTextField newage = new JTextField("Enter Age");
	        newage.setBounds(80,205,300,40);
	        JTextField newgender = new JTextField("Enter Gender");
	        newgender.setBounds(80,255,300,40);
	        JTextField newillness = new JTextField("Enter Illness");
	        newillness.setBounds(80,305,300,40);
	       
	       doc=new JComboBox<String>();
	       doc.setBounds(80,355,300,40);
	       doc.addItem("--Select Doctor--");
	        try
			{   d.open();
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
			
	        
	        JButton newbutton = new JButton("Submit");
	        newbutton.setBounds(100,415,250,40);
	       
	        //*** ADDING FORM ELEMENTS ***//
	        newform.add(newlabel);
	        newform.add(newname);
	        newform.add(newname);
	        newform.add(newaddress);
	        newform.add(newcontact);
	        newform.add(newage);
	        newform.add(newgender);
	        newform.add(newillness);
	        newform.add(doc);
	        newform.add(newbutton);
	        
	        panel1.add(newform);
	        // FORM end
	      
	      
	        
	        //*** SUBMIT BUTTON ACTION ***//
	        newbutton.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
	                PreparedStatement pstmt = null;
	                PreparedStatement billpstmt = null;
	        
	                try {
		            	d.open();  
	                  String query = "insert into patients(p_nm, p_add, p_contact, p_age, p_gender, p_illness, doctor) values(?, ?, ?, ?, ?, ?, ?)";
					  
					  
				  // FOR BILLING TABLE start
					   Statement s = d.conn.createStatement();
					   ResultSet r = s.executeQuery("SELECT COUNT(*) AS rowcount FROM patients");
					   r.next();
					   int count = r.getInt("rowcount");
					  count= count+1;				  
					  String billquery = "CREATE TABLE patient_"+count+" " +
	                   "(id int(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, " +
	                   " bill_item VARCHAR(255), " + 
	                   " billamount VARCHAR(255))";
					  billpstmt = d.conn.prepareStatement(billquery);
					  billpstmt.executeUpdate();
					  // FOR BILLING TABLE end
					   

	                  pstmt = d.conn.prepareStatement(query);
	                  pstmt.setString(1, newname.getText()); 
	                  pstmt.setString(2, newaddress.getText()); 
	                  pstmt.setString(3, newcontact.getText());
	                  pstmt.setString(4, newage.getText());
	                  pstmt.setString(5, newgender.getText());
	                  pstmt.setString(6, newillness.getText());
	                  pstmt.setString(7, doc.getSelectedItem().toString());
	                  pstmt.executeUpdate(); // execute insert statement
	                  JOptionPane.showMessageDialog(null, "Successfully entered details");
	                  new Menu();
	                  menupageframe.setVisible(false);
	                } catch (Exception e) {
	                  e.printStackTrace();
	                } 
	                finally {
	                	d.close();
	                }
	            }
	        });
	                
	        
	        //*** PATIENT RECORDS ***//
	        JPanel panel2 = new JPanel();
	        panel2.setOpaque(true);
	        panel2.setBounds(5,110,screenSize.width-10,screenSize.height-(screenSize.height/4));
	        panel2.setLayout(null);
	        
	        JButton editpatient = new JButton("Edit Records");
	        editpatient.setBounds(150,15,150,40);        
	       
	        JButton searchpatient = new JButton("Search Records");
	        searchpatient.setBounds(350,15,150,40);
	        
	        JButton delpatient = new JButton("Delete Records");
	        delpatient.setBounds(550,15,150,40);  
	        
	        JButton admitpatient = new JButton("Admit");
	        admitpatient.setBounds(750,15,150,40);
	        
	        JButton dischargepatient = new JButton("Discharge");
	        dischargepatient.setBounds(950,15,150,40);
	        
	        //*** BUTTON ACTIONS ***//
	        editpatient.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
			menupageframe.setVisible(false);
	               new Edit();
	            }
	        });
	        
        
	        searchpatient.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
			menupageframe.setVisible(false);
	                new Search();
	            }
	        });
	        
	        delpatient.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
			menupageframe.setVisible(false);
	               new Delete();
	            }
	        });
	       
	        admitpatient.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
			menupageframe.setVisible(false);
	             new RoomAdmit();
	            }
	        });
	        
	        dischargepatient.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
			menupageframe.setVisible(false);
	             new RoomDischarge();
	            }
	        });

	        
	        i=1;
	       
	      //*** PATIENT TABLE ***//
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

	        try{           
	           
	        	d.open();    
       			PreparedStatement pst = d.conn.prepareStatement("Select * from patients");                
	            ResultSet rs = pst.executeQuery();       
				
	            while(rs.next())
	            {   
				
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
	                
	                patientmodel.addRow(new Object[]{id, name, address, phone, age, gender, illness, doc, admitstatus, ward,doa,dod});
	                i++;
								
	            }
	            } 
	        catch(Exception e){
	            e.printStackTrace();                
	            } 
	        finally {
	        	d.close();
	        }
	        
	        JTable patienttable = new JTable(patientmodel);
	        JScrollPane patientpane = new JScrollPane(patienttable);
	  
	        
	        patientpane.setBounds(10,70,1240,420);
	        panel2.add(editpatient);
	        panel2.add(searchpatient);
	        panel2.add(delpatient);
	        panel2.add(admitpatient);
	        panel2.add(dischargepatient);
	        panel2.add(patientpane);
	        panel2.setVisible(true);
	        
	        
	        //*** DOCOTORS RECORDS ***//
	        JPanel panel3 = new JPanel();
	        panel3.setOpaque(true);
	        panel3.setLayout(null);
	        panel3.setBounds(5,110,screenSize.width-10,screenSize.height-(screenSize.height/4));
	        
	        
	        JButton adddoctor = new JButton("Add Records");
	        adddoctor.setBounds(250,15,150,40);
	        adddoctor.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
			menupageframe.setVisible(false);
	          new AddDoc();
	            }
	        });
	        
	        JButton editdoctor = new JButton("Edit Records");
	        editdoctor.setBounds(450,15,150,40);
	        editdoctor.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
			menupageframe.setVisible(false);
	          new EditDoc();
	            }
	        });
	        
	        JButton searchdoctor = new JButton("Search Records");
	        searchdoctor.setBounds(650,15,150,40);
	        searchdoctor.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
			menupageframe.setVisible(false);
	          new SearchDoc();
	            }
	        });
	        
	        JButton deldoctor= new JButton("Delete Records");
	        deldoctor.setBounds(850,15,150,40);
	        deldoctor.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
			menupageframe.setVisible(false);
	          new DeleteDoc();
	            }
	        });
	        
	        //*** DOCTORS TABLE ***//
	        DefaultTableModel doctormodel = new DefaultTableModel();
	        doctormodel.addColumn("<html><h3>Id</h3></html>");   
	        doctormodel.addColumn("<html><h3>Name</h3></html>");   
	        doctormodel.addColumn("<html><h3>Specialisation</h3></html>");   
	        doctormodel.addColumn("<html><h3>Phone Number</h3></html>");  
	        doctormodel.addColumn("<html><h3>Address</h3></html>"); 
	    
	   
	   
	        try{           

	        	d.open();
	            PreparedStatement pst = d.conn.prepareStatement("Select * from doctors");                
	            ResultSet rs = pst.executeQuery();                        
	            while(rs.next())
	            {   
	                String id = rs.getString("doc_id");
	                String name = rs.getString("doc_nm");  
	                String specialization = rs.getString("doc_Specialization");
	                String contact = rs.getString("doc_contact");
	                String address = rs.getString("doc_add");
	                                
	                
	                doctormodel.addRow(new Object[]{id, name, specialization,contact,address});
	               
	            }
	            } 
	        catch(Exception e){
	            e.printStackTrace();                
	            } 
	        finally {
	        	d.close();
	        }
	        
	        JTable doctortable = new JTable(doctormodel);
	        JScrollPane doctorpane = new JScrollPane(doctortable);
	        doctorpane.setBounds(10,70,1240,420);
	        
	        panel3.add(adddoctor);
	        panel3.add(editdoctor);
	        panel3.add(searchdoctor);
	        panel3.add(deldoctor);
	        panel3.add(doctorpane);
	        panel3.setVisible(true);
	        
	         
	        
	        
	        //*** ROOM OCCUPANCY ***//
	    
	        JPanel panel4 = new JPanel();
	        panel4.setOpaque(true);
	        panel4.setLayout(null);
	        panel4.setBounds(5,110,screenSize.width-10,screenSize.height-(screenSize.height/4));
	        
	        
	        JTabbedPane tabpane2 = new JTabbedPane();
	        tabpane2.setBounds(5,0,screenSize.width-15,screenSize.height-(screenSize.height/8));
	        panel4.add(tabpane2);
	        
	        JPanel roompanel = new JPanel();
	        roompanel.setOpaque(true);
	        roompanel.setLayout(null);
	        roompanel.setBounds(10,70,screenSize.width-20,screenSize.height-(screenSize.height/4));
	        
	        
	        DefaultTableModel roommodel = new DefaultTableModel();
	        roommodel.addColumn("<html><h3>Room ID</h3></html>");   
	        roommodel.addColumn("<html><h3>Room Type</h3></html>");   
	        roommodel.addColumn("<html><h4>Total no. of Beds</h4></html>");   
	        roommodel.addColumn("<html><h3>Empty Beds</h3></html>");   
	       
  try{           
	            
	       	  	d.open();
	            
	            PreparedStatement pst = d.conn.prepareStatement("Select * from room");                
	            ResultSet rs = pst.executeQuery();                        
	            while(rs.next())
	            {   
	                String rid = rs.getString("room_id");
	                String type = rs.getString("roomtype");  
	                String totalbed = rs.getString("totalbeds");
	                String emptybed = rs.getString("emptybeds");
	                	                
	                roommodel.addRow(new Object[]{rid, type, totalbed, emptybed});
	                
	            }
	            } 
	        catch(Exception e){
	            e.printStackTrace();                
	            } 
	        finally {
	        	d.close();
	        }
  
  			JTable roomtable = new JTable(roommodel);
  			JScrollPane roompane = new JScrollPane(roomtable);
  			roompane.setBounds(10,70,1240,420);
 
  			roompanel.add(roompane);
  			roompanel.setVisible(true);
	        
	        
	        JPanel gpanel = new JPanel();
	        gpanel.setOpaque(true);
	        gpanel.setLayout(null);
	        gpanel.setBounds(10,70,screenSize.width-20,screenSize.height-(screenSize.height/4));
	  
	        
	        DefaultTableModel generalmodel = new DefaultTableModel();
	        generalmodel.addColumn("<html><h3>Bed No.</h3></html>");   
	        generalmodel.addColumn("<html><h3>Patient Id</h3></html>");   
	        generalmodel.addColumn("<html><h3>Patient Name</h3></html>");   
	        generalmodel.addColumn("<html><h3>Patient age</h3></html>");   
	        generalmodel.addColumn("<html><h3>Patient Contact</h3></html>");   
	        generalmodel.addColumn("<html><h3>Gender</h3></html>");    
	        generalmodel.addColumn("<html><h3>Illness</h3></html>");   
	        generalmodel.addColumn("<html><h3>Admit Date</h3></html>");  
	        generalmodel.addColumn("<html><h3>Discharge Date</h3></html>");   
	        
	        
	        try{           
	            
	       	  	d.open();
	            
	            PreparedStatement pst = d.conn.prepareStatement("Select G.bedno,P.p_id, P.p_nm, P.p_age, P.p_contact, P.p_gender, P.p_illness, G.doa, G.dod from general G INNER JOIN patients P where G.p_id = P.p_id");                
	            ResultSet rs = pst.executeQuery();                        
	            while(rs.next())
	            {   
	                String bedno = rs.getString("bedno");
	                String id = rs.getString("p_id");  
	                String name = rs.getString("p_nm");
	                String age = rs.getString("p_age");
	                String contact = rs.getString("p_contact");
	                String gender = rs.getString("p_gender");
	                String illness = rs.getString("p_illness");
	                String doa = rs.getString("doa");
	                String dod = rs.getString("dod");
	                
	                generalmodel.addRow(new Object[]{bedno,id, name,age,contact,gender,illness,doa,dod});
	                
	            }
	            } 
	        catch(Exception e){
	            e.printStackTrace();                
	            } 
	        finally {
	        	d.close();
	        }
	        
	        JTable generaltable = new JTable(generalmodel);
	        JScrollPane generalpane = new JScrollPane(generaltable);
	        generalpane.setBounds(10,70,1240,420);
	       
	        gpanel.add(generalpane);
	        gpanel.setVisible(true);
	        
	        
	        JPanel dpanel = new JPanel();
	        dpanel.setOpaque(true);
	        dpanel.setLayout(null);
	        dpanel.setBounds(10,70,screenSize.width-20,screenSize.height-(screenSize.height/4));
	  
	        
	        DefaultTableModel deluxemodel = new DefaultTableModel();
	        deluxemodel.addColumn("<html><h3>Bed No.</h3></html>");   
	        deluxemodel.addColumn("<html><h3>Patient Id</h3></html>");   
	        deluxemodel.addColumn("<html><h3>Patient Name</h3></html>");   
	        deluxemodel.addColumn("<html><h3>Patient age</h3></html>");   
	        deluxemodel.addColumn("<html><h3>Patient Contact</h3></html>");   
	        deluxemodel.addColumn("<html><h3>Gender</h3></html>");   
	        deluxemodel.addColumn("<html><h3>Illness</h3></html>");   
	        deluxemodel.addColumn("<html><h3>Admit Date</h3></html>");  
	        deluxemodel.addColumn("<html><h3>Discharge Date</h3></html>");   
	        
	        
	        try{           
	                  	
	        	  	d.open();
	            
	            PreparedStatement pst = d.conn.prepareStatement("Select D.bedno,P.p_id, P.p_nm, P.p_age, P.p_contact, P.p_gender, P.p_illness, D.doa, D.dod from deluxe D INNER JOIN patients P where D.p_id = P.p_id");                
	            ResultSet rs = pst.executeQuery();                        
	            while(rs.next())
	            {   
	                String bedno = rs.getString("bedno");
	                String id = rs.getString("p_id");  
	                String name = rs.getString("p_nm");
	                String age = rs.getString("p_age");
	                String contact = rs.getString("p_contact");
	                String gender = rs.getString("p_gender");
	                String illness = rs.getString("p_illness");
	                String doa = rs.getString("doa");
	                String dod = rs.getString("dod");
	                
	                deluxemodel.addRow(new Object[]{bedno,id, name,age,contact,gender,illness,doa,dod});
	                
	            }
	            } 
	        catch(Exception e){
	            e.printStackTrace();                
	            } 
	        finally {
	        	d.close();
	        }
	        
	        JTable deluxetable = new JTable(deluxemodel);
	        JScrollPane deluxepane = new JScrollPane(deluxetable);
	        deluxepane.setBounds(10,70,1240,420);
	       
	        dpanel.add(deluxepane);
	        dpanel.setVisible(true);
	   	        
	        
	        JPanel ipanel = new JPanel();
	        ipanel.setOpaque(true);
	        ipanel.setLayout(null);
	        ipanel.setBounds(10,70,screenSize.width-20,screenSize.height-(screenSize.height/4));
	  
	        
	        DefaultTableModel icumodel = new DefaultTableModel();
	        icumodel.addColumn("<html><h3>Bed No.</h3></html>");   
	        icumodel.addColumn("<html><h3>Patient Id</h3></html>");   
	        icumodel.addColumn("<html><h3>Patient Name</h3></html>");  
	        icumodel.addColumn("<html><h3>Patient age</h3></html>");   
	        icumodel.addColumn("<html><h3>Patient Contact</h3></html>");   
	        icumodel.addColumn("<html><h3>Gender</h3></html>");   
	        icumodel.addColumn("<html><h3>Illness</h3></html>");   
	        icumodel.addColumn("<html><h3>Admit Date</h3></html>");  
	        icumodel.addColumn("<html><h3>Discharge Date</h3></html>");   
	        
	        
	        try{           
	            
	        	d.open();
	            
	            PreparedStatement pst = d.conn.prepareStatement("Select I.bedno,P.p_id, P.p_nm, P.p_age, P.p_contact, P.p_gender, P.p_illness, I.doa, I.dod from icu I INNER JOIN patients P where I.p_id = P.p_id");                
	            ResultSet rs = pst.executeQuery();                        
	            while(rs.next())
	            {   
	                String bedno = rs.getString("bedno");
	                String id = rs.getString("p_id");  
	                String name = rs.getString("p_nm");
	                String age = rs.getString("p_age");
	                String contact = rs.getString("p_contact");
	                String gender = rs.getString("p_gender");
	                String illness = rs.getString("p_illness");
	                String doa = rs.getString("doa");
	                String dod = rs.getString("dod");
	                
	                icumodel.addRow(new Object[]{bedno,id, name,age,contact,gender,illness,doa,dod});
	                
	            }
	            } 
	        catch(Exception e){
	            e.printStackTrace();                
	            } 
	        finally {
	        	d.close();
	        }
	        
	        JTable icutable = new JTable(icumodel);
	        JScrollPane icupane = new JScrollPane(icutable);
	        icupane.setBounds(10,70,1240,420);
	       
	        ipanel.add(icupane);
	        ipanel.setVisible(true);
	        
	        JPanel opanel = new JPanel();
	        opanel.setOpaque(true);
	        opanel.setLayout(null);
	        opanel.setBounds(10,70,screenSize.width-20,screenSize.height-(screenSize.height/4));
	  
	        
	        DefaultTableModel otmodel = new DefaultTableModel();
	        otmodel.addColumn("<html><h3>Bed No.</h3></html>");   
	        otmodel.addColumn("<html><h3>Patient Id</h3></html>");   
	        otmodel.addColumn("<html><h3>Patient Name</h3></html>");  
	        otmodel.addColumn("<html><h3>Patient age</h3></html>");   
	        otmodel.addColumn("<html><h3>Patient Contact</h3></html>");   
	        otmodel.addColumn("<html><h3>Gender</h3></html>");   
	        otmodel.addColumn("<html><h3>Illness</h3></html>");   
	        otmodel.addColumn("<html><h3>Admit Date</h3></html>");  
	        otmodel.addColumn("<html><h3>Discharge Date</h3></html>");   
	        
	        
	        try{           
	            
	      	  	d.open();
	            
	            PreparedStatement pst = d.conn.prepareStatement("Select O.bedno,P.p_id, P.p_nm, P.p_age, P.p_contact, P.p_gender, P.p_illness, O.doa, O.dod from ot O INNER JOIN patients P where O.p_id = P.p_id");                
	            ResultSet rs = pst.executeQuery();                        
	            while(rs.next())
	            {   
	                String bedno = rs.getString("bedno");
	                String id = rs.getString("p_id");  
	                String name = rs.getString("p_nm");
	                String age = rs.getString("p_age");
	                String contact = rs.getString("p_contact");
	                String gender = rs.getString("p_gender");
	                String illness = rs.getString("p_illness");
	                String doa = rs.getString("doa");
	                String dod = rs.getString("dod");
	                
	                otmodel.addRow(new Object[]{bedno,id, name,age,contact,gender,illness,doa,dod});
	                
	            }
	            } 
	        catch(Exception e){
	            e.printStackTrace();                
	            } 
	        finally {
	        	d.close();
	        }
	        
	        JTable ottable = new JTable(otmodel);
	        JScrollPane otpane = new JScrollPane(ottable);
	        otpane.setBounds(10,70,1240,420);
	       
	        opanel.add(otpane);
	        opanel.setVisible(true);
	        
	        tabpane2.setTabPlacement(SwingConstants.LEFT);
	        
	            tabpane2.add(roompanel,"<html>R<br>O<br>O<br>M</html>");
               	tabpane2.add(gpanel,"<html>G<br>E<br>N<br>E<br>R<br>A<br>L</html>");
	            tabpane2.add(dpanel,"<html>D<br>E<br>L<br>U<br>X<br>E</html>");
	            tabpane2.add(ipanel,"<html>I<br>C<br>U</html>");
	            tabpane2.add(opanel,"<html>O<br>T</html>");

	        
	        //***BILL PAYMENT START***//
	        final JPanel panel5 = new JPanel();
	        panel5.setLayout(null);
	        panel5.setOpaque(true);
	        panel5.setBounds(5,110,screenSize.width-10,screenSize.height-(screenSize.height/4));
			
			
		// ID PANEL start	
	        JPanel billidpanel = new JPanel();
	        billidpanel.setLayout(null);
	        billidpanel.setBounds(450,10,500,50);

	        final JTextField billidfield = new JTextField("Enter id");
	        JButton billidbutton = new JButton("Show");
	        billidfield.setBounds(5,5,200,30);
	        billidbutton.setBounds(300,5,200,30);

	        billidpanel.add(billidfield);
	        billidpanel.add(billidbutton);
		
	        // ID PANEL end
			
			
	        // BILL ENTRY PANEL start

	        final JTextField bill_item = new JTextField("Bill Item");
	        bill_item.setBounds(140,130,300,30);

	        final JTextField bill_amt = new JTextField("Bill Amount");
	        bill_amt.setBounds(140,200,300,30);

	        JButton bill_submit = new JButton("Add");
	        bill_submit.setBounds(240,260,75,30);

	        JButton total_amt = new JButton("Show Total Amount");
	        total_amt.setBounds(180,320,230,30);

	        final JPanel billentrypanel = new JPanel();
	        billentrypanel.setLayout(null);
	        billentrypanel.setBounds(20,100,600,400);
	        billentrypanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			
			
		bill_submit.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {  
	                PreparedStatement pstmt = null;
	                try 
	                {
	                    int a = Integer.parseInt(billidfield.getText());
	                
	                    DBConn d = new DBConn(); 
	                	System.out.println("Connecting to database...");
	                	d.open();
	                  
	                  String query = "insert into patient_"+a+"(bill_item, billamount) values(?, ?)";
				
	                  pstmt = d.conn.prepareStatement(query);
	                  pstmt.setString(1, bill_item.getText()); 
	                  pstmt.setString(2, bill_amt.getText()); 
	                  
	                  pstmt.executeUpdate(); // execute insert statement
	                  
	                } catch (Exception e) 
	                {
	                  e.printStackTrace();
	                } 
	            }
	        });

        
			
		    //***BILL DISPLAY PANEL***//
	        billidbutton.addActionListener(new ActionListener()
	            {
	            public void actionPerformed(ActionEvent ae)
	            {
	                try{
	                int a = Integer.parseInt(billidfield.getText());
	                
	        DBConn d = new DBConn(); 
        	
        	d.open();
	                
	            // FOR PATIENT NAME start
	               Statement s = d.conn.createStatement();
	               ResultSet r = s.executeQuery("SELECT * FROM patients WHERE p_id="+a+"");
	               r.next();
	               String patient_name = r.getString("p_nm");
	               String patient_age = r.getString("p_age");
	               String patient_sex = r.getString("p_gender");
	               
	               

	               patient_name_label = new JTextField("Patient Name: "+patient_name);
	               billentrypanel.add(patient_name_label);
	               patient_name_label.setBounds(20,20,150,30);
	               patient_name_label = new JTextField("Patient Age: "+patient_age);
	               billentrypanel.add(patient_name_label);
	               patient_name_label.setBounds(220,20,150,30);
	               patient_name_label = new JTextField("Patient Sex: "+patient_sex);
	               billentrypanel.add(patient_name_label);
	               patient_name_label.setBounds(420,20,150,30);

	                // FOR PATIENT NAME END
	                }
	                catch(Exception e)
	                {
	                    e.printStackTrace();
	                }
	                
	                
	                //*** BILL TABLE START***//
	                billtablemodel = new DefaultTableModel();
	                billtablemodel.addColumn("Bill Item");   
	                billtablemodel.addColumn("Bill Amount");                   
				
	                try 
		     	{
	                   
	                	DBConn d = new DBConn(); 
	                	
	                	d.open();
	                    	
	                   int a = Integer.parseInt(billidfield.getText());
	                
	                    PreparedStatement pst = d.conn.prepareStatement("Select * from patient_"+a+"");                
	                    ResultSet rs = pst.executeQuery();       
	                    
	                    while(rs.next())
	                    {   
	                        
	                        String billitem = rs.getString("bill_item");
	                        String billamount = rs.getString("billamount");
	                        billtablemodel.addRow(new Object[]{billitem, billamount});
	                        
	                    }
	                    billtable = new JTable(billtablemodel);
	                    billdisplayscroll = new JScrollPane(billtable);
	                    billdisplayscroll.setBounds(20,20,470,280);
	                    billdisplaypanel.add(billdisplayscroll);
	                }
	                catch(Exception e)
	                {
	                    e.printStackTrace();                
	                } 
	                
	            }
	        });
	        
	        
	        
	        total_amt.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
	                billtablemodel.fireTableDataChanged();
	                billtable.repaint();
	                
	                
	                
	                 try 
		          	{
	                   
	                	 DBConn d = new DBConn(); 
	                 
	                 	d.open();
	                		
	                   int a = Integer.parseInt(billidfield.getText());
	                   
	                     Statement s = d.conn.createStatement();
	                     ResultSet rs = s.executeQuery("SELECT * FROM patient_"+a+"");
	                              
	                                   
	                    totalsum1 = 0;
	                    int sum1 = 0;
	                    
	                    while(rs.next())
	                    {   
	                   	  
	                        String amt = rs.getString("billamount");
	                        sum1 = Integer.parseInt(amt);
	                        totalsum1 = totalsum1 +sum1;
	                    }

	                }
	                catch(Exception e)
	                {
	                    e.printStackTrace();                
	                } 
	                String total2 = String.valueOf(totalsum1);
	                totaldisplaylabel = new JLabel();
	                totaldisplaylabel.setText("");
	                billdisplaypanel.add(totaldisplaylabel);
	                totaldisplaylabel.setText("Amount payable: "+total2);
	                 
	                 JTextField totaltext = new JTextField();
	                 totaltext.setText("Amount payable: "+total2);
	                 billdisplaypanel.add(totaltext);
	                 totaltext.setBounds(150,300,200,30);
	                System.out.println(total2);
	                 
	            }
	        });
	        
	        billdisplaypanel = new JPanel();
	        billdisplaypanel.setLayout(null);
	        panel5.add(billdisplaypanel);
	        billdisplaypanel.setBounds(700,100,500,400);
	        billdisplaypanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));


	        billentrypanel.add(bill_item);
	        billentrypanel.add(bill_amt);
	        billentrypanel.add(total_amt);
	        billentrypanel.add(bill_submit);

	        panel5.add(billentrypanel);
	        panel5.add(billidpanel);
			
	
			

	        //add panels to tabpane
	        tabpane.setTabPlacement(SwingConstants.TOP);
	        
		
		    tabpane.add(panel1,"<html><b><i>NEW PATIENT</i></b></html>");
	        tabpane.add(panel2,"<html><b><i>PATIENT RECORDS</i></b></html>");
	        tabpane.add(panel3,"<html><b><i>DOCTOR RECORDS</i></b></html>");
	        tabpane.add(panel4,"<html><b><i>ROOM OCCUPANCY</i></b></html>");
	        tabpane.add(panel5,"<html><b><i>BILL</i></b></html>");
	        
	        //add mainbodypanel
	        menupageframe.add(mainbodypanel);
	    }
		
	    
	    public static void main(String args[])
	    {
	        SwingUtilities.invokeLater(new Runnable()
	        {
	                public void run()
	                {
	                        new Menu();
	                }
	        });
	    }


	}




