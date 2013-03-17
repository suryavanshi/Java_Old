/*This class is used to withdraw cash money from a account.It accepts account number and money 
to be withdrawn from another class which accepts account number.This class then finds the 
account number is valid or not and whether it contains required amount or not and then processes 
the transaction.*/ 
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class withdrawmoney
{
public void with(int acn3,int mnw,int empid) throws SQLException
{
try
{
int taccno=acn3;
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
Statement stmt2=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from customer4");
int f4=0,f5=0;
while(rs.next())
{
int tac=rs.getInt("accno");
String name=rs.getString("name");
String acctype=rs.getString("acctype");	
int accno=tac;
if(tac==taccno)
{
System.out.println("Enter money to be withdrawn:");
f4=1;
int wit=mnw;
int bal=rs.getInt("ideposit");
if(bal<wit+500)
{
JOptionPane.showMessageDialog(null,"ACCOUNT HAS INSUFFICIENT MONEY","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(bal>=wit+500)
{
f5=1;
int nbal=bal-wit;
stmt.executeUpdate("update customer4 set ideposit=" + nbal+"where accno="+taccno);
Calendar cal=Calendar.getInstance();
int c1=cal.get(Calendar.DATE);
int c2=cal.get(Calendar.MONTH);
int c3=cal.get(Calendar.YEAR);
String st1=Integer.toString(c1);
String st2=Integer.toString(c2);
String st3=Integer.toString(c3);
String st4=st1+"-"+st2+"-"+st3;
System.out.println(st4);
stmt.executeUpdate("insert into trans(name,accno,acctype,trtype,money,dated)"+"values('" + name +"','" + accno + "','" + acctype + "','withdraw','"+ nbal+ "','" + st4 + "')");
stmt2.executeUpdate("insert into trans5(toaccno,moneydep,moneyaf,transtype,dated)"+"values('" + accno +"','" + wit + "','" + nbal + "','withdrawcash',sysdate)");
stmt2.executeUpdate("insert into employeelog(fromaccno,money,dated,transtype,empid) values('"+accno+"','"+wit+"',sysdate,'withdrawcash','"+empid+"')");
}
}
}
if(f4==0)
{
System.out.println("Account number not found!!");
enterframe g1=new enterframe();
g1.error("INVALID ACCOUNT NUMBER");
}
if(f5==1)
{
System.out.println("Accout upadated!!");
depositmoney dm=new depositmoney();
dm.update("MONEY WITHDRAWN");
}
}
catch(SQLException e)
{
System.out.println(e);
}	
}
}
