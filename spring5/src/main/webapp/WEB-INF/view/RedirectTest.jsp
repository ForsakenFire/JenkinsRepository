<%@page import="spring5.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重定向带参数测试页面</title>
</head>
<body>
<%
User user = (User)request.getAttribute("user");
%>
重定向带参数测试页面<hr>
(1)放入model参数：<%=request.getAttribute("test") %><hr>
(2)使用flash参数：user.name:<%=user.getName()%><br>
  user.code:<%=user.getCode()%>
</body>
</html>