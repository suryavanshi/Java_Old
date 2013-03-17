/*this class defines the general gui frame which is used by all other classes.*/
package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;

class myframe extends JFrame
{
public myframe()
{
try
{
myframe.setDefaultLookAndFeelDecorated(true);
setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
}
catch(Exception look)
{
System.out.println("look"+look);
}
setTitle("JAVA BANK");
Toolkit tk = Toolkit.getDefaultToolkit();
Image img = tk.getImage("x:\\images\\bank3.jpg");

setIconImage(img);
//setLocation(200,300);
/*addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  setState(Frame.NORMAL);
	  show(); 
         } 
      } );*/
//setSize(600,500);
//show();
//JFrame entf=new enterframe();
}
}