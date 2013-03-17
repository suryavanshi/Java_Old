/*-this class contains methods which allow the employee to check the cash transaction history of  
a customer, it extends myframe and implements ActionListener. The methods are transframe() 
which displays the frame in which account number is given by the employee, transe()-the 
arguments passed to it are account number of the customer, after verifying the account number 
the method retrieves transaction history from trans table in the database and displays it in a 
JTable.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class employeetranshistory extends myframe implements ActionListener
{
void transframe() throws SQLException
{
try
{
setTitle("JAVA BANK TRANSACTION HISTORY");
JPanel pane3=new JPanel();
JLabel label3=new JLabel("Enter Account Number");
jtf3=new JTextField(10);
button5=new JButton("Enter");
//button9=new JButton("Back");
pane3.add(label3);
pane3.add(jtf3);
pane3.add(button5);
//pane3.add(button9);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
setLocation(screenWidth / 4, screenHeight / 4);
Image img = tk.getImage("icon.gif");
setSize(350,200);
setResizable(false);
//f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
getContentPane().add(pane3);
addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  setState(Frame.NORMAL);
	  show(); 
         } 
      } );
show();
button5.addActionListener(this);
//button9.addActionListener(this);
}
catch(Exception e)
{
System.out.println(e);
}
}
public void actionPerformed(ActionEvent a)
{
String h=jtf3.getText();
int h1=-1;
try
{
h1=Integer.parseInt(h);
}
catch(NumberFormatException er){}
if(h1==-1)
{
enterframe ef1=new enterframe();
ef1.error("ACCOUNT NUMBER HAS TO BE INTEGER");
}
try
{
setVisible(false);
if(h1!=-1)transe(h1);
}
catch(Exception e)
{
}
}
void transe(int ac2)  //this gives transaction history to employee 
{
int i=0;
myframe f9=new myframe();
JPanel panet1=new JPanel();
final String[] colHeads={"Toaccno","Fromaccno","Transaction","Chequeno","Draftno","Money Transacted","Date"};
Object[][] data=new Object[100][7];
int flag2=0;
try
{
System.out.println("inside transe");
int naccno=ac2;
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from trans5");
data=new Object[100][7];

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
JOptionPane.showMessageDialog(null,"ACCOUNT NUMBER NOT FOUND","ERROR",JOptionPane.ERROR_MESSAGE);
}
}
public JButton button5;
public JTextField jtf3;
String type;
}