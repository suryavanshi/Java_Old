/*this class contain methods which allow deposition of money in the bank through cheque, draft,
and cash, it extends myframe and implements ActionListener. The methods are deposit()-it 
displays the options through which money can be deposited and on selection of any method calls 
the corresponding method.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class depositframe extends myframe implements ActionListener
{
int empid;
void deposit(int empid1) throws SQLException
{
empid=empid1;
try
{
System.out.println("yes2");
setTitle("JAVA BANK DEPOSIT MONEY");
JPanel pane5=new JPanel();
button1=new JButton("BY CHEQUE");
button2=new JButton("BY DRAFT");
button3=new JButton("BY CASH");
//button11=new JButton("Enter");
button12=new JButton("Back");
pane5.add(button1);
pane5.add(button2);
pane5.add(button3);
//pane5.add(button11);
//pane5.add(button12);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
//setSize(screenWidth / 2, screenHeight / 2);
setLocation(screenWidth / 4, screenHeight / 4);
Image img = tk.getImage("manu.jpg");
// setIconImage(img);
setSize(350,200);
setResizable(false);
//f5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
getContentPane().add(pane5);
show();
//button12.addActionListener(this);
button1.addActionListener(this);
button2.addActionListener(this);
button3.addActionListener(this);
}
catch(Exception e)
{
System.out.println(e);
}
}
public void actionPerformed(ActionEvent a)
{
setVisible(false);
Object source=a.getSource();
if(source==button1)
{
depochequeframe dcf=new depochequeframe();
dcf.depochequefr(empid);
}
if(source==button2)
{
System.out.println("source is button2");
depodraftframe ddf=new depodraftframe();
ddf.depodraftfr(empid);
}
if(source==button3)
{
depocashframe dcash=new depocashframe();
dcash.depocashfr(empid);
}

}
public JButton button1;
public JButton button2;
public JButton button3;

public JButton button11;
public JButton button12;
public JTextField jtf6;
public JTextField jtf7;
String type;
}
