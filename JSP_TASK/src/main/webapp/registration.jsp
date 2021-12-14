<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Details</title>
</head>
<body>

	Hi <% out.print(request.getParameter("name")); %>,<br>
	
	<%
		Calendar c = Calendar.getInstance();
		int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
	
		if(timeOfDay >= 0 && timeOfDay < 12) {
			out.print("Good Morning");
		}
		else if(timeOfDay >= 12 && timeOfDay < 16) {
			out.print("Good Afternoon");
		}
		else if(timeOfDay >= 16 && timeOfDay < 21) {
			out.print("Good Evening");
		}
		else if(timeOfDay >= 21 && timeOfDay < 24) {
			out.print("Good Night");
		}
	%>
	
	<%
		String gender = request.getParameter("gender");
		if(gender.equals("male"))
			out.print("Sir");
		else
			out.print("Madam");
	%>
</body>
</html>