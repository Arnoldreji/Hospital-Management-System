
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class RoomDischarge {

	   
    JFrame roomframe;
    JPanel roompane,dischargepane,formpanel;
    JButton  dischargebutton;
    JComboBox<String> roomcb;
    
  String ward;
  int bed_no,n;
  
	
        RoomDischarge()
    	{   
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                
                //*** HEADER PANEL ***//
                final JFrame roomframe = new JFrame("Room");
                roomframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
                roomframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                roomframe.setLayout(null);
                
                
                JPanel wrapper = new JPanel();
                wrapper.setLayout(null);
                
                
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
    	        
                
                //*** FOOTER PANEL***//
    	        JButton logout = new JButton("Log out");
    	        logout.addActionListener(new ActionListener()
    	        {
    	            public void actionPerformed(ActionEvent ae)
    	            {
    	              roomframe.setVisible(true);
    	                new HomePage();
    	            }
    	        });
    	        logout.setBounds((screenSize.width/2)+30,650,100,30);
    	      roomframe.add(logout);
    	        
    	        JButton backbutton = new JButton("Back");
    	        backbutton.addActionListener(new ActionListener()
    	        {
    	            public void actionPerformed(ActionEvent ae)
    	            {
    	              roomframe.setVisible(true);
    	                new Menu();
    	            }
    	        });
    	        backbutton.setBounds((screenSize.width/2)-170,650,100,30);
    	      roomframe.add(backbutton);
           
        
                /*** BODY PANEL***/
                dischargepane = new JPanel();
                dischargepane.setLayout(null);
                dischargepane.setBounds(500,120,400,200);
  
                final JTextField pid = new JTextField("Enter ID");
                pid.setBounds(10,10,150,30);
                JButton viewbutton = new JButton("View");
                viewbutton.setBounds(170,10,150,30);
                
                dischargepane.add(pid);
                dischargepane.add(viewbutton);

                roomframe.add(dischargepane);
                roomframe.add(panel);
                roomframe.add(heading);
                roomframe.add(headerpanel);
                roomframe.add(headerpanel1);
             
      
                formpanel = new JPanel();
                formpanel.setBounds(400,170,600,450);		
                formpanel.setLayout(null);
                    
    		DBConn d = new DBConn();
                viewbutton.addActionListener(new ActionListener()
                {
                public void actionPerformed(ActionEvent ae)
                {d.open();
                    PreparedStatement pstmt = null;
                    try {
                
                       int a = Integer.parseInt(pid.getText());
                      String query = "select * FROM patients WHERE p_id = ?";
                      pstmt = d.conn.prepareStatement(query); // create a statement
                      pstmt.setInt(1, a); // set input parameter 1                  
                      ResultSet rs = pstmt.executeQuery(); // execute insert statement
                        rs.next();
                        String name = rs.getString("p_nm");
                        String age = rs.getString("p_age");
                        String gender = rs.getString("p_gender");
                        String contact = rs.getString("p_contact");
                        String ward= rs.getString("ward");
                       

                        dischargeform(name, age, gender, contact, ward, a);
    					
                    } catch (Exception e) {
                      e.printStackTrace();
                    } 
                    finally{
                    	d.close();
                    }
                }
            });
                    
               roomframe.add(formpanel);     
    		
                roomframe.setVisible(true);
    	}
            
            public void dischargeform(String name, String age, String gender, String contact, String ward ,final int a)
            {
          
            	
            			JLabel dischargelabel = new JLabel("Discharge Patient");
            			dischargelabel.setBounds(220,0,300,100);
            			dischargelabel.setFont(new Font("TimesNewRoman",Font.BOLD,24));       
            			 formpanel.add(dischargelabel);

            			JTextField namefield = new JTextField(name);
                        namefield.setBounds(180,80,280,40);
                        formpanel.add(namefield);

                        JTextField agefield = new JTextField(age);
                        agefield.setBounds(180,130,280,40);
                        formpanel.add(agefield);

                        JTextField genderfield = new JTextField(gender);
                        genderfield.setBounds(180,180,280,40);
                        formpanel.add(genderfield);

                        JTextField contactfield = new JTextField(contact);
                        contactfield.setBounds(180,230,280,40);
                        formpanel.add(contactfield);
                        
                        JTextField wardfield = new JTextField(ward);
                        wardfield.setBounds(180,280,280,40);
                        formpanel.add(wardfield);

                        JButton dischargebutton = new JButton("Discharge");
                        dischargebutton.setBounds(200,380,230,40);
                        dischargebutton.setVisible(true);
                        formpanel.add(dischargebutton);
                        
                        formpanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
                        
                        dischargepane.setVisible(false);
                        formpanel.repaint();

                        
                /***DISCHARGE BUTTON ACTION***/
                dischargebutton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                    	PreparedStatement pstmt = null, pst=null;
                	
                      	DBConn d = new DBConn();
           
                         try {
                        	 d.open();
                        	 
                      	 
                   
                           Date dod= new Date();
                           SimpleDateFormat ft =new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");	
                           

                           Statement smt = d.conn.createStatement(); 
                           ResultSet rst;   
                           
                           /***Update room charges in bill***/
                           rst=smt.executeQuery("Select doa from patients where p_id="+a+"");
              			    rst.next();
              				String dt=rst.getString("doa");
              			    Date doa = ft.parse(dt);
              			    long diff = dod.getTime() - doa.getTime();
              			    long days = (diff / (1000 * 60 * 60 * 24)) % 365;
              			
              		    	ResultSet rs=smt.executeQuery("Select * from room R INNER JOIN patients P where  P.p_id= "+a+"  and R.roomtype = P.ward");
               				rs.next();
               			    long charge = Long.parseLong(rs.getString(5)); 
               			    
               			    PreparedStatement psmt=  d.conn.prepareStatement("Insert into patient_"+a+" (bill_item,billamount) values(?,?)");
               			    psmt.setString(1,"room charge");
               			    psmt.setLong(2, charge*days);
               			    psmt.executeUpdate();
                         
                          /***Update discharge status in patients table***/ 
                          pstmt = d.conn.prepareStatement("UPDATE patients SET ward=?, admitstatus=?, dod=?  WHERE p_id=?"); 
                          pstmt.setString(1, ""); 
                          pstmt.setString(2, "Discharged");
                          pstmt.setString(3,ft.format(dod));
                          pstmt.setInt(4, a);
                          pstmt.executeUpdate(); 
                   
                 
                          /***Update discharge status in respective ward table***/
                        	
                          	pst = d.conn.prepareStatement("Delete from "+ward+" where p_id=?");
                            pst.setInt(1, a);
                            pst.executeUpdate();
             
                   	/***Update room occupancy***/  
                      	rst=smt.executeQuery("select count(*) from room");
                      	rst.next();
                  		int n=rst.getInt(1);
                  		String data[][]=new String[n][4];
                  	    rst=smt.executeQuery("select * from room where roomtype='"+ward+"'");
                       	for(int i=0;rst.next();i++)
                       	{
                       		data[i][0] = rst.getString(3);
                       		data[i][1] = rst.getString(4);
                       		data[i][2] = "";
                       		data[i][3] = "";
                       	}
                       
                       	int i=0;
                     	while(i<n)
                      	{
                       		smt = d.conn.createStatement();
                       		rst=smt.executeQuery("select count(*) from "+ward+" where dod= 'Not discharged'");
                       		rst.next();
                       		int cnt=rst.getInt(1);
                       		data[i][2]=cnt+"";
                       		data[i][3]=Integer.parseInt((String)data[i][0])-cnt+"";
                       		String x = data[i][3];
                       		                       		
                       		/***Update room table***/
                       		PreparedStatement psmt1 = d.conn.prepareStatement("UPDATE room SET emptybeds=? where roomtype= '"+ward+"'");
                       		psmt1.setString(1, x);
                       		psmt1.executeUpdate();
                       		JOptionPane.showMessageDialog(null, "Patient discharged successfully");
                         	 break;
                         	}
         
                        	 new RoomDischarge();	 
                        	  roomframe.setVisible(false);	
                       
                          
                         
                         }
                        catch (Exception e) {
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
                    new RoomDischarge();
                    
                }});
    		
    	}
    }


