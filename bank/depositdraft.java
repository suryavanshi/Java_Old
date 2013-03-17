/*this class contains the methods which accepts information from depodraftframe and then after 
verification processes the information. The methods are depodraft()-the arguments passed to it 
are to account number, draft number, from account number, money to be deposited and bank name, 
it then processes the draft by crediting to toaccountnumber and removing the money from 
fromaccountnumber and storing the transaction in trans5 table in the database.ONLY DRAFT 
ISSUED BY JAVA BANK ARE PROCESSED.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class depositdraft
{
void depodraft(int toacno2,int ddno2,int mondep2,int fromacno2,String banknm2,int empid)
{
System.out.println("inside transdraftdepo");
int flag1=0,flag2=0,flag3=0,ddno=0,fromac=0,ddval=0,flag5=0,flag6=0;
String toname="";
int toaccno=toacno2;
int moneydep=mondep2;
int fromaccno=fromacno2;
int draftno=ddno2;
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
ResultSet rs3=stmt2.executeQuery("select * from issuedraft");
while(rs3.next())
{
ddno=rs3.getInt("ddno");
fromac=rs3.getInt("fromac");
ddval=rs3.getInt("ddval");
toname=rs3.getString("toname");
int depositflag=rs3.getInt("depositflag");
if(draftno==ddno&&moneydep==ddval&&fromaccno==fromac&&depositflag!=1)
{
System.out.println("found draft");
flag5=1;
break;
}
}
if(flag5==0)
{
JOptionPane.showMessageDialog(null,"INVALID DRAFT","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(flag5==1)
{
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
stmt2.executeUpdate("insert into trans5(toaccno,moneydep,chequeno,draftno,fromaccno,frombank,dated,moneyaf,transtype)"+
"values('"+toaccno+"','"+moneydep+"','-1','"+draftno+"','"+fromaccno+"','"+bankname+"',sysdate,'"+moneyaf+"','depositdraft')");
stmt.executeUpdate("update customer4 set ideposit="+moneyaf+"where accno="+toaccno);
stmt4.executeUpdate("update issuedraft set depositflag="+1+"where ddno="+draftno);
stmt4.executeUpdate("insert into employeelog(toaccno,money,chequeno,draftno,fromaccno,dated,transtype,empid) values('"+toaccno+"','"+moneydep+"','-1','"+draftno+"','"+fromaccno+"',sysdate,'depositdraft','"+empid+"')");
}
}
}
if(flag3==0)
{
JOptionPane.showMessageDialog(null,"TOACNO NOT FOUND","ERROR",JOptionPane.ERROR_MESSAGE);
}
}

catch(Exception es)
{
JOptionPane.showMessageDialog(null,"OPERATION NOT SUCESSFULL","ERROR",JOptionPane.ERROR_MESSAGE);
System.out.println("maro maro"+es);
flag6=1;
}
if(flag3==1 && flag6==0)
{

JOptionPane.showMessageDialog(null,"DRAFT DEPOSITED SUCESSFULLY","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}
}
}