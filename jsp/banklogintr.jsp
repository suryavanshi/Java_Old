<html>
<head>
</body>
<p><font face="Comic Sans MS"><b>
<marquee bgcolor="#00FFFF">WELCOME TO JAVA BANK</marquee>
<body bgproperties="fixed" style="font-family: Arial Unicode MS; font-size: 1em; font-weight: bold" link="#00FFFF" bgcolor="#00FFFF" background="file:///D:/Apache%20Tomcat%204.0/webapps/ROOT/story.online.banking">


</b></font></p>
<%System.out.println("hi bye dhjdd");%>
<form name='form1' method="post"
<p>&nbsp;</p>
<p>USER ID:<input type="text" name="name" size="20"></p>
<p>PASSWORD:<input type="password" name="password" size="20"></p>
<input type="submit" value="submit" name="B1"></p>

<%
System.out.println("check1 ");
System.out.println("fhdfjdh ");
String str1=request.getParameter("name"); 
String str2=request.getParameter("password");
%>
<marquee bgcolor="#00FFFF">WELCOME TO JAVA BANK</marquee>
<%! int str3=0; %>
<%! int i=0; %>
<%! int acno=0; %>
<%! int tacno=0; %>
<%! int uspass=-1; %>
<%! int uid=-1; %>
<%! String accno; %>
<%
System.out.println("check2"+str1+str2);
try
{ 
i=0;
uspass=0;
try
{
uid=Integer.parseInt(str1);
}
catch(Exception vf)
{
uid=-1;
}
System.out.println("inside try");
java.sql.DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver()); 
java.sql.Connection conn1 = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu"); 
java.sql.Statement stmt1=conn1.createStatement(); 
java.sql.ResultSet rst1=null;
if(uid!=-1)
{
rst1=stmt1.executeQuery("select * from password10 where userid="+uid); 
}
while(rst1.next())
{
System.out.println("inside while");
try
{
uspass=Integer.parseInt(str2);
}
catch(Exception em)
{
System.out.println("error in number");
}
str3=rst1.getInt("passwd");
acno=rst1.getInt("accno");
System.out.println("yes"+str3+"uspass="+uspass);}
if(str3==uspass&&str3!=0&&uspass!=-1)
{
System.out.println("inside if");
tacno=acno;
System.out.println("got accno"+tacno);
accno=Integer.toString(tacno);
session.setAttribute("accno", accno);
session.setAttribute("name", str1);
session.setAttribute("password",str2);
i=1;
} 
}
catch(java.sql.SQLException sqle)
{
%> 
<p>Sorry1!</p>
<%
System.out.println("hi bi goog sdjsd");
}
catch(Exception e){System.out.println("maro");} 
%>
<%
if(i==1&&uspass!=-1)
{


System.out.println("inside if calling checkaccounttr.jsp");
%>
<jsp:forward page="checkaccounttr.jsp" />   
<%

}
session.removeAttribute("submit");

%>
</form>
</body>
</html>

