


<%! String name; %>
<%
int bal=0;
String acn=request.getQueryString();
String acno=request.getParameter("accno");
String pass=request.getParameter("password");
//System.out.println("acn="+acn);
System.out.println("acno="+acno+"passwd="+pass);
int taccno=37;
int pass1=88;
int dpass=0,passflag=0;
try
{
taccno=Integer.parseInt(acno);
pass1=Integer.parseInt(pass);
System.out.println("taccno="+taccno);
}
catch(Exception num)
{
out.println("invalid accno");
}
try
{
java.sql.DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
java.sql.Connection conn1 = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@daiict_oracle_9:1521:btech", "200201189", "manu");
java.sql.Statement stmt1=conn1.createStatement(); 
java.sql.ResultSet rs=stmt1.executeQuery("select * from customer4 where accno="+taccno); 
while(rs.next())
{
dpass=rs.getInt("passwd");
if(dpass==pass1)
{
bal=rs.getInt("ideposit");
passflag=1;
}
}
}
catch(Exception ft)
{
System.out.println("sqlexception="+ft);
}
String balance=Integer.toString(bal);
java.util.Date today = new java.util.Date();
if(passflag==1)out.println("Your Balance is:"+balance);
else out.println("INVALID PASSWORD");
out.println("Date&time: "+today);
%>

