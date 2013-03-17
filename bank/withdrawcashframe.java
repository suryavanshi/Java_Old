/*this class extends myframe and implements ActionListner. The methods are withdrawcashfr()-it 
accepts the account number and the money to be withdrawn in a gui frame from the employee and 
verifies if all fields are filled properly and then calls the corresponding function.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class withdrawcashframe extends myframe implements ActionListener
{
int empid;
void withdrawcashfr(int empid1) throws SQLException
{
empid=empid1;
try
{
setTitle("JAVA BANK WITHDRAW MONEY");
JPanel pane4=new JPanel();
JLabel label4=new JLabel("Enter Account Number");
jtf4=new JTextField(10);
JLabel label5=new JLabel("Enter Money To Be Withdrawn");
jtf5=new JTextField(10);
button6=new JButton("Enter");
//button10=new JButton("Back");
pane4.add(label4);
pane4.add(jtf4);
pane4.add(label5);
pane4.add(jtf5);
pane4.add(button6);
//pane4.add(button10);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
setLocation(screenWidth / 4, screenHeight / 4);
Image img = tk.getImage("manu.jpg");
setSize(300,300);
setResizable(false);
//f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
getContentPane().add(pane4);
show();
//button10.addActionListener(this);
button6.addActionListener(this);
}
catch(Exception e)
{
System.out.println(e);
}
}
public void actionPerformed(ActionEvent a)
{
String f=jtf4.getText();
String g=jtf5.getText();
int f1=-1,g1=-1;
try
{
f1=Integer.parseInt(f);
g1=Integer.parseInt(g);
}
catch(NumberFormatException e2){}
if(f1==-1 || g1==-1)
{
enterframe ef=new enterframe();
ef.error("ACCOUNT NUMBER AND MONEY TO BE WITHDRAWN HAVE TO BE INTEGER");
}
setVisible(false);
withdrawmoney wm=new withdrawmoney();
try
{
if(f1!=-1 && g1!=-1)wm.with(f1,g1,empid);
}
catch(Exception e)
{
}
}
public JButton button6;
public JButton button10;
public JTextField jtf4;
public JTextField jtf5;
String type;
}