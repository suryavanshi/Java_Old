/*this class implements ActionListener, it contain method which accepts information about a draft
which is to be deposited, it displays a form in which information about the draft is given by 
the employee, it then calls the method which processes the transaction and updates various 
accounts.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class depodraftframe  implements ActionListener 
{
public myframe f;
int empid;
void depodraftfr(int empid1)
{
empid=empid1;
try
{
f=new myframe();
System.out.println("here inside depodraftframe");
f.setTitle("DEPOSIT BY DRAFT");
JPanel p=new JPanel();
f.getContentPane().add(p);
JLabel l1=new JLabel("Enter Account Number");
jtf1=new JTextField(15);
JLabel l2=new JLabel("Enter draft Number");
jtf2=new JTextField(10);
JLabel l3=new JLabel("Enter money deposited ");
jtf3=new JTextField(10);
JLabel l4=new JLabel("Enter Bank Name");
jtf4=new JTextField("JAVA BANK");
jtf4.setEditable(false);
//JLabel l5=new JLabel("Enter issuee  Name");
//jtf5=new JTextField(10);
JLabel l6=new JLabel("Enter from account number");
jtf6=new JTextField(10);
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
b.add(l6);
b.add(jtf6);
b.add(b1);
p.add(b);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
f.setLocation(screenWidth / 4, screenHeight / 4);
f.setSize(350,310);
b1.addActionListener(this);
f.addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  f.setState(Frame.NORMAL);
	  f.show(); 
         } 
      } );
f.show();
}
catch(Exception ew){System.out.println("inside depodraftframe"+ew);}
}
public void actionPerformed(ActionEvent e)
{
int flag1=0,flag2=0;
System.out.println("inside actionperformed");
String acno1=jtf1.getText();
String ddno1=jtf2.getText();
String money1=jtf3.getText();
String banknm1=jtf4.getText();
String fromac1=jtf6.getText();
int acno2=-1,ddno2=-1,money2=-1,fromac2=-1;
if(banknm1.compareTo("")==0)
{
JOptionPane.showMessageDialog(null,"FILL ALL FIELDS","ERROR",JOptionPane.ERROR_MESSAGE);
flag1=1;
}
if(flag1==0)
{
try
{
acno2=Integer.parseInt(acno1);
ddno2=Integer.parseInt(ddno1);
money2=Integer.parseInt(money1);
fromac2=Integer.parseInt(fromac1);
}
catch(Exception er){System.out.println("number"+er);}
if(acno2==-1||ddno2==-1||money2==-1||fromac2==-1)
{
JOptionPane.showMessageDialog(null,"ENTER CORRECTLY THE INTEGER FIELDS");
flag2=1;
}
}
if(flag2==0)
{
try
{
System.out.println("inside try calling depodraft");
f.setVisible(false);
depositdraft df=new depositdraft();
df.depodraft(acno2,ddno2,money2,fromac2,banknm1,empid);
}
catch(Exception e1){}
}
}
void draftvisible()
{
System.out.println("inside draftvisible");
f.setVisible(false);
}
public JButton b1;
public JTextField jtf1;
public JTextField jtf2; 
public JTextField jtf3; 
public JTextField jtf4; 
public JTextField jtf5; 
public JTextField jtf6; 
}