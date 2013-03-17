package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class canceldraft implements ActionListener
{
private JButton b;
private JTextField jtf;
private myframe f;
void cancelfr() 
{
int flag1=0;
f=new myframe();
JPanel p=new JPanel();
f.getContentPane().add(p);
JLabel l=new JLabel("Enter draft number");
jtf=new JTextField(10);
b=new JButton("Ok");
Box b5 = Box.createVerticalBox();
b5.add(l);
b5.add(jtf);
b5.add(b);
p.add(b5);
f.setSize(300,300);
f.setLocation(300,100);
f.setResizable(false);
b.addActionListener(this);
f.show();
}
public void actionPerformed(ActionEvent e)
{
String ddno1=jtf.getText();
int ddno2=-1,flag1=0;
try
{
ddno2=Integer.parseInt(ddno1);
}
catch(Exception er)
{
flag1=1;
JOptionPane.showMessageDialog(null,"FILL DRAFT NUMBER PROPERLY","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag1==0)
{
docancel(ddno2);
}
}
void docancel(int ddno)
{
int flag2=0,acn=0,custac=0,custbal=0,ddval=0,depoflag=9;
try
{
System.out.println("inside canceldraft");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
Statement stmt2=conn.createStatement();
Statement stmt3=conn.createStatement();
Statement stmt4=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from issuedraft");
while(rs.next())
{
int getddno=rs.getInt("ddno");
depoflag=rs.getInt("depositflag");
if(getddno==ddno&&depoflag!=1)
{
flag2=1;
acn=rs.getInt("fromac");
ddval=rs.getInt("ddval");
ResultSet rs2=stmt3.executeQuery("select * from customer4 where accno="+acn);
while(rs2.next())
{
custac=rs2.getInt("accno");
if(custac==acn)
{
custbal=rs2.getInt("ideposit");
}
}
int newcustbal=custbal+ddval;
stmt2.executeUpdate("delete issuedraft where ddno="+getddno);
stmt4.executeUpdate("update customer4 set ideposit="+newcustbal+"where accno="+acn);
break;
}
}
if(flag2==0)
{
JOptionPane.showMessageDialog(null,"INVALID DRAFT NUMBER","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag2==1)
{
JOptionPane.showMessageDialog(null,"OPERATION SUCESSFUL","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
f.setVisible(false);
}
if(depoflag==1)
{
JOptionPane.showMessageDialog(null,"CHECK CANNOT BE CANCELLED,ALREADY DEPOSITED","ERROR",JOptionPane.ERROR_MESSAGE);
}
}
catch(Exception es)
{
JOptionPane.showMessageDialog(null,"OPERATION NOT SUCESSFUL","ERROR",JOptionPane.ERROR_MESSAGE);
}
}
}