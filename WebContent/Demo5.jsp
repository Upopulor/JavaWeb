<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		pageContext.setAttribute("ss", "sss");
		
		request.setAttribute("ss","request");
		
		session.setAttribute("ss", "session");
		
		application.setAttribute("ss", "application");
		//request.getRequestDispatcher("Demo4.jsp").forward(request, response);
		//response.sendRedirect(request.getContextPath()+"/Demo4.jsp"); //如果使用了重定向，能取到
	%>
	${ss}
	${requestScope.ss}
	${sessionScope.ss}
</body>
</html>