/*This contains the method which calculates the interest.It finds the 
average amount of money in every account in one month and gives 
interest if period is greater than one month.*/
package bank;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
import java.io.*;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.util.Calendar;
class interest 
{
void calinterest()
{
System.out.println("inside calinterest");
int tr=0;
float rate=6,sav=6,cur=6,high=6,fix=6;
long timet=1,timei=1,timediff=1;
int money2=1,ibalance=0;
try
{
System.out.println("inside trycalinterest");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech9", "200201189", "manu");
Statement stmt=conn.createStatement();
Statement stmt2=conn.createStatement();
Statement stmt3=conn.createStatement();
Statement stmt4=conn.createStatement();
Statement stmt5=conn.createStatement();
Statement stmt6=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from customer4");
ResultSet rs3=stmt6.executeQuery("select * from interest");
while(rs3.next())
{
String actype=rs3.getString("acctype");
if(actype.compareTo("Saving")==0)
{
sav=rs3.getInt("rate");
}
if(actype.compareTo("Current")==0)
{
cur=rs3.getInt("rate");
}
if(actype.compareTo("High Value")==0)
{
high=rs3.getInt("rate");
}
if(actype.compareTo("Fixed Deposit")==0)
{
fix=rs3.getInt("rate");
}
}
while(rs.next())
{
System.out.println("inside whilecalinterest");
java.sql.Date df=new java.sql.Date(10000);
df=rs.getDate("idate");
ibalance=rs.getInt("ideposit");
java.util.Date dd=new java.util.Date(2004,1,1);
Calendar cal=Calendar.getInstance();
//cal.set(2004,1,1);
try
{
timet=cal.getTimeInMillis();
}
catch(Exception time)
{
System.out.println("time error"+time);
}
System.out.println("df="+df);
System.out.println("dd="+dd);
timei=df.getTime();
//timet=dd.getTime();
timediff=timet-timei;
System.out.println("the diff in time="+timediff);
float days=timediff/86400000;
System.out.println("days="+days);
if(days>=30)
{
float years=days/365;
int acno=rs.getInt("accno");
String acctype=rs.getString("acctype");
System.out.println("acctype="+acctype);
if(acctype.compareTo("Saving")==0)
{
rate=sav;
}
if(acctype.compareTo("Current")==0)
{
rate=cur;
}
if(acctype.compareTo("High Value")==0)
{
rate=high;
}
if(acctype.compareTo("Fixed Deposit")==0)
{
rate=fix;
}
System.out.println("acno ="+acno);
ResultSet rs2=stmt2.executeQuery("select * from trans where accno="+acno);
tr=0;
money2=0;
while(rs2.next())
{
tr=tr+1;
System.out.println("transactions are"+tr);
int money=rs2.getInt("money");
System.out.println("money="+money);
money2=money2+money;
}
int avgmoney=money2/tr;
System.out.println("avgmoney="+avgmoney+"years="+years+"ideposit="+ibalance+"rate="+rate);
float interest=(avgmoney*years*rate)/100;
System.out.println("interest="+interest);
stmt3.executeUpdate("update customer4 set idate=sysdate");
//stmt4.executeUpdate("upadate trans set interest="+interest);
stmt5.executeUpdate("update customer4 set ideposit="+(ibalance+interest)+"where accno="+acno);
}
System.out.println("out of while");
}
System.out.println("out of while2");
}
catch(Exception e)
{
JOptionPane.showMessageDialog(null,"ERROR IN ACCESSING ORACLE DATABASE, THE APPLICATION MAY NOT WORK","ERROR",JOptionPane.ERROR_MESSAGE);
System.out.println("hi"+e);
}
}

//--------------------------------------------------------------------------------------------

void loaninterest()
{
System.out.println("inside calinterest");
int tr=0;
float rate=6,sav=6,cur=6,high=6,fix=6;
long timet=1,timei=1,timediff=1;
int money2=1,ibalance=0;
float accno=0.0f,namount=0.0f,pamount=0.0f,newnamount=0.0f,newnamount2=0.0f,mi=0.0f,fine=0.0f,interest=0.0f;
int givflag=0;
try
{
System.out.println("inside trycalinterest");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
Statement stmt6=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from loan");
while(rs.next())
{
System.out.println("inside while loancalinterest");
java.sql.Date df=new java.sql.Date(10000);
accno=rs.getInt("accno");
givflag=rs.getInt("givenflag");
df=rs.getDate("pdate");
namount=rs.getInt("namount");
pamount=rs.getInt("pamount");
System.out.println("namount="+namount+"pamount="+pamount);
mi=(2*namount)/100;
fine=(1*namount)/100;
System.out.println("mi="+mi+"namount="+namount);
java.util.Date dd=new java.util.Date(2004,1,1);
Calendar cal=Calendar.getInstance();
//cal.set(2004,1,1);
timet=cal.getTimeInMillis();
System.out.println("df="+df);
System.out.println("dd="+dd);
timei=df.getTime();
//timet=dd.getTime();
timediff=timet-timei;
System.out.println("the diff in time="+timediff);
float days=timediff/86400000;
System.out.println("days="+days);
if(days>=30)
{
if((pamount-namount<=mi)&&givflag==1)
{
System.out.println("late installment will have to give fine givflag="+givflag);
newnamount=namount+mi;
interest=(1*newnamount)/100;
newnamount2=newnamount+interest;
System.out.println("newamount2="+newnamount2+"mi="+mi+"newnamount="+newnamount+"interest="+interest);
stmt6.executeUpdate("update loan set namount="+newnamount2+"where accno="+accno);
stmt6.executeUpdate("update loan set pamount="+newnamount2+"where accno="+accno);
stmt6.executeUpdate("update loan set pdate=sysdate where accno="+accno);
stmt6.executeUpdate("update loan set udate=sysdate where accno="+accno);
}
}
}
}
catch(Exception e)
{
System.out.println(e+"in interest class in loan interest");
}
}
}

