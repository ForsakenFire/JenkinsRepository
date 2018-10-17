<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>UserWelcome Page</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<body>
<h1>欢迎第一次光临</h1>
<form:form>
<input type="hidden" name="_flowExecutionKey" value="${_flowExecutionKey}"/>
账号：<input name="userName"/>
<hr>
密码：<input name="userPassword"/>
<hr>
年龄：<input name="age"/>
<hr>
<!-- name为固定写法，表示触发doLogin事件 -->
<input type="submit" name="_eventId_doLogin" value="登录"/>
</form:form>
</body>
</html>