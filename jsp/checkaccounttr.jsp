<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>ENTER ACCOUNT NUMBER</title>
</head>
<body>

<form name='form2' method="post"
<p><font face="HelmetCondensed" size="4"><b>
<marquee bgcolor="#00FFFF">WELCOME TO JAVA BANK ACCOUNT CHECKING</marquee>

</b></font></p>
<p><b>YOUR BALANCE IS:</b></p>

<%! int accno=0;%>
<%! int uspass=0;%>
<%! int uid=-1; %>
<%
String name=(String)session.getAttribute("name");
String pass=(String)session.getAttribute("password");
session.setAttribute("name",name);
session.setAttribute("password",pass);
%>
<p>WELCOME-<%=name%></p>

<%
try
{
uspass=Integer.parseInt(pass);
uid=Integer.parseInt(name);
}
catch(Exception bh)
{
System.out.println("bahut maro");
}
System.out.println("yahoo"+name+"accno="+pass);
try
{
java.sql.DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
java.sql.Connection conn1 = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
java.sql.Statement stmt1=conn1.createStatement(); 
java.sql.ResultSet rst1=stmt1.executeQuery("select * from customer4"); 
while(rst1.next())
{
int getid=rst1.getInt("userid");
int passd=rst1.getInt("passwd");
if(getid==uid && uspass==passd)
{
int depo=rst1.getInt("ideposit");
System.out.println("deposit="+depo);
System.out.println(name);
%>
<p><%=depo%></p>
<p><a href="http://10.100.63.94:8080/temp.jsp?accno=<%=name%>">transfer money</a></p>
<%
}
}
}
catch(Exception ex)
{

System.out.println("error");%>
<p>sorryrr</p>
<%

}

%>

</form>

</body>

</html>
