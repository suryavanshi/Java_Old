/*this class contains method check2() which is called when a employee wants to check the account 
of a customer, the argument passed to it are the account number of the customer, its return type 
is void.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class checkaccount
{
public void check2(int acn1) throws SQLException
		{
		
		try
		{
		int naccno=acn1;
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
	Connection conn = DriverManager.getConnection
        ("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from customer4");
		int f2=0;
		while(rs.next())
		{
		int nac=rs.getInt("accno");
		if(nac==naccno)
		{
		//repaint();
		String nname=rs.getString("name");
		int nbalance=rs.getInt("ideposit");
		String naddr=rs.getString("addr");
		String nacctype=rs.getString("acctype");
		JFrame f19=new JFrame();
		JPanel panet1=new JPanel();
		f19.getContentPane().add(panet1);
		JTextField labelt1=new JTextField("NAME IS---"+nname);
		JTextField  labelt2=new JTextField ("BALANCE IS---"+nbalance);
		JTextField  labelt3=new JTextField ("ADDRESS IS---"+naddr);
		JTextField  labelt4=new JTextField ("ACCOUNT TYPE IS---"+nacctype);
		panet1.add(labelt1);
		panet1.add(labelt2);
		panet1.add(labelt3);
		panet1.add(labelt4);
		labelt1.setEditable(false);
		labelt2.setEditable(false);
		labelt3.setEditable(false);
		labelt4.setEditable(false);		
		Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension d = tk.getScreenSize();
      int screenHeight = d.height;
      int screenWidth = d.width;
      //setSize(screenWidth / 2, screenHeight / 2);
      f19.setLocation(screenWidth / 4, screenHeight / 4);
      Image img = tk.getImage("icon.gif");
      f19.setTitle("CHECK ACCOUNT DISPLAY");
	f19.setIconImage(img);
		
		f19.setSize(250,210);	
		f19.show();			
		f2=1;
		}
		}
		if(f2==0)
		{
		enterframe g1=new enterframe();
		g1.error("INVALID ACCOUNT NUMBER");
		}
		}
		catch(SQLException e)
		{
		System.out.println(e);
		}
		
		}
		
}