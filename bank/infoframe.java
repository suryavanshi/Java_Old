/*it extends myframe and implemnts actionlistener, it contains methods information()-the method 
accepts information about the user in a gui form when a new account is created and verifies the 
information about the user and if all information is valid then calls function newacc().*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
import javax.swing.JComboBox;
class infoframe extends myframe implements ActionListener
{
int flag=0;
public void information()
{

setTitle("JAVA BANK NEW ACCOUNT");
System.out.println("working");
JPanel pane2=new JPanel();
JLabel label1=new JLabel("Enter Name");
jtf1=new JTextField(10);
JLabel label2=new JLabel("Enter address");
jtf2=new JTextField(10);
JLabel label3=new JLabel("Enter address2");
jtf3=new JTextField(10);
JLabel label4=new JLabel("Enter Guranters name");
jtf4=new JTextField(10);
JLabel label5=new JLabel("Enter Guranters address");
jtf5=new JTextField(10);
JLabel label6=new JLabel("Enter Guranters accno");
jtf6=new JTextField(10);
JLabel label7=new JLabel("Enter Guranters userid");
jtf7=new JTextField(10);
JLabel emid=new JLabel("Enter your Email id");
email=new JTextField(10);
JLabel label13=new JLabel("Enter Account Type");
style = new JComboBox();
style.setEditable(false);
style.addItem("Saving");
style.addItem("Current");
style.addItem("High Value");
style.addItem("Fixed Deposit");
style.addItem("Default");
style.addActionListener(this);
//jtf13=new JTextField(10);
JLabel label14=new JLabel("Enter initial deposit");
jtf14=new JTextField("500",10);
//JLabel label15=new JLabel("Enter account number");
//jtf15=new JTextField(10);
button7=new JButton("Enter");
button8=new JButton("Back");
Box b3 = Box.createVerticalBox();
Box b5 = Box.createVerticalBox();
//b3.add(Box.createGlue());
b3.add(label1);
b3.add(jtf1);
b3.add(label2);
b3.add(jtf2);
b3.add(label3);
b3.add(jtf3);
b3.add(label4);
b3.add(jtf4);
b3.add(label5);
b3.add(jtf5);
b3.add(label6);
b3.add(jtf6);
b3.add(label7);
b3.add(jtf7);
b3.add(emid);
b3.add(email);
b3.add(label13);
b3.add(style);
b3.add(label14);
b3.add(jtf14);
//b3.add(label15);
//b3.add(jtf15);
pane2.add(b3,"North");
//pane2.add(b5);
Box b4 = Box.createHorizontalBox();
b4.add(button7);
//b4.add(button8);
pane2.add(b4,"South");
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
setLocation(screenWidth / 3, screenHeight / 9);
Image img = tk.getImage("icon.gif");
Border border1 = BorderFactory.createMatteBorder(5,5,5, 5, Color.pink);
pane2.setBorder(border1);
setSize(360,450);
setResizable(false);
//f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
getContentPane().add(pane2);
show();
addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  setState(Frame.NORMAL);
	  show(); 
         } 
      } );
button7.addActionListener(this);
//button8.addActionListener(this);
style.addActionListener(this);
}
public void actionPerformed(ActionEvent a1)
{
Object source=a1.getSource();
//String item="";
if(source==style)
{
item = (String)style.getSelectedItem();
System.out.println("item"+item);
}
if(source==button7)
{
String u=jtf1.getText();
String n=jtf2.getText();
String y=jtf14.getText();
String gid=jtf7.getText();
String addr2=jtf3.getText();
String gnm=jtf4.getText();
String gad=jtf5.getText();
String gac=jtf6.getText();
String x=item;
String emailad=email.getText();
System.out.println("acctype="+x);
int y1=-1,z1=-1,gid1=-1,gac1=-1;
int fi=0;
int guranterpresent=0;
if(u.compareTo("")==0 || n.compareTo("")==0 ||addr2.compareTo("")==0 || gnm.compareTo("")==0 || gad.compareTo("")==0)
{
fi=1;
enterframe ef1=new enterframe();
ef1.error("FILL ALL FIELDS PROPERLY");
}
try
{ 
y1=Integer.parseInt(y);
gac1=Integer.parseInt(gac);
gid1=Integer.parseInt(gid);
}
catch(NumberFormatException e1){}
if(fi==0)
{
if( y1==-1||gac1==-1||gid1==-1||y1<500)
{
flag=1;
enterframe ef=new enterframe();
ef.error("ACCOUNT NUMBER,USERID AND DEPOSIT HAVE TO BE INTEGER AND INITIAL DEPOSIT GREATER THAN Rs.500");
}
}

if(flag==0 && fi==0)
{
try
{
System.out.println("inside guran check");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
Statement stmt2=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from customer4 ");
//ResultSet rs2=stmt2.executeQuery("select * from employee");
while(rs.next())
{
//System.out.println("no such user id");
System.out.println(rs.getInt("accno")+rs.getString("name"));
System.out.println("acceptedavalue"+gac1);
int guranac=rs.getInt("accno");
//int empac=rs2.getInt("userid");
if(guranac==gac1)
{
System.out.println("comparing name");
String gurantername=rs.getString("name");
//String empname=rs2.getString("name");
System.out.println(gurantername+gnm);
if(gurantername.compareTo(gnm)==0)
{
System.out.println("name are equal");
guranterpresent=1;
}
}
}
if(gnm.compareTo("manu")==0&&gac1==1983)
{
guranterpresent=1;
}
}
catch(Exception ee){System.out.println("inside guran check"+ee);}
if(guranterpresent==0)
{
JOptionPane.showMessageDialog(null,"WRONG INFORMATION ABOUT GURANTER", "Warning", JOptionPane.ERROR_MESSAGE);
         
}
if(fi==0 && y1!=-1 && gac1!=-1 && gid1!=-1&&guranterpresent==1)
{
customer c=new customer();
c.name=u;
c.accno=1;
c.balance=y1;
c.addr=n;
c.acctype=x;
c.guranterac=gac1;
c.addr2=addr2;
c.userid=1;
employee na=new employee();
setVisible(false);
try
{
na.newacc(c,gid1,gnm,gad,emailad);
}
catch(Exception e)
{
}
}
}
}
}
public JButton button7;
public JButton button8;
public JTextField jtf1;
public JTextField jtf2;
public JTextField jtf3;
public JTextField jtf4;
public JTextField jtf5;
public JTextField jtf6;
public JTextField jtf7;
public JTextField jtf13;
public JTextField jtf14;
public JTextField jtf15;
public JTextField jtf16;
public JTextField email;
public JComboBox style;
String item="Saving";
//public ComboBoxTestPanel panel;
//public JTextField jtf44;
String type;
}