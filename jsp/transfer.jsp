<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>ENTER TO ACCOUNT NUMBER</title>
<style fprolloverstyle>A:hover {color: red; font-weight: bold}
</style>
</head>
<%System.out.println("inside transfer.jsp");%>
<body background="story.online.banking">
<form name='form4' method="post"
<p><font face="Arial Unicode MS" size="3"><b>ENTER TO ACCOUNT NUMBER</b></font>:<input type="text" name="staccno" size="20"></p>
<p><font face="Arial Unicode MS" size="3"><b>ENTER MONEY TO BE TRANSFERRED</b></font>:<input type="text" name="money" size="20"></p>
<p><input type="submit" value="Submit" name="submit2"></p>
<%! int accno=-1;%>
<%! int money=-1;%>
<%! int fromac=-1;%>
<%! int frommoney=0;%>
<%! int frommoneyfound=0;%>
<%! int toacno=0;%>
<%! int toaccnofound=0;%>
<%! int newideposit=0;%>
<%! int oldtomoney=0;%>
<%! int newtoaccmoney=0;%>
<%
accno=-1;
money=-1;
frommoneyfound=0;
System.out.println("babaka");
System.out.println("jdgjdh");
String check=(String)session.getAttribute("name");
System.out.println("check="+check);
String accno1=request.getParameter("staccno");
String money1=request.getParameter("money");
String fromac1=request.getParameter("accno");
System.out.println("toaccno="+accno1+money1+"fromaccnosds="+fromac1);
%>
<p><b>WELCOME <%=fromac1%> </b></p>
<%
try
{
accno=Integer.parseInt(accno1);
money=Integer.parseInt(money1);
fromac=Integer.parseInt(fromac1);
}
catch(Exception jn)
{
System.out.println("not proper number");
%>
<p><b>FILL NUMERIC FIELDS PROPERLY</b></p>
<%
}
if(accno!=-1 && money!=-1)
{
try
{
System.out.println("inside try of deposit");
java.sql.DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
java.sql.Connection conn1 = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
java.sql.Statement stmt1=conn1.createStatement(); 
java.sql.Statement stmt2=conn1.createStatement(); 
java.sql.Statement stmt3=conn1.createStatement(); 
java.sql.Statement stmt4=conn1.createStatement(); 
java.sql.ResultSet rst1=stmt1.executeQuery("select * from customer4");
while(rst1.next())
{
toacno=rst1.getInt("accno");
if(toacno==accno)
{
oldtomoney=rst1.getInt("ideposit");
newtoaccmoney=oldtomoney+money;
toaccnofound=1;
System.out.println("toacfound="+toacno);
}
if(toacno==fromac)
{
frommoney=rst1.getInt("ideposit");
if(frommoney>=(money+500))
{
frommoneyfound=1;
System.out.println("frommonryfound="+frommoneyfound);
}
}
if(toaccnofound==0)
{%>
<p><b>INVALID TO ACCOUNT NUMBER</b></p>
<%
}
if(frommoneyfound==0)
{
%>
<p><b>INSUFFICIENT MONEY IN ACCOUNT</b></p>
<%
}
if(toaccnofound==1 && frommoneyfound==1)
{
newideposit=frommoney-money;
System.out.println("new from depo"+newideposit);
System.out.println("newtoacc"+newtoaccmoney);
stmt2.executeUpdate("update customer4 set ideposit="+newideposit+"where accno="+fromac);
stmt3.executeUpdate("update customer4 set ideposit="+newtoaccmoney+"where accno="+accno);
stmt4.executeUpdate("insert into trans5(toaccno,fromaccno,moneydep,transtype,dated) values('"+accno+"','"+fromac+"','"+money+"','online transaction',sysdate)"); 
%>
<p><b>MONEY TRANSFERRED SUCESSFULLY</b></p>
<%
}
}
}
catch(Exception dm)
{
System.out.println("sql error in transfer");
}
} 

%>
</from>
</body>

</html>
