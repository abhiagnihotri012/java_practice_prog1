<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EMI Calculate</title>
</head>
<body>
	<h2>Hi</h2>
	<% 
		int p = Integer.parseInt(request.getParameter("amount")); 
		int n = Integer.parseInt(request.getParameter("period"));		
		double r = Double.parseDouble(request.getParameter("interest")); 
		n = n * 12 ;
		r = r / ( 12 * 100 );
	%>
	
	<%!
		double findEMI(int p, double r, int n){
			double emi =  (p * r *Math.pow(1 + r, n))/ (Math.pow(1 + r, n) - 1);
			return emi;
		}
	%>
	
	<h3><%= "Your Monthly EMI will be : "+findEMI(p,r,n) %></h3> <br>
</body>
</html>