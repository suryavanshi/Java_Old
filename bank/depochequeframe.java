/*this class extends myframe and implements ActionListener. The methods 
are depochequefr()-this method displays a gui form in which 
information about the cheque is given by the employee, it then calls 
the function which processes the cheque.*/ 
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class depochequeframe extends myframe implements ActionListener
{
public JTextField jtf1;
public JTextField jtf2;
public JTextField jtf3;
public JTextField jtf4;
public JTextField jtf5;
public JTextField jtf6;
public JTextField jtf7;
public JButton b1;
int empid;
void depochequefr(int empid1)
{
empid=empid1;
setTitle("DEPOSIT BY CHEQUE");
JPanel p=new JPanel();
getContentPane().add(p);
JLabel l1=new JLabel("Enter to Account Number");
jtf1=new JTextField(15);
JLabel l2=new JLabel("Enter Cheque Number");
jtf2=new JTextField(10);
JLabel l3=new JLabel("Enter money to be deposited");
jtf3=new JTextField(10);
JLabel l4=new JLabel("Enter Bank Name");
jtf4=new JTextField("JAVA BANK");
jtf4.setEditable(false);
JLabel l7=new JLabel("Enter from which account number");
jtf7=new JTextField(10);
b1=new JButton("OK");
Box b = Box.createVerticalBox();
b.add(l1);
b.add(jtf1);
b.add(l2);
b.add(jtf2);
b.add(l3);
b.add(jtf3);
b.add(l4);
b.add(jtf4);
b.add(l7);
b.add(jtf7);
b.add(b1);
p.add(b);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
setLocation(screenWidth / 4, screenHeight / 4);
b1.addActionListener(this);
setSize(350,360);
addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  setState(Frame.NORMAL);
	  show(); 
         } 
      } );
show();
}

public void actionPerformed(ActionEvent e)
{
System.out.println("inside actionlistener");
int toacno2=-1,cheno2=-1,mondep2=-1,fromacno2=-1;
int flag1=0,flag2=0;
String toacno1="",cheno1="",mondep1="",banknm="",fromname="",toname="",fromacno1="";
try
{
toacno1=jtf1.getText();
cheno1=jtf2.getText();
mondep1= jtf3.getText();
banknm=jtf4.getText();
fromacno1=jtf7.getText();
}
catch(Exception cv)
{
System.out.println("before try"+cv);
}
if(banknm.compareTo("")==0)
{
System.out.println("inside if invalid");
flag2=1;
JOptionPane.showMessageDialog(null,"FILL THE FIELDS PROPERLY","ERROR", JOptionPane.ERROR_MESSAGE);
}
if(flag2==0)
{
try
{
System.out.println("inside try of frame");
toacno2=Integer.parseInt(toacno1);
cheno2=Integer.parseInt(cheno1);
mondep2=Integer.parseInt(mondep1);
fromacno2=Integer.parseInt(fromacno1);
}
catch(Exception num){System.out.println("exception in frame"+num);}
if(toacno2==-1||cheno2==-1||mondep2==-1||fromacno2==-1)
{
JOptionPane.showMessageDialog(null,"FILL INTEGER FIELDS PROPERLY","ERROR",JOptionPane.ERROR_MESSAGE);
System.out.println("making flag1=1");
flag1=1;
}
}

if(flag1!=1 && flag2!=1)
{
setVisible(false);
transbychequedepo tcd=new transbychequedepo();
tcd.transchequedepo(toacno2,cheno2,mondep2,fromacno2,banknm,empid);
}
}
}