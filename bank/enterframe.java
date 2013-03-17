/*this class contain method which does user verification. When a employee enters his username 
and password then the operation which he can invoke are displayed and when a customer enters 
his password then his option are displayed.The methods are enterframe2()-it accepts userid and 
passworderror()-this function is called to display some error, it accepts error message as 
string type*/

package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class enterframe extends myframe implements ActionListener
{
public void enterframe2()
{
setTitle("JAVA BANK USER LOGIN");
ImageIcon img2 = new ImageIcon("x:\\images\\enter.gif");
JButton button = new JButton("ENTER");
button.setBackground(Color.pink);
jtf=new JTextField(15);
jtf21=new JPasswordField(15);
JLabel label = new JLabel("User Id");
JLabel label2=new JLabel("Password");
JPanel pane=new JPanel();
pane.add(label);
pane.add(jtf);
pane.add(label2);
pane.add(jtf21);
pane.add(button);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
setSize(screenWidth / 2, screenHeight / 2);
setLocation(screenWidth / 3, screenHeight / 4);
//ImageIcon img2 = new ImageIcon("x:\\images\\enter.gif");
Image img = tk.getImage("manu.jpg");
//setIconImage(img);
setSize(260,150);
setResizable(false);
//setIconImage("flowers.jpg");
getContentPane().add(pane);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//jtf.addActionListener(this);
button.addActionListener(this);
addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  setState(Frame.NORMAL);
	  show(); 
         } 
      } );
show();
if(getState()==Frame.ICONIFIED)
{
setState(Frame.NORMAL);
show();
}
if(isShowing()==false)
{
System.out.println("inside isshowin");
show();
}
}


void error(String mes)
{
JFrame f6=new JFrame();
JOptionPane.showMessageDialog(f6,mes,"Error Message",JOptionPane.ERROR_MESSAGE);
/*(JPanel pane6=new JPanel();
JLabel labele=new JLabel("WRONG USERNAME OR PASSWORD!!!");
pane6.add(labele);
Border border1 = BorderFactory.createMatteBorder(5, 5,
            5, 5, Color.pink);
pane.setBorder(border1);*/
f6.setSize(250,50);
f6.setResizable(false);
//f6.getContentPane().add(pane6);
setVisible(false);
}

public void actionPerformed(ActionEvent e)
{
int y=-1,uidget=-1;
String g=jtf.getText();
String i=jtf21.getText();
try
{
y=Integer.parseInt(i);
uidget=Integer.parseInt(g);
}
catch(Exception er){}
if(y==-1||uidget==-1)
{
JOptionPane.showMessageDialog(null,"ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
}
jtf.setText(g);
jtf21.setText(i);
System.out.println(g+"\n"+i);
try
{
System.out.println("inside data");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery("select * from password10");
int f3=0;
while(rs.next())
{
System.out.println("inside while next");
String str=rs.getString("username");
//String pass=rs.getString("passwd");
type=rs.getString("usertype");
int uid=rs.getInt("userid");
int z=rs.getInt("passwd");
int accno=rs.getInt("accno");
System.out.println(str+"--------"+z+"-----"+type+"---"+uid+"==="+uidget);
if(uidget==uid && y==z && type.compareTo("employee")==0)
{
f3=1;
setVisible(false);
System.out.println("found");
employee emf=new employee();
emf.guimain(str,uid);
}
if(uidget==uid && z==y && type.compareTo("user")==0)
{
System.out.println("inside guiuser");
f3=1;
setVisible(false);
userframe uf=new userframe();
uf.guiuser(accno,str,uid);
}
}
if(f3==0)
{
error("INVALID USER NAME OR PASSWORD");
}
}
catch(Exception e1)
{
System.out.println(e1);
}
}
public JTextField jtf;
public JTextField jtf21;
String type;
}