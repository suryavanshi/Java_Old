/*this class contains the method which processes the cheque and updates various accounts. 
The methods are transchequedepo()-the arguments passed to this function are toaccount number, 
fromaccount number, money to be deposited , cheque number and bank name, it verifies the 
account numbers and whether sufficient money is present in the account or not and then 
updates various accounts and records the transaction in trans5 table in database.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class transbychequedepo
{
void transchequedepo(int toacno2,int cheno2,int mondep2,int fromacno2,String banknm2,int empid1)
{

System.out.println("inside transdraftdepo");
int flag1=0,flag2=0,flag3=0,samecheck=0;
int toaccno=toacno2;
int moneydep=mondep2;
int fromaccno=fromacno2;
int chequeno=cheno2;
String bankname=banknm2;
try
{
System.out.println("inside try");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
Statement stmt2=conn.createStatement();
Statement stmt3=conn.createStatement();
Statement stmt4=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from customer4");
ResultSet rs2=stmt3.executeQuery("select * from customer4");
ResultSet rs3=stmt4.executeQuery("select * from trans5 where transtype='chequedepo'");
while(rs3.next())
{
int chno=rs3.getInt("chequeno");
if(chno==chequeno)
{
samecheck=1;
JOptionPane.showMessageDialog(null,"INVALID CHEQUE ALREADY DEPOSITED","ERROR",JOptionPane.ERROR_MESSAGE);
break;
}
}

if(samecheck==0)
{
while(rs.next())
{
System.out.println("inside while");
int acn=rs.getInt("accno");
System.out.println("fromacc="+fromaccno+"==="+acn);
if(fromaccno==acn)
{
int fromacbalance=rs.getInt("ideposit");
System.out.println("found accno");
flag1=1;
if(fromacbalance>(moneydep+500))
{
int newfromacbal=fromacbalance-moneydep;
flag2=1;

while(rs2.next())
{
System.out.println("inside while tosearch toacc");
int toaccnointable=rs2.getInt("accno");
System.out.println("toaccnointable="+toaccnointable+"toaccno="+toaccno);
if(toaccnointable==toaccno)
{
flag3=1;
System.out.println("found toaccno");
int toacideposit=rs2.getInt("ideposit");
int moneyaf=toacideposit+moneydep;
System.out.println("toaccno="+toaccno);
System.out.println("moneydep="+moneydep+"chequeno="+chequeno);
stmt2.executeUpdate("insert into trans5(toaccno,moneydep,chequeno,draftno,fromaccno,frombank,dated,moneyaf,transtype)"+
"values('"+toaccno+"','"+moneydep+"','"+chequeno+"','-1','"+fromaccno+"','"+bankname+"',sysdate,'"+moneyaf+"','chequedepo')");
stmt.executeUpdate("update customer4 set ideposit="+moneyaf+"where accno="+toaccno);
stmt.executeUpdate("update customer4 set ideposit="+newfromacbal+"where accno="+fromaccno);
stmt.executeUpdate("insert into employeelog(toaccno,money,chequeno,draftno,fromaccno,dated,transtype,empid) values('"+toaccno+"','"+moneydep+"','"+chequeno+"','-1','"+fromaccno+"',sysdate,'chequedepo','"+empid1+"')");
}
}
}
}
}
if(flag1==0)
{
JOptionPane.showMessageDialog(null,"FROM ACCNO NOT FOUND","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag2==0)
{
JOptionPane.showMessageDialog(null,"FROMACNO HAS INSUFFICIENT MONEY","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag3==0)
{
JOptionPane.showMessageDialog(null,"TOACNO NOT FOUND","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag3==1)
{
System.out.println("calling draftvisible");
depodraftframe ddfr=new depodraftframe();
//ddfr.draftvisible();
JOptionPane.showMessageDialog(null,"CHEQUE DEPOSITED SUCESSFULLY","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}
}
}
catch(Exception es)
{
JOptionPane.showMessageDialog(null,"OPERATION NOT SUCESSFULL","ERROR",JOptionPane.ERROR_MESSAGE);
System.out.println("maro YAHA"+es);
}


}
}
