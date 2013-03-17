/*this class implements ActionListener and contains methods which can be invoked by the owner of 
the bank.ownerenter()-this class contains the frame which accepts the password from the owner 
and verifies it, if the password is correct then it displays the methods which can be invoked by 
the owner.ownerset()-this method is called if password is correct and it displays the methods of 
the owner and when any method is selected then the corresponding method is called, its return 
type is void.addemployee-this method allows the owner to add new employee, it accepts data about 
the new employee and enters it in the database.*/

package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class owner implements ActionListener
{
private JButton b;
private JButton b1;
private JButton b2;
private JButton b3;
private JButton b5;
private JButton b6;
private JButton b7;
private JButton b8;
private JButton b9;
private JButton b10;
private JButton searchbutton;
private JButton b11;
private JButton loanlist;
private JButton sanction;
private JButton sanctionbutton;
private JTextField sanc;
private JTextField jtf1;
private JTextField jtf2;
private JTextField jtf3;
private JTextField jtf4;
private JTextField sav;
private JTextField cur;
private JTextField high;
private JTextField fix;
private JTextField empid;
private String acctype1;
private String acctype2;
private String acctype3;
private myframe f1;
private myframe f2;
private myframe f3;
private myframe f4;
private myframe f5;
private myframe sanctionfr;
void ownerenter()
{
f1=new myframe();
JPanel p=new JPanel();
JLabel l=new JLabel("ENTER OWNER PASSWORD");
jtf1=new JPasswordField(10);
b=new JButton("Enter");
p.add(l);
p.add(jtf1);
p.add(b);
b.addActionListener(this);
f1.getContentPane().add(p);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
f1.setLocation(screenWidth / 3, screenHeight / 9);
f1.setSize(300,400);
f1.addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  f1.setState(Frame.NORMAL);
	  f1.show(); 
         } 
      } );
f1.show();
}
public void actionPerformed(ActionEvent e)
{
int flag=0,emid=1,flag2=0;
if(e.getSource()==b)
{
String pass=jtf1.getText();
if(pass.compareTo("manu")==0)
{
f1.setVisible(false);
ownerset();
}
else
{
JOptionPane.showMessageDialog(null,"WRONG PASSWORD","ERROR", JOptionPane.ERROR_MESSAGE);
}
}
if(e.getSource()==b1)
{
accset();
}
if(e.getSource()==b2)
{
addemployee();
}
if(e.getSource()==b3)
{
String name=jtf3.getText();
String addr=jtf4.getText();
if(name.compareTo("")==0||addr.compareTo("")==0)
{
JOptionPane.showMessageDialog(null,"ERROR","FILL FIELDS PROPERLY",JOptionPane.ERROR_MESSAGE);
flag=1;
}
if(flag==0)
{
f3.setVisible(false);
try
{
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
Statement stmt2=conn.createStatement();
Statement stmt3=conn.createStatement();
Statement stmt4=conn.createStatement();
ResultSet rs=stmt2.executeQuery("select * from empid");
while(rs.next())
{
emid=rs.getInt("id");
}
stmt.executeUpdate("insert into employee(name,password,addr,userid)"+"values('"+name+"','"+emid+"','"+addr+"','"+emid+"')");
stmt4.executeUpdate("insert into password10(username,usertype,passwd,accno,dated,userid)"+"values('"+name+"','employee','"+emid+"',-1,sysdate,'"+emid+"')");
int newemid=emid+1;
stmt3.executeUpdate("update empid set id="+newemid);
}
catch(Exception e1)
{
System.out.println(e1);
flag2=1;
}
if(flag2==1)
{
JOptionPane.showMessageDialog(null,"ERROR","EMPLOYEE NOT CREATED",JOptionPane.ERROR_MESSAGE);
}
else
{
JOptionPane.showMessageDialog(null,"PASSWORD AND USERID IS"+emid,"MESSAGE",JOptionPane.INFORMATION_MESSAGE);
}
}
}
if(e.getSource()==b7)
{
int flag1=0;
String sav1=sav.getText();
String cur1=cur.getText();
String high1=high.getText();
String fix1=fix.getText();
int sav2=-1,cur2=-1,high2=-1,fix2=-1;
try
{
sav2=Integer.parseInt(sav1);
cur2=Integer.parseInt(cur1);
high2=Integer.parseInt(high1);
fix2=Integer.parseInt(fix1);
}
catch(Exception xm)
{
JOptionPane.showMessageDialog(null,"FILL INTEREST RATES PROPERLY","ERROR",JOptionPane.ERROR_MESSAGE);
flag1=1;
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
Statement stmt4=conn.createStatement();
stmt.executeUpdate("update interest set rate="+sav2+"where acctype='Saving'");
stmt2.executeUpdate("update interest set rate="+cur2+"where acctype='Current'");
stmt3.executeUpdate("update interest set rate="+high2+"where acctype='High Value'");
stmt4.executeUpdate("update interest set rate="+fix2+"where acctype='Fixed Deposit'");
}
catch(Exception ec)
{
}
}
}
if(e.getSource()==b8)
{
System.out.println("b8 called");
employeelog();
}
if(e.getSource()==b9)
{
int empflag=0;
String empid1=empid.getText();
int empid2=-1;
try
{
empid2=Integer.parseInt(empid1);
}
catch(Exception ir)
{
JOptionPane.showMessageDialog(null,"EMPLOYEE ID HAS TO BE INTEGER","ERROR",JOptionPane.ERROR_MESSAGE);
empflag=1;
}
if(empflag==0)
{
showemployeelog(empid2);
}
}
if(e.getSource()==b10)
{
searchframe();
}
if(e.getSource()==b11)
{
bankbalance();
}
if(e.getSource()==loanlist)
{
seeloanlist();
}
if(e.getSource()==sanction)
{
sanctionloan();
}
if(e.getSource()==sanctionbutton)
{
int sacflag=0,exflag=0;
int givflag=0,acflag=0;;
String sac=sanc.getText();
int sac2=-1;
try
{
sac2=Integer.parseInt(sac);
}
catch(Exception nh)
{
JOptionPane.showMessageDialog(null,"ACCOUNT NUMBER HAS TO BE INTERGER","ERROR",JOptionPane.ERROR_MESSAGE);
sacflag=1;
}
if(sacflag==0)
{
try
{
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from loan");


while(rs.next())
{
int snac=rs.getInt("loanid");
if(snac==sac2)
{
System.out.println("snac="+snac+"sac2="+sac2);
acflag=1;
givflag=rs.getInt("givenflag");
System.out.println("givflag="+givflag);
if(givflag==0)
{
stmt.executeUpdate("update loan set givenflag="+1+"where loanid="+sac2);
sanctionfr.setVisible(false);
}
if(givflag==1)
{
JOptionPane.showMessageDialog(null,"ALREADY SANCTIONED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);

}
}
}
if(acflag==0)
{
JOptionPane.showMessageDialog(null,"ACCOUNT NUMBER NOT FOUND","ERROR",JOptionPane.ERROR_MESSAGE);
}
}
catch(Exception yh)
{
System.out.println(yh+"inside owner in sanctionbutton");
JOptionPane.showMessageDialog(null,"OPERATION UNSUCESSFUL","ERROR",JOptionPane.ERROR_MESSAGE);
exflag=1;
}
if(exflag==0&&givflag==0&&acflag==1)
{
JOptionPane.showMessageDialog(null,"OPERATION SUCCESSFUL","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}
}
}
}
void ownerset()
{
f2=new myframe();
f2.setTitle("WELCOME OWNER");
JPanel p3=new JPanel();
f2.getContentPane().add(p3);
b1=new JButton("ADD AND EDIT ACCOUNT TYPE");
b2=new JButton("ADD NEW EMPLOYEE");
b5=new JButton("ADD SPECIAL CUSTOMER");
b6=new JButton("REMOVE SPECIAL CUSTOMER");
b8=new JButton("SEE EMPLOYEE LOG");
b10=new JButton("SEARCH");
b11=new JButton("TOTAL BANK BALANCE");
loanlist=new JButton("SEE LIST OF LOANS");
sanction=new JButton("SANCTION LOAN");
Box p2=Box.createVerticalBox();
p2.add(b1);
p2.add(b2);
//p2.add(b5);
//p2.add(b6);
p2.add(b8);
p2.add(b11);
p2.add(loanlist);
p2.add(sanction);
p3.add(p2);
b1.addActionListener(this);
b2.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);
b8.addActionListener(this);
b11.addActionListener(this);
loanlist.addActionListener(this);
sanction.addActionListener(this);
f2.setSize(300,300);
f2.setLocation(300,200);
f2.addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  f2.setState(Frame.NORMAL);
	  f2.show(); 
         } 
      } );
f2.show();
}
void addemployee()
{
f3=new myframe();
JPanel p3=new JPanel();
JLabel l1=new JLabel("Enter employee name");
jtf3=new JTextField(10);
JLabel l2=new JLabel("Enter employee address");
jtf4=new JTextField(10);
b3=new JButton("Enter");
p3.add(l1);
p3.add(jtf3);
p3.add(l2);
p3.add(jtf4);
p3.add(b3);
b3.addActionListener(this);
f3.getContentPane().add(p3);
f3.setSize(300,300);
f3.addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  f3.setState(Frame.NORMAL);
	  f3.show(); 
         } 
      } );
f3.show();
}
void accset()
{
f4=new myframe();
f4.setTitle("ENTER PER MONTH RATE OF INTEREST");
JPanel p4=new JPanel();
f4.getContentPane().add(p4);
JLabel l1=new JLabel("SAVING");
sav=new JTextField("Enter rate of interest");
JLabel l2=new JLabel("CURRENT");
cur=new JTextField("Enter rate of interest");
JLabel l3=new JLabel("HIGH VALUE");
high=new JTextField("Enter rate of interest");
JLabel l4=new JLabel("FIXED DEPOSIT");
fix=new JTextField("Enter rate of interest");
b7=new JButton("Ok");
Box b3 = Box.createVerticalBox();
b3.add(l1);
b3.add(sav);
b3.add(l2);
b3.add(cur);
b3.add(l3);
b3.add(high);
b3.add(l4);
b3.add(fix);
b3.add(b7);
p4.add(b3);
b7.addActionListener(this);
f4.setSize(300,300);
f4.addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  f4.setState(Frame.NORMAL);
	  f4.show(); 
         } 
      } );
f4.show();
}
void employeelog()
{
System.out.println("inside employeelog");
f5=new myframe();
f5.setTitle("EMPLOYEE LOG");
JPanel p=new JPanel();
JLabel l=new JLabel("Enter employee id");
empid=new JTextField(10);
b9=new JButton("ok");
p.add(l);
p.add(empid);
p.add(b9);
f5.getContentPane().add(p);
b9.addActionListener(this);
f5.setLocation(300,300);
f5.setSize(300,300);
f5.addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  f5.setState(Frame.NORMAL);
	  f5.show(); 
         } 
      } );
f5.show();
}
void showemployeelog(int empid)
{
int i=0;
myframe f9=new myframe();
JPanel panet1=new JPanel();
final String[] colHeads={"Empid","Toaccno","Fromaccno","Transaction","Chequeno","Draftno","Money Transacted","Date"};
Object[][] data=new Object[10][8];
int flag2=0;
try
{
System.out.println("inside trans");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from employeelog");
data=new Object[20][8];

while(rs.next())
{
System.out.println("inside while");
int emid=rs.getInt("empid");
if(emid==empid)
{
System.out.println("inside if");
int toac=rs.getInt("toaccno");
int fromac=rs.getInt("fromaccno");
int nbalance=rs.getInt("money");
int cheno=rs.getInt("chequeno");
int drano=rs.getInt("draftno");
int empid2=rs.getInt("empid");
String trtype=rs.getString("transtype");
java.util.Date dated=rs.getDate("dated"); 
String nbalance1=Integer.toString(nbalance);
String toac1=Integer.toString(toac);
String fromac1=Integer.toString(fromac);
String cheno1=Integer.toString(cheno);
String drano1=Integer.toString(drano);
String empids=Integer.toString(empid2);
data[i][0]=empids;
data[i][1]=toac1;
data[i][2]=fromac1;
data[i][3]=trtype;
data[i][4]=nbalance1;
data[i][5]=cheno1;
data[i][6]=drano1;
data[i][7]=dated;
i=i+1;
flag2=1;
}
}
}
catch(SQLException e)
{
System.out.println(e);
}
JTable table=new JTable(data,colHeads);
int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
JScrollPane jsp=new JScrollPane(table,v,h);
panet1.add(jsp,BorderLayout.CENTER);
f9.setTitle("TRANSACTION HISTORY");
f9.setSize(650,500);
f9.setResizable(false);
f9.getContentPane().add(panet1);
if(flag2==1)f9.show();			
if(flag2==0)
{
JOptionPane.showMessageDialog(null,"EMPLOYEE ID NOT FOUND","ERROR",JOptionPane.ERROR_MESSAGE);
}
}
void searchframe()
{
myframe search=new myframe();
JPanel p=new JPanel();
searchbutton=new JButton("SEARCH BY ID");
}
private void bankbalance()
{
float bal=0;
float netloan=0;
try
{
System.out.println("inside trans");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();

ResultSet rs2=stmt.executeQuery("select * from loan");
float loan=0,namount=0,tloan=0,tpaid=0;
int givflag=0;
while(rs2.next())
{
givflag=rs2.getInt("givenflag");
if(givflag==1)
{
loan=rs2.getInt("iamount");
tloan=tloan+loan;
namount=rs2.getInt("namount");
float diff=loan-namount;
if(diff>0)
{
tpaid=tpaid+diff;
}
}
}
netloan=tloan-tpaid;
float depo=0;
ResultSet rs=stmt.executeQuery("select * from customer4");
while(rs.next())
{
System.out.println("inside while to find total deposit");
depo=rs.getInt("ideposit");
System.out.println("deposit="+depo);
bal=bal+depo;
System.out.println("bal="+bal);
}
}
catch(Exception dx)
{
System.out.println(dx+"in owner class searchframe function");
}
float netbal=bal-netloan;
System.out.println("netbal="+netbal+"bal="+bal+"netloan="+netloan);
String bal1=Float.toString(netbal);
JOptionPane.showMessageDialog(null,"TOTAL MONEY IN BANK IS:"+bal1,"INFORMATION",JOptionPane.INFORMATION_MESSAGE);
/*myframe balfr=new myframe();
JPanel p=new JPanel();
JLabel l=new JLabel("TOTAL MONEY IN BANK IS:"+bal1);
p.add(l);
balfr.getContentPane().add(p);
balfr.setSize(300,300);
balfr.setLocation(300,200);
balfr.show();*/
}
public void seeloanlist()
{
int i=0;
myframe f9=new myframe();
JPanel panet1=new JPanel();
final String[] colHeads={"Toaccno","INITIAL AMOUNT","PRESENT AMOUNT","SANCTION FLAG","DATE","LOANID"};
Object[][] data=new Object[100][6];
int flag2=0;
try
{
System.out.println("inside transe");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from loan");
data=new Object[100][6];
while(rs.next())
{
System.out.println("inside while");
int toac=rs.getInt("accno");
int iamount=rs.getInt("iamount");
int namount=rs.getInt("namount");
int sanctionflag=rs.getInt("givenflag");
int loanid=rs.getInt("loanid");
java.util.Date dated=rs.getDate("idate"); 
String toac1=Integer.toString(toac);
String iamount1=Integer.toString(iamount);
String namount1=Integer.toString(namount);
String loanid1=Integer.toString(loanid);
String sanctionflag1=" ";
if(sanctionflag==1)
{
sanctionflag1="LOAN SANCTIONED";
}
if(sanctionflag==0)
{
sanctionflag1="LOAN NOT SANCTIONED";
}
data[i][0]=toac1;
data[i][1]=iamount1;
data[i][2]=namount1;
data[i][3]=sanctionflag1;
data[i][4]=dated;
data[i][5]=loanid1;
i=i+1;
flag2=1;
}
}
catch(SQLException e)
{
System.out.println(e);
}
JTable table=new JTable(data,colHeads);
int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
JScrollPane jsp=new JScrollPane(table,v,h);
panet1.add(jsp,BorderLayout.CENTER);
f9.setTitle("TRANSACTION HISTORY");
f9.setSize(650,500);
f9.setResizable(false);
f9.getContentPane().add(panet1);
f9.show();			
}
void sanctionloan()
{
sanctionfr=new myframe();
sanctionfr.setTitle("SANCTION LOAN");
JPanel p=new JPanel();
JLabel l=new JLabel("Enter Loan Id:");
sanc=new JTextField(10);
sanctionbutton=new JButton("Ok");
Box k=Box.createVerticalBox();
k.add(l);
k.add(sanc);
k.add(sanctionbutton);
p.add(k);
sanctionfr.getContentPane().add(p);
sanctionfr.setSize(300,300);
sanctionfr.setLocation(300,100);
sanctionbutton.addActionListener(this);
sanctionfr.show();
}
}