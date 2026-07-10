import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.border.BevelBorder;


public class SearchDoc {
	
	JFrame searchdocframe;
	JPanel searchdocpane;
	JComboBox<String> jcbselect;
	
	SearchDoc(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		final JFrame searchdocframe = new JFrame("Search");
		searchdocframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		searchdocframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchdocframe.setLayout(null);
		
		
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
                searchdocframe.setVisible(true);
                new HomePage();
            }
        });
        logout.setBounds((screenSize.width/2)+30,650,100,30);
        searchdocframe.add(logout);
        
        JButton backbutton = new JButton("Back");
        backbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                searchdocframe.setVisible(true);
                new Menu();
            }
        });
        backbutton.setBounds((screenSize.width/2)-170,650,100,30);
        searchdocframe.add(backbutton);
    
        
        
        searchdocpane = new JPanel();
        searchdocpane.setLayout(null);
        searchdocpane.setBounds(450, 120, 600, 200);
        
        //***COMBO BOX ***//
        jcbselect = new JComboBox<String>();
        jcbselect.setBounds(0, 10, 100, 30);
        jcbselect.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
        jcbselect.addItem("doc_id");
        jcbselect.addItem("doc_nm");
        jcbselect.addItem("doc_specialization");
        jcbselect.addItem("doc_add");
        
        final JTextField field = new JTextField("Enter ID/Name/Specialization/Address");
        field.setBounds(125, 10, 225, 30);
        JButton searchdocbutton = new JButton("Search");
        searchdocbutton.setBounds(375, 10, 150, 28);
        
        searchdocpane.add(jcbselect);
        searchdocpane.add(field);
        searchdocpane.add(searchdocbutton);
        
        searchdocframe.add(searchdocpane);
        searchdocframe.add(panel);
        searchdocframe.add(heading);
        searchdocframe.add(headerpanel);
        searchdocframe.add(headerpanel1);
        
        
        searchdocbutton.addActionListener(new ActionListener()
        		{
        		public void actionPerformed(ActionEvent ae)
        		{
        			DefaultTableModel doctormodel = new DefaultTableModel();
        			doctormodel.addColumn("<html><h3>Id</h3></html");
        			doctormodel.addColumn("<html><h3>Name</h3></html");
        			doctormodel.addColumn("<html><h3>Specialization</h3></html");
        			doctormodel.addColumn("<html><h3>Phone Number</h3></html");
        			doctormodel.addColumn("<html><h3>Address</h3></html");
        			
        			
        			PreparedStatement pstmt = null;
        			
        			DBConn d = new DBConn();
        			try {
        				d.open();
        				
        				String select = (String)jcbselect.getSelectedItem();
        				String value = field.getText();
        				String query = "select * FROM doctors WHERE " + select + " = '" + value + "'";
        				pstmt = d.conn.prepareStatement(query);
        				ResultSet rs = pstmt.executeQuery();
        				rs.next();
        				
        				String id = rs.getString("doc_id");
        				String name = rs.getString("doc_nm");
        				String specialization = rs.getString("doc_specialization");
        				String contact = rs.getString("doc_contact");
        				String address = rs.getString("doc_add");
        				
        				doctormodel.addRow(new Object[] {id, name, specialization, contact, address});
        				
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
        			finally{
        				d.close();
        			}
        		
        			JTable doctortable = new JTable(doctormodel);
        			JScrollPane doctorpane = new JScrollPane(doctortable);
        			doctorpane.setBounds(30, 200, 1240, 420);
        			searchdocframe.add(doctorpane);
        			
        		   }
        		});
       
        searchdocframe.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
				{
				public void run() {
					new SearchDoc();
			     	}
				});

	}

}
