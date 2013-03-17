/*this class allows the empoyee and customer to change his password, it implements ActionListener. 
It contains method changepassfr which accepts old password and new password two times and 
verifies if the old password is correct. The argument passed to it are userid of the user who 
wants to change his account.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class changepass implements ActionListener
{
public myframe f;
public JButton sub;
public JTextField uid;
public JTextField opass;
public JTextField npass;
public JTextField rpass;
void changepassfr(int id)
{
f=new myframe();
JPanel p=new JPanel();
JLabel l=new JLabel("User Id");
String sid=Integer.toString(id);
uid=new JTextField(sid,10);
uid.setEditable(false);
JLabel l2=new JLabel("Enter old Password");
opass=new JPasswordField(10);
JLabel l3=new JLabel("Enter new Password");
npass=new JPasswordField(10);
JLabel l4=new JLabel("Retype new Password");
rpass=new JPasswordField(10);
sub=new JButton("Submit");
Box b3 = Box.createVerticalBox();
b3.add(l);
b3.add(uid);
b3.add(l2);
b3.add(opass);
b3.add(l3);
b3.add(npass);
b3.add(l4);
b3.add(rpass);
b3.add(sub);
p.add(b3);
sub.addActionListener(this);
f.getContentPane().add(p);
f.setSize(250,300);
f.setLocation(200,100);
f.show();
}
public void actionPerformed(ActionEvent e)
{
System.out.println("inside actionperformed");
int flag1=0,flag2=0;
String opass2=opass.getText();
String npass2=npass.getText();
String sid2=uid.getText();
String rpass2=rpass.getText();
int sid3=Integer.parseInt(sid2);
int opass3=-1,npass3=-1,rpass3=-1;
try
{
opass3=Integer.parseInt(opass2);
npass3=Integer.parseInt(npass2);
rpass3=Integer.parseInt(rpass2);
}
catch(Exception em)
{
JOptionPane.showMessageDialog(null,"PASSWORD HAS TO BE INTEGER","ERROR",JOptionPane.ERROR_MESSAGE);
flag1=1;
}
if(flag1==0)
{
try
{
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
Statement stmt2=conn.createStatement();
Statement stmt3=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from password10 where userid="+sid3);
while(rs.next())
{
int uid6=rs.getInt("userid");
if(uid6==sid3)
{
int upass=rs.getInt("passwd");
if(opass3==upass)
{
System.out.println("found");
flag2=1;
}
}
}
if(flag2==0)
{
JOptionPane.showMessageDialog(null,"WRONG OLD PASSWORD","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag2==1)
{
if(npass3==rpass3)
{
try
{
System.out.println("id="+sid3+"pass="+npass3);
stmt2.executeUpdate("update password10 set passwd=" + npass3 + "where userid=" + sid3);
stmt3.executeUpdate("update customer4 set passwd="+npass3+"where userid="+sid3);
JOptionPane.showMessageDialog(null,"PASSWORD CHANGED SUCESSFULLY","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
f.setVisible(false);
}
catch(Exception df){System.out.println("maro"+df);}
}
if(npass3!=rpass3)
{
JOptionPane.showMessageDialog(null,"NEW PASSWORD DON'T RETYPED PASSWORD","ERROR",JOptionPane.ERROR_MESSAGE);
}
}
}
catch(Exception ed){System.out.println("sql excep"+ed);}
}
}
}