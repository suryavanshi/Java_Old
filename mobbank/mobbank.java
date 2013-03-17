import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.io.*;
import java.io.*;

public class mobbank extends MIDlet implements CommandListener {
Display display = null;
Ticker t = null;
TextField name = null;
TextField pass=null;
Form form;
String url = "http://127.0.0.1:8080/today.jsp";
static final Command callCommand = new Command("date?", Command.OK, 2);
static final Command clearCommand = new Command("clear", Command.STOP, 2);
String myname;
String mypass;
public mobbank() 
{
t = new Ticker("WELCOME TO JAVA BANK MOBILE BANKING SERVICE. World's Most Reliable"  +
 		" BANKING SERVICE");
System.out.println("inside invokejspmidlet");
display = Display.getDisplay(this);
name = new TextField("ACCNO:", "", 25, TextField.ANY);
pass=new TextField("PASSWD:","",25, TextField.ANY);
form = new Form("Invoke JSP");
form.setTicker(t);
}

public void startApp() throws MIDletStateChangeException 
{
System.out.println("inside startapp");
form.append(name);
form.append(pass);
form.addCommand(clearCommand);
form.addCommand(callCommand);
form.setCommandListener(this);
display.setCurrent(form);
}

public void pauseApp() 
{
}

public void destroyApp(boolean unconditional) 
{
notifyDestroyed();
}
void invokeJSP(String url,String myname,String mypass) throws IOException 
{
System.out.println("inside invokejsp url="+url);
HttpConnection c = null;
InputStream is = null;
OutputStream os = null;
StringBuffer b = new StringBuffer();
TextBox t = null;
try
{
System.out.println("myname is="+myname);
//myname.trim();
//mypass.trim();
String url2="http://10.100.63.94:8080/today.jsp?accno="+myname+"&password="+mypass;
c = (HttpConnection)Connector.open(url2);
c.setRequestMethod(HttpConnection.GET);
/*c.setRequestProperty("IF-Modified-Since", "25 Nov 2001 15:17:19 GMT");
c.setRequestProperty("User-Agent","Profile/MIDP-1.0 Configuration/CLDC-1.0");
c.setRequestProperty("Content-Language", "en-CA");
c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");*/
System.out.println("url"+c.getURL());
os = c.openOutputStream();
os.write(("name="+myname).getBytes());
os.flush();
is = c.openDataInputStream();
int ch;
while ((ch = is.read()) != -1) 
{
b.append((char) ch);
System.out.print((char)ch);
}
t = new TextBox("Date", b.toString(), 1024, 0);
t.setCommandListener(this);
}
finally 
{
if(is!= null) 
{
is.close();
}
if(os != null) 
{
os.close();
}
if(c != null) 
{
c.close();
}
}
display.setCurrent(t);
} 
public void commandAction(Command c, Displayable d) 
{
System.out.println("inside commandAction");
String label = c.getLabel();
System.out.println("label="+label);
if(label.equals("clear")) 
{
destroyApp(true);
} 
else if (label.equals("date?")) 
{
myname = name.getString();
mypass=pass.getString();
System.out.println("myname after name.getString="+myname+"mypass="+mypass);
try 
{
invokeJSP(url,myname,mypass);
}
catch(IOException e) 
{
}
} 
}
}

