package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class userframe extends myframe implements ActionListener
{
int userid;
void guiuser(int accno,String str,int uid)
{
userid=uid;
setTitle("JAVA BANK USER OPTIONS");
//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
JPanel pane7=new JPanel();
enterframe ef3=new enterframe();
JLabel label45=new JLabel("WELCOME----"+str);
String ui=Integer.toString(accno);
jtf16=new JTextField(ui);
//jtf16.setText(accno);
button14=new JButton("Check Account");
button15=new JButton("Transaction History");
button16=new JButton("Change Password");
Box b3 = Box.createVerticalBox();
b3.add(label45);
b3.add(button14);
b3.add(button15);
b3.add(button16);
pane7.add(b3);
getContentPane().add(pane7);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
setLocation(screenWidth / 4, screenHeight / 4);
Image img = tk.getImage("icon.gif");
setSize(305,200);
show();
addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  setState(Frame.NORMAL);
	  show(); 
         } 
      } );
button14.addActionListener(this);
button15.addActionListener(this);
button16.addActionListener(this);
}
public JButton button14;
public JButton button15;
public JButton button16;
public JTextField jtf16;
String type;
public void actionPerformed(ActionEvent a)
{
Object source=a.getSource();
if(source==button14)
{
System.out.println("called check3");
String v=jtf16.getText();
int ac=Integer.parseInt(v);
System.out.println("working chk"+ac);
checkacbyuser csu=new checkacbyuser();
try
{
csu.check3(ac);
}
catch(Exception e)
{
System.out.println(e);
}
}
if(source==button15)
{
checktransbyuser ctu=new checktransbyuser();
try
{
System.out.println("called trans");
String ut=jtf16.getText();
System.out.println(ut);
int ac2=Integer.parseInt(ut);
ctu.trans(ac2);
}
catch(Exception e)
{
System.out.println(e);
}
}
if(source==button16)
{
changepass cp2=new changepass();
cp2.changepassfr(userid);
}
}
}
