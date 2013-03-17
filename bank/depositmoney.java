/*this class contains method which processes the cash deposition of money. The methods are dep()-
the arguments passes to it are account number and money to be deposited, it verifies the 
account number and then updates the account.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class depositmoney
{
public void dep(int acn2,int mn,int empid) throws SQLException
{
try
{
int taccno=acn2;
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
Statement stmt2=conn.createStatement();
Statement stmt3=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from customer4");
int f3=0;
while(rs.next())
{
int tac=rs.getInt("accno");
int accno=tac;
String name=rs.getString("name");
String acctype=rs.getString("acctype");	
if(tac==taccno)
{
f3=1;
int dep=mn;
int bal=rs.getInt("ideposit");
int nbal=bal+dep;
stmt.executeUpdate("update customer4 set ideposit=" + nbal + "where accno="+taccno);
Calendar cal=Calendar.getInstance();
int c1=cal.get(Calendar.DATE);
int c2=cal.get(Calendar.MONTH);
int c3=cal.get(Calendar.YEAR);
String st1=Integer.toString(c1);
String st2=Integer.toString(c2);
String st3=Integer.toString(c3);
String st4=st1+"-"+st2+"-"+st3;
System.out.println(st4);
stmt.executeUpdate("insert into trans(name,accno,acctype,trtype,money,dated)"+"values('" + name +"','" + accno + "','" + acctype + "','deposit','"+ nbal+ "','" + st4 + "')");
stmt2.executeUpdate("insert into trans5(toaccno,moneydep,moneyaf,transtype,dated)"+"values('" + accno +"','" + dep + "','" + nbal + "','depositcash',sysdate)");
stmt3.executeUpdate("insert into employeelog(toaccno,money,transtype,dated,empid) values('" + accno +"','" + dep + "','depositcash',sysdate,'"+empid+"')");
}
}
if(f3==0)
{
System.out.println("Account number not found!!");
enterframe g1=new enterframe();
g1.error("INVALID ACCOUNT NUMBER");
}
else
{
System.out.println("Account updated!!");
update("MONEY DEPOSITED");
}
}
catch(SQLException e)
{
System.out.println(e);
}

}
void update(String mes)
{
JFrame f6=new JFrame();
JOptionPane.showMessageDialog(f6,mes,"INFORMATION MESSAGE",JOptionPane.INFORMATION_MESSAGE);
/*(JPanel pane6=new JPanel();
JLabel labele=new JLabel("WRONG USERNAME OR PASSWORD!!!");
pane6.add(labele);
Border border1 = BorderFactory.createMatteBorder(5, 5,
            5, 5, Color.pink);
pane.setBorder(border1);*/
f6.setSize(250,50);
f6.setResizable(false);
//f6.getContentPane().add(pane6);
f6.setVisible(false);
}

}