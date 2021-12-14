<%@ page  language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="MyErrorPage.jsp"   import="task.Account"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Withdrawn</title>
</head>
<body>

	<%! Account a=new Account(); %>
	<h2><% 
	   
	 	int amt=Integer.parseInt(request.getParameter("amount"));
		
		a.withdraw(amt);
		out.print("Amount Withdrawn Successfully!!!<br><br>");
		out.print("Current Balance : "+a.showBalance());
		
	 %></h2>	 

</body>
</html>