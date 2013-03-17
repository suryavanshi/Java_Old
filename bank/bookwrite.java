package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class bookwrite implements ActionListener
{
public myframe f;
public JTextField jtf;
public JButton b;
void writebookfr()
{
f=new myframe();
JPanel p=new JPanel();
f.getContentPane().add(p);
JLabel l=new JLabel("ENTER ACCOUNT NUMBER");
jtf=new JTextField(10);
b=new JButton("ENTER");
p.add(l);
p.add(jtf);
p.add(b);
b.addActionListener(this);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
f.setLocation(screenWidth / 4, screenHeight / 4);
f.setSize(200,200);
f.show();
}
public void actionPerformed(ActionEvent e)
{
String accno1=jtf.getText();
int accno2=-1;
try
{
accno2=Integer.parseInt(accno1);
}
catch(Exception ed){System.out.println(ed);}
if(accno2==-1)
{
JOptionPane.showMessageDialog(null,"ACCOUNT NUMBER HAS TO BE INTEGER","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(accno2!=-1)
{
writebook(accno2);
}
}
void writebook(int accno)
{
try
{
System.out.println("inside data");
DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
Statement stmt=conn.createStatement();
}
catch(Exception ef){System.out.println(ef);}
}
}
