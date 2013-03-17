package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class issuedraft implements ActionListener
{
private myframe f;
private JTextField jtf;
private JTextField jtf2;
private JTextField jtf3;
private JButton b;
void issuedraftfr()
{
int flag1=0;
f=new myframe();
f.setTitle("ISSUE DRAFT");
JPanel p=new JPanel();
JLabel l=new JLabel("Enter to name");
jtf=new JTextField(10);
JLabel l2=new JLabel("Enter value of draft");
jtf2=new JTextField(10);
JLabel l3=new JLabel("Enter from account number");
jtf3=new JTextField(10);
//JLabel l4=new JLabel("Enter draft number");
b=new JButton("Ok");
Box b5 = Box.createVerticalBox();
b5.add(l);
b5.add(jtf);
b5.add(l2);
b5.add(jtf2);
b5.add(l3);
b5.add(jtf3);
//b5.add(l4);
//b5.add(jtf4);
b5.add(b);
p.add(b5);
b.addActionListener(this);
f.getContentPane().add(p);
f.setSize(300,300);
f.setLocation(300,100);
f.addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  f.setState(Frame.NORMAL);
	  f.show(); 
         } 
      } );
f.show();
}
public void actionPerformed(ActionEvent e)
{
int flag1=0;
String toname=jtf.getText();
String ddval1=jtf2.getText();
String fromac1=jtf3.getText();
//String ddno1=jtf4.getText();
int ddval2=-1,fromac2=-1,ddno2=-1;
try
{
ddval2=Integer.parseInt(ddval1);
fromac2=Integer.parseInt(fromac1);
//ddno2=Integer.parseInt(ddno1);
}
catch(Exception ft)
{
flag1=1;
JOptionPane.showMessageDialog(null,"FILL NUMERIC FIELDS PROPERLY","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag1==0)
{
f.setVisible(false);
calissuedraft(toname,ddval2,fromac2);
}
}
void calissuedraft(String toname,int ddval,int fromac)
{
int draftno=0,fromacbal=0;
int flag2=0,flag3=0,flag4=0;
try
{
System.out.println("inside inisde issuedraft");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
Statement stmt2=conn.createStatement();
Statement stmt3=conn.createStatement();
Statement stmt4=conn.createStatement();
Statement stmt5=conn.createStatement();
ResultSet rs2=stmt4.executeQuery("select * from customer4");
while(rs2.next())
{
int acn=rs2.getInt("accno");
if(fromac==acn)
{
flag2=1;
fromacbal=rs2.getInt("ideposit");
if(fromacbal>=(ddval+500))
{
flag4=1;
}
System.out.println("found");
}
}
if(flag2==0)
{
System.out.println("inside flag2");
JOptionPane.showMessageDialog(null,"INVALID FROM ACCOUNT NUMBER","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag2!=0 && flag4==0)
{
JOptionPane.showMessageDialog(null,"FROM ACCOUNT NUMBER HAS INSUFFICIENT MONEY","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag4==1)
{
int newfromacbal=fromacbal-ddval;
ResultSet rs=stmt.executeQuery("select * from draftno");
System.out.println("inside suf money");
while(rs.next())
{
draftno=rs.getInt("ddno");
}
System.out.println("out of while");
int newddno=draftno+1;
stmt2.executeUpdate("update draftno set ddno="+newddno);
stmt3.executeQuery("insert into issuedraft(toname,ddval,ddno,fromac,depositflag)"+"values('"+toname+"','"+ddval+"','"+draftno+"','"+fromac+"',0)");
stmt5.executeUpdate("update customer4 set ideposit="+newfromacbal+"where accno="+fromac);
f.setVisible(false);
}
}
catch(Exception ex)
{
System.out.println("in issue draft"+ex);
flag3=1;
JOptionPane.showMessageDialog(null,"DRAFT NOT ISSUED","ERROR",JOptionPane.ERROR_MESSAGE);

}
if(flag3==0&&flag4==1)
{
JOptionPane.showMessageDialog(null,"DRAFT ISSUED AND DRAFT NO="+draftno,"INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}
}
}
