<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>New Page 2</title>
</head>

<body>
<%String name=request.getParameter("accno");
int flag=0;
String pass=(String)session.getAttribute("password");
int uid=-1,pad=-1;
try
{
pad=Integer.parseInt(pass);
uid=Integer.parseInt(name);
}
catch(Exception tx)
{
}
System.out.println("pass="+pass+"name="+name);
try
{
java.sql.DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
java.sql.Connection conn1 = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
java.sql.Statement stmt1=conn1.createStatement(); 
java.sql.ResultSet rst1=stmt1.executeQuery("select * from customer4 where userid="+uid); 
while(rst1.next())
{
int id=rst1.getInt("userid");
if(id==uid)
{
int pas=rst1.getInt("passwd");
if(pas==pad)
{
flag=1;
}
}
}
}
catch(Exception ef)
{
}
if(name==null||pass==null)
{
response.sendRedirect("http://10.100.63.94:8080/banklogintr.jsp");
}
else if(flag==1)
{
%>
<jsp:forward page="transfer.jsp" />  
<%
}
%>
</body>

</html>
