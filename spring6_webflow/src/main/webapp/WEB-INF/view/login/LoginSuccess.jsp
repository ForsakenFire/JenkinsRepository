<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LoginSuccess Page</title>
</head>
<body>
<h1>登录成功页面</h1>
<!-- 页面上使用了变量 flowExecutionUrl ，表示 flow 执行到当前状态时的 URL 。
flowExecutionUrl 的值已经由 Spring Web Flow 2.0 框架的代码进行赋值，
 并放入相应的 model中供 view 访问。
flowExecutionUrl 的值包含 flow 在执行过程中会为每一状态生成的唯一的 key ，因此不可用其他手段来获取。
请求参数中 _eventId 的值与shoppting.xml中 transition 元素的 on 属性的值是对应的，在接收到_eventId参数后，相应transition会被执行
也就是直接请求当前view-state，触发对应的事件进行跳转
 -->
<a href="${flowExecutionUrl}&_eventId=goodBye">注销</a>
<a href="${flowExecutionUrl}&_eventId=cancle">取消流程</a>
</body>
</html>