package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class intertrans implements ActionListener
{
private myframe f;
private JButton b;
private JTextField jtf;
private JTextField jtf2;
void calintertransfr()
{
f=new myframe();
f.setTitle("INTER ACCOUNT TRANSACTIONS");
JPanel p=new JPanel();
f.getContentPane().add(p);
JLabel l=new JLabel("Enter first account number");
jtf=new JTextField(10);
JLabel l2=new JLabel("Enter second account number");
jtf2=new JTextField(10);
b=new JButton("Enter");
Box b5 = Box.createVerticalBox();
b5.add(l);
b5.add(jtf);
b5.add(l2);
b5.add(jtf2);
b5.add(b);
p.add(b5);
f.setSize(300,300);
f.setLocation(300,100);
f.setResizable(false);
b.addActionListener(this);
f.show();
}
public void actionPerformed(ActionEvent e)
{
int flag=0;
String acno1=jtf.getText();
String acno2=jtf2.getText();
int acno12=-1,acno22=-1;
try
{
acno12=Integer.parseInt(acno1);
acno22=Integer.parseInt(acno2);
}
catch(Exception es)
{
JOptionPane.showMessageDialog(null,"ACCOUNT NUMBER HAS TO BE INTEGER","ERROR",JOptionPane.ERROR_MESSAGE);
flag=1;
}
if(flag==0)
{
f.setVisible(false);
calintertrans(acno12,acno22);
}
}
void calintertrans(int ac1,int ac2)
{
int i=0,flag2=0;
myframe f1=new myframe();
JPanel panet1=new JPanel();
f1.getContentPane().add(panet1);
final String[] colHeads={"Toaccno","Fromaccno","Chequeno","Draftno","Transaction","Moneydep","ToMoneyafter","Date"};
Object[][] data=new Object[10][8];
try
{
System.out.println("inside calintertrans");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from trans5");
data=new Object[10][8];
while(rs.next())
{
int acno1=rs.getInt("toaccno");
int acno2=rs.getInt("fromaccno");
System.out.println("acno1="+acno1+"acno2"+acno2);
if((ac1==acno1 && ac2==acno2)||(ac1==acno2 && ac2==acno1))
{
System.out.println("inside if");
flag2=1;
String toac=Integer.toString(acno1);
String fromac=Integer.toString(acno2);
int cheno=rs.getInt("chequeno");
String chequeno=Integer.toString(cheno);
int mond=rs.getInt("moneydep");
String moneydep=Integer.toString(mond);
String transaction=rs.getString("transtype");
int monaf=rs.getInt("moneyaf");
String tomoneyaf=Integer.toString(monaf);
int ddno=rs.getInt("draftno");
String draftno=Integer.toString(ddno);
java.util.Date d=rs.getDate("dated");
data[i][0]=toac;
data[i][1]=fromac;
data[i][2]=chequeno;
data[i][3]=draftno;
data[i][4]=transaction;
data[i][5]=moneydep;
data[i][6]=tomoneyaf;
data[i][7]=d;
i=i+1;
}
}
}
catch(Exception ed)
{
}
JTable table=new JTable(data,colHeads);
int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
JScrollPane jsp=new JScrollPane(table,v,h);
panet1.add(jsp,BorderLayout.CENTER);
f1.setTitle("TRANSACTION HISTORY");
f1.setSize(650,500);
//f1.setResizable(false);
//f1.getContentPane().add(panet1);
f1.show();			
}
}