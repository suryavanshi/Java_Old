/*this class extends myframe and implements ActionListener. The methods are depocshfr()-the 
methods displays the  gui form in which the account number and money to be deposited is 
entered, it then calls the function which processes the transaction and updates accounts. 
This function performs the verification on number fields.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class depocashframe extends myframe implements ActionListener
{
int empid;
void depocashfr(int empid1)
{
empid=empid1;
setTitle("JAVA BANK DEPOSIT MONEY CASH");
JPanel pane5=new JPanel();
JLabel label6=new JLabel("Enter Account Number");
jtf6=new JTextField(10);
JLabel label7=new JLabel("Enter Money To Be Deposited");
jtf7=new JTextField(10);
button11=new JButton("Enter");
pane5.add(label6);
pane5.add(jtf6);
pane5.add(label7);
pane5.add(jtf7);
pane5.add(button11);
//pane5.add(button12);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
setLocation(screenWidth / 4, screenHeight / 4);
setSize(350,200);
setResizable(false);
//f5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
getContentPane().add(pane5);
show();
//button12.addActionListener(this);
button11.addActionListener(this);
}
public void actionPerformed(ActionEvent a)
{
String j=jtf6.getText();
String d=jtf7.getText();
int j1=-1,d1=-1;
try
{
j1=Integer.parseInt(j);
d1=Integer.parseInt(d);
}
catch(NumberFormatException e4){}
if(j1==-1||d1==-1)
{
enterframe ef=new enterframe();
ef.error("ACCOUNT NUMBER AND MONEY DEPOSIT SHOULD BE NUMBER");
}
setVisible(false);
depositmoney dm=new depositmoney();
try
{
if(j1!=-1&&d1!=-1)dm.dep(j1,d1,empid);
}
catch(Exception e)
{
}
}
public JButton button11;
public JTextField jtf6;
public JTextField jtf7;
}