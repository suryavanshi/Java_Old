/*this class extends myframe and implements ActionListener. The methods are check()-the 
method displays the gui frame which accepts account number from the employee and then calls 
other method which displays the information.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class checkframe extends myframe implements ActionListener
{
void check() throws SQLException
{
try
{
setTitle("JAVA BANK CHECK ACCOUNT");
JPanel pane3=new JPanel();
JLabel label3=new JLabel("Enter Account Number");
jtf3=new JTextField(10);
button5=new JButton("Enter");
//button9=new JButton("Back");
pane3.add(label3);
pane3.add(jtf3);
pane3.add(button5);
//pane3.add(button9);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
//setSize(screenWidth / 2, screenHeight / 2);
setLocation(screenWidth / 4, screenHeight / 4);
Image img = tk.getImage("icon.gif");
// setIconImage(img);
setSize(350,200);
setResizable(false);
//f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
getContentPane().add(pane3);
show();
button5.addActionListener(this);
//button9.addActionListener(this);
}
catch(Exception e)
{
System.out.println(e);
}
}
public void actionPerformed(ActionEvent a)
{
String h=jtf3.getText();
int h1=-1;
try
{
h1=Integer.parseInt(h);
}
catch(NumberFormatException e2){}
if(h1==-1)
{
enterframe ef=new enterframe();
ef.error("ACCOUNT NUMBER HAS TO BE NUMBER");
}
checkaccount ck=new checkaccount();
try
{
setVisible(false);
if(h1!=-1)ck.check2(h1);
}
catch(Exception e)
{
}
}
public JButton button5;
public JTextField jtf3;
String type;
}
