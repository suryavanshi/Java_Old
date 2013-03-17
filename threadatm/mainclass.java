import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;
import java.rmi.*;
import java.net.MalformedURLException;
import java.util.Locale;
import java.text.NumberFormat;
import java.io.*;
import java.io.Serializable;
import java.lang.System.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.print.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
class enter extends Thread
{
void enter1()
{
enter e=new enter();
e.start();
}
public void run()
{
atmclient atm=new atmclient();
atm.enterframe();
}
}
//--------------------------------------------------------
class mainclass
{
public static void main(String args[])
{
enter en=new enter();
en.enter1();
}
}
//------------------------------------------------------------------------------------
class atmclient implements ActionListener,Serializable,Runnable
{
private atminterface ai;
private JTextField pas;
private JTextField acno;
private JFrame f;
atmuser gb;
public void user(int accno,int pass) 
{
int verifypass=0;
System.out.println("inside user accno="+accno+pass);
try 
{
ai=(atminterface)Naming.lookup("//10.100.60.59:4711/atmserver");
} 
catch (MalformedURLException malformedException) 
{
System.err.println("Bad URL: " + malformedException);
} 
catch (NotBoundException notBoundException) 
{
System.err.println("Not Bound: " + notBoundException);
}
catch (RemoteException remoteException) 
{
System.err.println("Remote Exception: " + remoteException);
}

try 
{
atmuser ar=new atmuser();
ar.oaccno=accno;
ar.passwd=pass;
verifypass=ai.verify(ar);
if(verifypass==0)
{
JOptionPane.showMessageDialog(null,"WRONG USER ID OR PASSWORD","ERROR",JOptionPane.ERROR_MESSAGE);
f.show();
}
if(verifypass==1)
{
InputStreamReader isr = new InputStreamReader(System.in);
BufferedReader br = new BufferedReader(isr);	
int choice=0;
System.out.println("WELCOME");
while(choice!=3)
{
System.out.println("-----------------------------------------------------------------------");
System.out.println("1.GET BALANCE"+"\n"+"2.WITHDRAW"+"\n"+"3.EXIT"+"\n"+"ENTER YOUR CHOICE:");
try
{
choice=Integer.parseInt(br.readLine());
}
catch(Exception om){}
if(choice==1)
{
atmuser u=new atmuser();
atmuser u2=new atmuser();
u2.oaccno=accno;
u=ai.getbalance(u2);
System.out.println("Balance is:"+u.bal);
}
if(choice==3)
{
System.exit(0);
}
if(choice==2)
{
int monflag=0;
System.out.println("ENTER MONEY TO BE WITHDRAWN:");
int money=0;
try
{
money=Integer.parseInt(br.readLine());
}
catch(Exception rt)
{
System.out.println("money has to be integer"+rt);
monflag=1;
}
if(monflag==0)
{
atmuser au=new atmuser();
atmuser au2=new atmuser();
au2.oaccno=accno;
try
{
au=ai.withdraw(money,au2);
}
catch(Exception pl)
{
System.out.println("returned null"+pl);
}

if(au!=null)
{
au.oaccno=accno;
System.out.println("YOUR BALANCE IS:"+au.bal);
printtestframe pt=new printtestframe();
pt.printframe(au);
gb=au;
Thread t=new Thread(this);
t.start();
}
if(au==null)
{
//System.out.println("inside insufficient money");
JOptionPane.showMessageDialog(null,"ACCOUNT HAS INSUFFICIENT MONEY","ERROR",JOptionPane.ERROR_MESSAGE);
}
}
}
}
}
}
catch (RemoteException remoteException) 
{
System.out.println("rmi error"+remoteException);
}
}
public void run()
{
String msgText = "ACCOUNT NUMBER IS:"+gb.oaccno+"\nYOUR BALANCE IS:"+gb.bal+"\nTHANKS FOR USING JAVA ATM";
System.out.println();
String to =gb.email;
String from ="JAVABANK";
String host ="yoga.da-iict.org";
boolean debug =false;
//System.out.println("main ad="+gb.email);
Properties props = new Properties();
props.put("mail.smtp.host", host);
//if (debug) props.put("mail.debug", args[3]);
Session session = Session.getInstance(props, null);
session.setDebug(debug);
try {
// create a message
Message msg = new MimeMessage(session);
msg.setFrom(new InternetAddress(from));
InternetAddress[] address = {new InternetAddress(to)};
msg.setRecipients(Message.RecipientType.TO, address);
msg.setSubject("JAVA BANK ATM SERVICE");
msg.setSentDate(new java.util.Date());
// If the desired charset is known, you can use
// setText(text, charset)
msg.setText(msgText);
Transport.send(msg);
}
catch (MessagingException mex)
{
System.out.println("\n--Exception handling in msgsendsample.java");
mex.printStackTrace();
System.out.println();
Exception ex = mex;
do
{
if (ex instanceof SendFailedException)
{
SendFailedException sfex = (SendFailedException)ex;
Address[] invalid = sfex.getInvalidAddresses();
if (invalid != null)
{
System.out.println("    ** Invalid Addresses");
if (invalid != null)
{
for (int i = 0; i < invalid.length; i++) 
System.out.println("         " + invalid[i]);
}
}
Address[] validUnsent = sfex.getValidUnsentAddresses();
if (validUnsent != null)
{
System.out.println("    ** ValidUnsent Addresses");
if (validUnsent != null) 
{
for (int i = 0; i < validUnsent.length; i++) 
System.out.println("         "+validUnsent[i]);
}
}
Address[] validSent = sfex.getValidSentAddresses();

if (validSent != null) {
System.out.println("    ** ValidSent Addresses");
if (validSent != null) {
for (int i = 0; i < validSent.length; i++) 
System.out.println("         "+validSent[i]);
}
}
}
System.out.println();
if (ex instanceof MessagingException)
ex = ((MessagingException)ex).getNextException();
else
ex = null;
}
while (ex != null);
}
}

//-------------------------------------------------------------------------------------------
public void enterframe()
{
f=new JFrame();
f.setTitle("JAVA ATM USER LOGIN");
JButton button = new JButton("ENTER");
acno=new JTextField(15);
pas=new JPasswordField(15);
JLabel label = new JLabel("User Id");
JLabel label2=new JLabel("Password");
JPanel pane=new JPanel();
pane.add(label);
pane.add(acno);
pane.add(label2);
pane.add(pas);
pane.add(button);
f.setSize(260,150);
f.setResizable(false);
f.getContentPane().add(pane);
button.addActionListener(this);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.show();
f.addWindowListener(new WindowAdapter() 
      {  public void windowIconified(WindowEvent e) 
         {
	  System.out.println("inside iconified"); 
	  f.setState(Frame.NORMAL);
	  f.show(); 
         } 
      } );
}
//------------------------------------------------------------------------------------
public void actionPerformed(ActionEvent e)
{
String pass1=pas.getText();
String acno1=acno.getText();
int pass2=-1,acno2=-1;
int invalid=0;
try
{
acno2=Integer.parseInt(acno1);
pass2=Integer.parseInt(pass1);
}
catch(Exception iu)
{
JOptionPane.showMessageDialog(null,"PASSWORD AND USERID HAVE TO BE INTEGER","ERROR",JOptionPane.ERROR_MESSAGE);
invalid=1;
}
if(invalid==0)
{
f.setVisible(false);
user(acno2,pass2);
}
}
//--------------------------------------------------------------------------------------
}
//--------------------------------------------------------------------------------------
class atmuser implements Serializable
{
public String name;
public String acctype;
public int oaccno;
public int passwd;
public int bal;
public String email;
}
//-----------------------------------------------------------------------------------------
class printtestframe extends JFrame 
{  
public void printframe(atmuser df)
{  
setTitle("PrintTest");
setSize(300, 300);
addWindowListener(new WindowAdapter()
{  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
} );
Container contentPane = getContentPane();
canvas = new PrintPanel(df);
contentPane.add(canvas, "Center");
JPanel buttonPanel = new JPanel();
contentPane.add(buttonPanel, "North");
printtr(df);
}
public void printtr(atmuser rec)
{  
PrinterJob printJob = PrinterJob.getPrinterJob();
if (pageFormat == null)
pageFormat = printJob.defaultPage();
printJob.setPrintable(canvas, pageFormat);
if (printJob.printDialog())
{  try
{
printJob.print();
}
catch (PrinterException exception)
{
JOptionPane.showMessageDialog(this, exception);
}
}
}
private PrintPanel canvas;
private PageFormat pageFormat;
}
class PrintPanel extends JPanel implements Printable
{  
atmuser jk;
public PrintPanel(atmuser iu)
{
jk=new atmuser();
jk=iu;
}
public void paintComponent(Graphics g)
{  
super.paintComponent(g);
Graphics2D g2 = (Graphics2D)g;
drawPage(g2);
}
public int print(Graphics g, PageFormat pf, int page) throws PrinterException
{
if (page >= 1) return Printable.NO_SUCH_PAGE;
Graphics2D g2 = (Graphics2D)g;
g2.setPaint(Color.black);
g2.translate(pf.getImageableX(), pf.getImageableY());
g2.draw(new Rectangle2D.Double(0, 0,
pf.getImageableWidth(), pf.getImageableHeight()));
drawPage(g2);
return Printable.PAGE_EXISTS;
}
public void drawPage(Graphics2D g2)
{
FontRenderContext context = g2.getFontRenderContext();
Font f = new Font("Serif", Font.PLAIN, 72);
GeneralPath clipShape = new GeneralPath();
String name1=Integer.toString(jk.oaccno);
//System.out.println("name="+name1+jk.oaccno);
TextLayout layout = new TextLayout("ACCOUNT NUMBER:"+name1, f, context);
AffineTransform transform = AffineTransform.getTranslateInstance(0, 72);
Shape outline = layout.getOutline(transform);
clipShape.append(outline, false);
String bal=Integer.toString(jk.bal);
//System.out.println("bal="+jk.bal);
layout = new TextLayout("BALANCE IS:"+bal, f, context);
transform = AffineTransform.getTranslateInstance(0, 144);
outline = layout.getOutline(transform);
clipShape.append(outline, false);
g2.draw(clipShape);
g2.clip(clipShape);
final int NLINES =50;
Point2D p = new Point2D.Double(0, 0);
for (int i = 0; i < NLINES; i++)
{
double x = (2 * getWidth() * i) / NLINES;
double y = (2 * getHeight() * (NLINES - 1 - i))/ NLINES;
Point2D q = new Point2D.Double(x, y);
g2.draw(new Line2D.Double(p, q));
}
}
}


