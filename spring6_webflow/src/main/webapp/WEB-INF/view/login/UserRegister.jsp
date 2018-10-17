<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>UserRegister Page</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<body>
<h1>欢迎注册</h1>
<!-- commandName指定 表单属性绑定的实体名 -->
<form:form commandName="user">
<input type="hidden" name="_flowExecutionKey" value="${_flowExecutionKey}"/>
账号：<form:input path="userName"/>
<hr>
密码：<form:input path="userPassword"/>
<hr>
年龄：<form:input path="age"/>
<hr>
<!-- name为固定写法，表示触发doLogin事件 -->
<input type="submit" name="_eventId_doLogin" value="登录"/>
</form:form>
</body>
</html>