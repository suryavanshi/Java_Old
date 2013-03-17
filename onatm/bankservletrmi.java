import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
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
public class bankservletrmi extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
{
String[] pvalue={"0","0"};
int i=0;
pvalue[0]=request.getParameter("userid");
pvalue[1]=request.getParameter("password");
System.out.println("ssyhjdfagjk");
int acn=-1,pas=-1,numflag=0;
try
{
System.out.println("inside try");
acn=Integer.parseInt(pvalue[0]);
pas=Integer.parseInt(pvalue[1]);
}
catch(Exception nu)
{
System.out.println("number exception"+nu);
numflag=1;
}
if(numflag==0)
{
userthread ut=new userthread();
ut.run1(acn,pas);
}
}
//-------------------------------------------------------------------------------------
class userthread extends Thread implements Serializable
{
private int accno;
private int pass;
private atminterface ai;
/*public userthread(int acno,int pas)
{
this.accno=acno;
this.pass=pas;
}*/
public void run1(int acn,int pas)
{
int verifypass=0;
System.out.println("inside user accno="+acn+pas);
try 
{
ai=(atminterface)Naming.lookup("rmi://10.100.63.94:4711/atmserver");
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
ar.oaccno=acn;
ar.passwd=pas;
verifypass=ai.verify(ar);
if(verifypass==0)
{
JOptionPane.showMessageDialog(null,"WRONG USER ID OR PASSWORD","ERROR",JOptionPane.ERROR_MESSAGE);
}
if(verifypass==1)
{
InputStreamReader isr = new InputStreamReader(System.in);
BufferedReader br = new BufferedReader(isr);	
int choice=0;
System.out.println("WELCOME");
while(choice!=3)
{
System.out.println("1.GET BALANCE"+"\n"+"2.WITHDRAW"+"\n"+"3.EXIT"+"\n");
try
{
choice=Integer.parseInt(br.readLine());
}
catch(Exception om){}
if(choice==1)
{
atmuser u=new atmuser();
atmuser u2=new atmuser();
u2.oaccno=acn;
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
au2.oaccno=acn;
au=ai.withdraw(money,au2);
if(au!=null)System.out.println("YOUR BALANCE IS:"+au.bal);
if(au==null)
{
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
}
}
//------------------------------------------------------------------
class atmuser implements Serializable
{
public String name;
public String acctype;
public int oaccno;
public int passwd;
public int bal;
}