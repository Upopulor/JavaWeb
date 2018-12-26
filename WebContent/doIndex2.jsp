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
 		//处理表单数据
 		String userName = request.getParameter("userName");
 		String pwd = request.getParameter("pwd");
 		//处理业务逻辑
 		if("tom".equals(userName)&&"123".equals(pwd)){
 			request.getRequestDispatcher("/success.jsp").forward(request, response);
 			
 		}else{
 			response.sendRedirect("/Servlet1/index2.jsp");
 		}
 		
 		//分发转向
 	
 	%>

</body>
</html>