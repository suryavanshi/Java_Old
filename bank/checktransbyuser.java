/*this class contains methods which allow the customer to check his  cash transaction history.
 The methods are  trans()-the arguments passed to it are account number of the customer,
 after verifying the account number the method retrieves transaction history from trans 
table in the database and displays it in a JTable.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class checktransbyuser
{
void trans(int ac2)
{
int i=0;
myframe f9=new myframe();
JPanel panet1=new JPanel();
final String[] colHeads={"Toaccno","Fromaccno","Transaction","Chequeno","Draftno","Money Transacted","Date"};
Object[][] data=new Object[100][7];
try
{
System.out.println("inside trans");
int naccno=ac2;
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from trans5");
data=new Object[100][7];
int f2=0;
while(rs.next())
{
System.out.println("inside while");
int nac=rs.getInt("toaccno");
int nac2=rs.getInt("fromaccno");
if(nac==naccno||nac2==naccno)
{
System.out.println("inside if");
int toac=rs.getInt("toaccno");
int fromac=rs.getInt("fromaccno");
int nbalance=rs.getInt("moneydep");
int cheno=rs.getInt("chequeno");
int drano=rs.getInt("draftno");
String trtype=rs.getString("transtype");
java.util.Date dated=rs.getDate("dated"); 
String nbalance1=Integer.toString(nbalance);
String toac1=Integer.toString(toac);
String fromac1=Integer.toString(fromac);
String cheno1=Integer.toString(cheno);
String drano1=Integer.toString(drano);
data[i][0]=toac1;
data[i][1]=fromac1;
data[i][2]=trtype;
data[i][3]=nbalance1;
data[i][4]=cheno1;
data[i][5]=drano1;
data[i][6]=dated;
i=i+1;
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
f9.show();			
}
}
