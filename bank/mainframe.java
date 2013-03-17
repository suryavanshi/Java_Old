/*this class defines the mainframe which is displayed when the program is run.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
class mainframe extends myframe implements ActionListener
{
public JButton button;
public JButton button2;
public void mainframe2()
{
System.out.println("inside mainframe");
//setBackground(Color.white);
JPanel pane7=new JPanel();
//pane7.setBackground(Color.white);
//JPanel pane8=new JPanel();
//ImageIcon img1=new ImageIcon("bank.jpg");
//JLabel labels=new JLabel(img1);
ImageIcon img2 = new ImageIcon("x:\\images\\click.gif");
button=new JButton("ENTER");
button2=new JButton("ENTER OWNER");
pane7.setLayout(new BorderLayout());
Box bc=Box.createVerticalBox();
bc.add(button);
pane7.add(bc,"Center");
Box bb = Box.createVerticalBox();
bb.add(button2);
pane7.add(bb,"East");
Box b = Box.createVerticalBox();
b.add(Box.createGlue());
//b.add(new JLabel("JAVA BANK"));
b.add(new JLabel("WELCOME TO JAVA BANK"));
b.add(Box.createGlue());
pane7.add(b, "North");
ImageIcon img3 = new ImageIcon("x:\\images\\customer_care.gif");
ImageIcon img4 = new ImageIcon("x:\\images\\secure.gif");
Box b1 = Box.createVerticalBox();  
b1.add(Box.createGlue());
b1.add(new JLabel("JAVA BANK ATM"));
b1.add(new JLabel("JAVA BANK ONLINE SYSTEM"));
//b1.add(new JButton(img3));
//b1.add(new JButton(img4));
b1.add(Box.createGlue());
pane7.add(b1, "West");              
button.addActionListener(this);
button2.addActionListener(this);
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension d = tk.getScreenSize();
int screenHeight = d.height;
int screenWidth = d.width;
Image img = tk.getImage("manu.jpg");
Border etched = BorderFactory.createEtchedBorder();
Border titled = BorderFactory.createTitledBorder(etched, "JAVA BANK");
//Border border = BorderFactory.createMatteBorder(3,3,3,3, Color.orange);
pane7.setBorder(titled);
//Border border1 = BorderFactory.createMatteBorder(3,3,3,3, Color.pink);
//pane7.setBorder(border);
//pane8.setBorder(border1);
getContentPane().add(pane7,"Center");
//getContentPane().add(pane8,"South");
setSize(800,500);
//f11.setResizable(false);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
public void actionPerformed(ActionEvent e)
{
int y=1;
if(e.getSource()==button)
{
enterframe ef=new enterframe();
ef.enterframe2();
//repaint();
}
if(e.getSource()==button2)
{
owner ow=new owner();
ow.ownerenter();
}
}
}
