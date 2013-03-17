package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class closeaccount implements ActionListener
{
public JTextField jtf;
public JButton b;
public myframe f;
void closeaccfr()
{
System.out.println("inside close frame");
int flag=0;
f=new myframe();
JPanel p=new JPanel();
f.getContentPane().add(p);
JLabel l=new JLabel("Enter account number to be closed");
jtf=new JTextField(10);
b=new JButton("Enter");
p.add(l);
p.add(jtf);
p.add(b);
b.addActionListener(this);
f.setSize(200,200);
f.show();
}
public void actionPerformed(ActionEvent e)
{
String acc=jtf.getText();
int acc2=-1;
f.setVisible(false);
try
{
acc2=Integer.parseInt(acc);
}
catch(Exception e1){}
if(acc2==-1)
{
JOptionPane.showMessageDialog(null,"ACCOUNT NUMBER HAS TO BE INTGER","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(acc2!=-1)
{
closeacc(acc2);
}
}
void closeacc(int acc2)
{
System.out.println("inside close"+acc2);
int flag=0;
try
{
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
stmt.executeUpdate("delete customer4 where accno="+acc2);
}
catch(Exception ed)
{
System.out.println(ed);
flag=1;
}
if(flag==1)
{
JOptionPane.showMessageDialog(null,"not done","error",JOptionPane.ERROR_MESSAGE);
}
if(flag==0)
{
JOptionPane.showMessageDialog(null,"ACCOUNT CLOSED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}
}
}