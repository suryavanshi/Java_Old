/*this class implements ActionListener. The methods are withdrawchequefr()-it accepts the 
information about the cheque in a gui frame and then calls actionPerformed()  in this method 
the data is verified and if the data is correct then transaction is processed and recorded with 
the date. AT PRESENT ONLY CHEQUES OF JAVA BANK ARE ACCEPTED.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class withdrawchequeframe  implements ActionListener
{
public myframe f;
public JButton b1;
public JTextField jtf1;
public JTextField jtf2;
public JTextField jtf3;
public JTextField jtf6;
public JTextField jtf5;
int empid;
void withdrawchequefr(int empid1)
{
empid=empid1;
f=new myframe();
f.setTitle("WITHDRAW BY CHEQUE");
JPanel p=new JPanel();
f.getContentPane().add(p);
JLabel l5=new JLabel("Enter To Name");
jtf5=new JTextField(15);
JLabel l1=new JLabel("Enter From Account Number");
jtf1=new JTextField(15);
JLabel l2=new JLabel("Enter Cheque Number");
jtf2=new JTextField(10);
JLabel l3=new JLabel("Enter Bank Name");
jtf3=new JTextField("JAVA BANK");
jtf3.setEditable(false);
JLabel l6=new JLabel("Enter money withdrawn ");
jtf6=new JTextField(10);
b1=new JButton("OK");
Box b = Box.createVerticalBox();
b.add(l5);
b.add(jtf5);
b.add(l1);
b.add(jtf1);
b.add(l2);
b.add(jtf2);
b.add(l3);
b.add(jtf3);
b.add(l6);
b.add(jtf6);
b.add(b1);
p.add(b);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
f.setLocation(screenWidth / 4, screenHeight / 4);
f.setSize(350,350);
b1.addActionListener(this);
f.show();
}
public void actionPerformed(ActionEvent e)
{
int flag=0,flag1=0,flag2=0,flag3=0,flag4=0;
String toname=jtf5.getText();
String fromaccno=jtf1.getText();
String chequeno=jtf2.getText();
String banknm=jtf3.getText();
String monwith=jtf6.getText();
int fromaccno2=-1,chequeno2=-1,monwith2=-1;
if(banknm.compareTo("")==0||toname.compareTo("")==0)
{
flag=1;
JOptionPane.showMessageDialog(null,"FILL ALL FIELDS PROPERLY","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag==0)
{
try
{
fromaccno2=Integer.parseInt(fromaccno);
chequeno2=Integer.parseInt(chequeno);
monwith2=Integer.parseInt(monwith);
}
catch(Exception ec)
{
flag1=1;
System.out.println(ec);
JOptionPane.showMessageDialog(null,"FILL INTEGER FIELDS PROPERLY","ERROR",JOptionPane.ERROR_MESSAGE);
}
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
ResultSet rs=stmt.executeQuery("select * from customer4");
while(rs.next())
{
int acn=rs.getInt("accno");
if(fromaccno2==acn)
{
flag2=1;
int fromacbal=rs.getInt("ideposit");
if(fromacbal>=(monwith2+500))
{
int newfromacbal=fromacbal-monwith2;
stmt2.executeUpdate("update customer4 set ideposit="+newfromacbal+"where accno="+fromaccno);
stmt3.executeUpdate("insert into trans5(toname,fromaccno,chequeno,moneydep,dated,frombank,frommoneyaf,transtype)"+"values('"+toname+"','"+fromaccno2+"','"+chequeno2+"','"+monwith2+"',sysdate,'"+banknm+"','"+newfromacbal+"','cashwithcheque')");                                                     
stmt3.executeUpdate("insert into employeelog(fromaccno,chequeno,money,dated,transtype,empid) values('"+fromaccno2+"','"+chequeno2+"','"+monwith2+"',sysdate,'cashwithcheque','"+empid+"')");
flag3=1;
}
}
}
}
catch(Exception es)
{
System.out.println(es+"yaha");
JOptionPane.showMessageDialog(null,"OPERATION NOT SUCCESFUL","ERROR",JOptionPane.ERROR_MESSAGE);
flag4=1;
}
if(flag4==0&&flag3==1)
{
f.setVisible(false);
JOptionPane.showMessageDialog(null,"OPERATION SUCCESFUL","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}
}
}
}
