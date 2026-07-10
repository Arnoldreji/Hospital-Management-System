
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class RoomAdmit {

	   
    JFrame roomframe;
    JPanel roompane;
    JButton  admitbutton;
    JComboBox<String> roomcb;
    
  String ward;
  int bed_no,n;
  
	
        RoomAdmit()
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
                JPanel admitpanel = new JPanel();
    	        admitpanel.setLayout(null);		
    	        admitpanel.setBounds(450, 150, 450, 300);
    	        admitpanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
    	        
    	        
    	        
    	        JLabel admitlabel = new JLabel("Admit Patient");
    	        admitlabel.setBounds(150,0,300,100);
    	        admitlabel.setFont(new Font("TimesNewRoman",Font.BOLD,24));                

    	        final JTextField pid = new JTextField("Enter ID of Patient");
    	        pid.setBounds(70,80,300,50);

    	        roomcb=new JComboBox<String>();
    		    roomcb.setBounds(70,150,300,50);
    	     	roomcb.setFont(new Font("TimesNewRoman",Font.BOLD,16));
    			roomcb.addItem("--Select Ward--");
    			
    			DBConn d = new DBConn();
    			Statement smt;
    			ResultSet rst;
    			try
    			{   d.open();
    				smt = (Statement) d.conn.createStatement();
    			
    		    	rst=smt.executeQuery("select count(*) from room");
    		    	rst.next();
    				 n=rst.getInt(1);
    			      	String data[][]=new String[n][4];

    		    	rst=smt.executeQuery("select * from room");
    		     	for(int i=0;rst.next();i++)
    		     	{
    		     		data[i][0] = rst.getString(2);
    		     		data[i][1] = rst.getString(3);
    		     		
    		     	}
    		     		
    		          		//***Checking Room occupancy***/
                       		smt = d.conn.createStatement();
                       		rst=smt.executeQuery("select * from room");
                       		while(rst.next())
                       		{
                       		int num=Integer.parseInt(rst.getString(4));
                       		
                      		if(num > 0)	
                       		{
                       		roomcb.addItem(rst.getString(2));
    		     		
                       		}
                       		}
    		   
    			}
    			
    		     	catch(SQLException e)
    				{
    					e.printStackTrace();
    				}
    			
    			finally {
    				d.close();
    			}
    	

    	        JButton admitbutton = new JButton("Admit");
    	        admitbutton.setBounds(120,220,200,50);

    	
                roomframe.add(pid);
                roomframe.add(admitbutton);
                roomframe.add(admitpanel);
    	 
    	        admitpanel.add(admitlabel);
    	        admitpanel.add(pid);
    	        admitpanel.add(roomcb);
    	        admitpanel.add(admitbutton);
       
                roomframe.add(panel);
                roomframe.add(heading);
                roomframe.add(headerpanel);
                roomframe.add(headerpanel1);
                roomframe.setVisible(true);   
     
                /***ADMIT BUTTON ACTION***/
                admitbutton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                    	PreparedStatement pstmt = null, pst=null;
                	
                      	DBConn d = new DBConn();
                     	
                      	 String ward = roomcb.getSelectedItem().toString();
                         int a = Integer.parseInt(pid.getText());
                         System.out.println(ward);
                         try {
                        	 d.open();
                        	 
                      	    Statement smt,stmt; 
                        	 ResultSet rs,rst;
                        	 
                        	 /***Check Patient admit status***/
                        	 stmt =  d.conn.createStatement(); 
                        	 rs= stmt.executeQuery("select * from "+ward);
                        	  while(rs.next())
                        	 {                        		 
                        		 int b=Integer.parseInt(rs.getString(2));
                        		System.out.println(b);
                        		
                        		if(a == b) {
                        		JOptionPane.showMessageDialog(null, "Patient already admitted","Error",JOptionPane.ERROR_MESSAGE);
                        		 }
                        	 
                        		 else{	
                       		 
                           Date doa= new Date();
                           SimpleDateFormat ft =new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");		 
                         
                          /***Update admit status in patients table***/ 
                          pstmt = d.conn.prepareStatement("UPDATE patients SET ward=?, admitstatus=?, doa=?, dod=?  WHERE p_id=?"); 
                          pstmt.setString(1, ward); 
                          pstmt.setString(2, "Admitted");
                          pstmt.setString(3,ft.format(doa));
                          pstmt.setString(4,"Not discharged");
                          pstmt.setInt(5, a);
                          
                          pstmt.executeUpdate(); 
                          
                 
                          /***Update admit status in respective ward table***/
                        	smt = d.conn.createStatement();
                            rst = smt.executeQuery("select max(bedno) from "+ ward);
                            rst.next();
                  			int bedno=rst.getInt(1)+1;
                          	pst = d.conn.prepareStatement("Insert into "+ward+" (bedno,p_id,doa,dod) values (?,?,?,?)");
                          	
                          	pst.setInt(1, bedno);
                          	pst.setInt(2, a);
                            pst.setString(3,ft.format(doa));
                            pst.setString(4,"Not discharged");
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
                       		PreparedStatement psmt = d.conn.prepareStatement("UPDATE room SET emptybeds=? where roomtype= '"+ward+"'");
                       		psmt.setString(1, x);
                       		psmt.executeUpdate();
                       		JOptionPane.showMessageDialog(null, "Patient admitted successfully");
                         	 break;
                      	}
                        		 }
                        	 }
                   
                        	 new RoomAdmit();	 
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
                    new RoomAdmit();
                    
                }});
    		
    	}
    }


