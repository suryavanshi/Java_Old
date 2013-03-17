/*This class contain two methods-(1)guimain-provides the options 
which are available to the empoyee.(2)newacc()-creates a new account
 and information about the customer in the database.*/

package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class employee extends myframe implements ActionListener
{
int empid;
public void guimain(String str,int id)
{
empid=id;
setTitle("JAVA BANK EMPLOYEE OPTIONS");
JPanel pane=new JPanel();
enterframe ef=new enterframe();
JLabel label44=new JLabel("WELCOME----"+str);
ImageIcon img2 = new ImageIcon("x:\\images\\new.gif");
ImageIcon img3 = new ImageIcon("x:\\images\\search.gif");
button1=new JButton("New Account");
button2=new JButton("Check Account");
button3=new JButton("Deposit Money");
button4=new JButton("Withdraw Money");
button14=new JButton("Transaction History");
button15=new JButton("Close Account");
button17=new JButton("Issue Dratft");
button18=new JButton("Updation of passbook");
button16=new JButton("Search",img3);
button19=new JButton("Change Password");
button20=new JButton("Inter account transactions");
button21=new JButton("CANCEL DRAFT");
listofloan=new JButton("SEE LIST OF LOANS");
loan=new JButton("LOAN");
pay2=new JButton("Pay back");
button13=new JButton("Exit");
Box b3 = Box.createVerticalBox();
//b3.add(Box.createGlue());
b3.add(label44);
b3.add(button1);
b3.add(button2);
b3.add(button3);
b3.add(button4);
b3.add(button14);
b3.add(button15);
b3.add(button20);
b3.add(button17);
//b3.add(button18);
//b3.add(button16);
b3.add(button21);
b3.add(pay2);
b3.add(button19);
b3.add(loan);
b3.add(listofloan);

//b3.add(button13);
pane.add(b3);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
setLocation(screenWidth / 3, screenHeight / 29);
Image img = tk.getImage("icon.gif");
setSize(330,500);
setResizable(false);
getContentPane().add(pane);

//f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
button1.addActionListener(this);
button2.addActionListener(this);
button3.addActionListener(this);
button4.addActionListener(this);
button14.addActionListener(this);
button13.addActionListener(this);
button15.addActionListener(this);
button17.addActionListener(this);
button18.addActionListener(this);
button19.addActionListener(this);
button20.addActionListener(this);
button21.addActionListener(this);
loan.addActionListener(this);
pay2.addActionListener(this);
listofloan.addActionListener(this);
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

public void actionPerformed(ActionEvent a)
{
Object source=a.getSource();
String s = a.getActionCommand();
if(source==button1)
{

infoframe ife=new infoframe();
ife.information();
}
if(source==button2)
{

try
{
checkframe cf=new checkframe();
cf.check();
}
catch(Exception e)
{
System.out.println(e);
}
}
if(source==button3)
{
System.out.println("inside deposit");
try
{
depositframe df=new depositframe();
df.deposit(empid);
}
catch(Exception e)
{
}
}
if(source==button4)
{
try
{
withdrawframe wf=new withdrawframe();
wf.withdraw(empid);
}
catch(Exception e)
{
System.out.println(e);
}
}
if(source==button14)
{
try
{
employeetranshistory wf=new employeetranshistory();
wf.transframe();
}
catch(Exception e)
{
System.out.println(e);
}
}
if(source==button15)
{
closeaccount bac=new closeaccount();
bac.closeaccfr();
}
if(source==button17)
{
issuedraft id=new issuedraft();
id.issuedraftfr();
}
if(source==button18)
{
bookwrite bk=new bookwrite();
bk.writebookfr();
}
if(source==button19)
{
changepass cp=new changepass();
cp.changepassfr(empid);
}
if(source==button20)
{
intertrans ct=new intertrans();
ct.calintertransfr();
}
if(source==button21)
{
canceldraft cd=new canceldraft();
cd.cancelfr();
}
if(source==loan)
{
applyloan();
}
if(source==apply)
{
String acno1=acno.getText();
String amt1=amt.getText();
int acno2=-1,amt2=-1;
int loanflag=0;
try
{
acno2=Integer.parseInt(acno1);
amt2=Integer.parseInt(amt1);
}
catch(Exception ug)
{
JOptionPane.showMessageDialog(null,"FILL INTEGER FIELDS PROPERLY","ERROR",JOptionPane.ERROR_MESSAGE);
loanflag=1;
}
if(loanflag==0)
{
try
{
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
ResultSet rs2=stmt.executeQuery("select * from loanid");
int loanid=0;
while(rs2.next())
{
loanid=rs2.getInt("id");
}
int nid=loanid+1;
stmt.executeUpdate("update loanid set id="+nid);
int acfound=0;
ResultSet rs=stmt.executeQuery("select * from customer4 where accno="+acno2);
while(rs.next())
{
int acn=rs.getInt("accno");
System.out.println("acn="+acn+"acno2="+acno2);
if(acno2==acn)
{
acfound=1;
}
}
if(acfound==1)
{
stmt.executeUpdate("insert into loan(accno,iamount,pamount,namount,idate,udate,givenflag,pdate,loanid) values('"+acno2+"','"+amt2+"','"+amt2+"','"+amt2+"',sysdate,sysdate,0,sysdate,'"+loanid+"')");;
JOptionPane.showMessageDialog(null,"TO KNOW LOAN IS SANCTIONED OR NOT COME AFTER ONE WEEK","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
applyloan.setVisible(false);
}
if(acfound==0)
{
JOptionPane.showMessageDialog(null,"ACCOUNT NUMBER NOT FOUND","ERROR",JOptionPane.ERROR_MESSAGE);
}
}
catch(Exception mv)
{
System.out.println(mv+"in employeeclass in actionlistener");
}
}
}
if(source==pay2)
{
loanpayment();
}
if(source==paybutton)
{
String acne1=acn.getText();
String amtp1=amtp.getText();
int payflag1=0;
int acne2=-1,amtp2=-1;
try
{
acne2=Integer.parseInt(acne1);
amtp2=Integer.parseInt(amtp1);
}
catch(Exception nd)
{
System.out.println(nd+"in employee in paybutton if action apply button");
JOptionPane.showMessageDialog(null,"FILL NUMERIC FIELDS PROPERLY","ERROR",JOptionPane.ERROR_MESSAGE);
payflag1=1;
}
if(payflag1==0)
{
int acn=0;
int pamt=0;
int givflag=0;
try
{
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from loan where accno="+acne2);
System.out.println("accn="+acne2);
int payacfound=0;
while(rs.next())
{
acn=rs.getInt("accno");
pamt=rs.getInt("namount");
givflag=rs.getInt("givenflag");
System.out.println("acn="+acn+"accne2="+acne2+"pamt="+pamt+"amtp2="+amtp2);
if(acne2==acn&&pamt>=amtp2&&givflag==1)
{
System.out.println("found correct pam="+pamt+amtp2+acne2);
payacfound=1;
}
}
if(payacfound==1)
{
int newpamt=pamt-amtp2;
stmt.executeUpdate("update loan set namount="+newpamt+"where accno="+acne2);
stmt.executeUpdate("update loan set udate=sysdate where accno="+acne2);
JOptionPane.showMessageDialog(null,"PAYMENT MADE","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
pay.setVisible(false);
}
if(payacfound==0)
{
JOptionPane.showMessageDialog(null,"WRONG DATA","ERROR",JOptionPane.ERROR_MESSAGE);
}
}
catch(Exception mv)
{
System.out.println(mv+"in employeeclass in actionlistener in apply button");
}
}
}
if(source==listofloan)
{
owner ow=new owner();
ow.seeloanlist();
}
}
public void newacc(customer c,int gid,String gnm,String gad,String email) throws SQLException
{
int userid=1,flag=0;
try
{
String name=c.name;
int accno=c.accno;
String addr=c.addr;
String addr2=c.addr2;
int balance=c.balance;	
String acctype=c.acctype;
int guranterac=c.guranterac;
int guranterid=gid;
String guranter=gnm;
String gaddr=gad;
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
Calendar cal=Calendar.getInstance();
//Date d=new Date();
//System.out.println(d);
int c1=cal.get(Calendar.DATE);
int c2=cal.get(Calendar.MONTH);
int c3=cal.get(Calendar.YEAR);
String st1=Integer.toString(c1);
String st2=Integer.toString(c2);
String st3=Integer.toString(c3);
String st4=st1+"-"+st2+"-"+st3;
ResultSet rs=stmt.executeQuery("select * from idac");
while(rs.next())
{
accno=rs.getInt("acno");
System.out.println(accno);
}
userid=accno;
int newaccno=accno+1;
int newuserid=userid+1;
stmt.executeUpdate("update idac set acno="+newaccno );
stmt.executeUpdate("insert into customer4(name,userid,accno,addr,addr2,guranter,gaddr,guserid,gaccno,ideposit,acctype,odate,idate,passwd,email)"+"values('" + name + "','" + userid + "','" + accno + "','" + addr +"','" + addr2 + "','" + guranter + "','" + gaddr + "','" + guranterid + "','" + guranterac + "','" + balance + "','" + acctype + "',sysdate,sysdate,88,'"+email+"')");
System.out.println(st4);
stmt.executeUpdate("insert into trans(name,accno,acctype,trtype,money,dated)"+"values('" + name +"','" + accno + "','" + acctype + "','deposit','"+ balance+ "','" + st4 + "')");
stmt.executeUpdate("insert into trans5(toaccno,moneydep,dated,transtype) values('"+accno+"','"+balance+"',sysdate,'initialdepo')");
stmt.executeUpdate("insert into password10(username,usertype,passwd,accno,dated,userid)"+"values('" + name + "','user',88,'"+accno+"',sysdate,'"+userid+"')");
}
catch(SQLException e)
{
System.out.println(e);
flag=1;
}
depositmoney dm1=new depositmoney();
if(flag==0)dm1.update("CONGRATULATIONS YOUR ACCOUNT IS CREATED ACCNO AND USERID="+userid+"PASSWORD="+88);
}
void applyloan()
{
applyloan=new myframe();
JPanel p=new JPanel();
JLabel l=new JLabel("Enter account number of applicant");
acno=new JTextField(10);
JLabel l2=new JLabel("Enter loan amount");
amt=new JTextField(10);
apply=new JButton("Ok");
Box b4 = Box.createVerticalBox();
b4.add(l);
b4.add(acno);
b4.add(l2);
b4.add(amt);
b4.add(apply);
p.add(b4);
apply.addActionListener(this);
applyloan.getContentPane().add(p);
applyloan.setSize(300,300);
applyloan.setLocation(300,200);
applyloan.show();
}
void loanpayment()
{
System.out.println("inside loanpayment");
try
{
pay=new myframe();
JPanel p=new JPanel();
JLabel l=new JLabel("Enter account number");
acn=new JTextField(10);
JLabel l2=new JLabel("Enter amount to be paid");
amtp=new JTextField(10);
paybutton=new JButton("Ok");
Box bb=Box.createVerticalBox();
bb.add(l);
bb.add(acn);
bb.add(l2);
bb.add(amtp);
bb.add(paybutton);
p.add(bb);
pay.getContentPane().add(p);
paybutton.addActionListener(this);
pay.setSize(300,300);
pay.setLocation(300,200);
pay.show();
}
catch(Exception cz)
{
System.out.println(cz+"inside loanpayment employee");
}
}
private myframe applyloan;
private myframe pay;
private JButton button1;
private JButton button2;
private JButton button3;
private JButton button4;
private JButton button13;
private JButton button14;
private JButton button15;
private JButton button16;
private JButton button17;
private JButton button18;
private JButton button19;
private JButton button20;
private JButton button21;
private JButton listofloan;
private JButton loan;
private JButton pay2;
private JButton payloan;
private JButton apply;
private JButton paybutton;
private JTextField acn;
private JTextField amtp;
private JTextField acno;
private JTextField amt;
public String name;
public String password;
public int userid;
public String addr;
public String addr2;
                
}
