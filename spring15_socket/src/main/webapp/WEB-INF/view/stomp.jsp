<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>stomp测试</title>
</head>
<body>
<button onclick="test()">stomp发送(服务端返回信息走订阅2)</button>
<button onclick="test2()">stomp订阅1（@subscribeMapping，不过代理）</button>
<button onclick="test3()">stomp订阅2 ：通过代理</button>
</body>
<script type="text/javascript" src="/spring15_socket/js/sockjs-0.3.4.min.js"></script>
<script type="text/javascript" src="/spring15_socket/js/stomp.js"></script>
<script type="text/javascript">
var url = 'http://localhost:8089/spring15_socket/stompEndPoint.do';
//创建sockjs链接
var sock = new SockJS(url);
//创建stomp客户端
var stomp = Stomp.over(sock);
var msg = JSON.stringify({'name':'td','age':13});
stomp.connect({},function(frame){
	console.log('connecting...'+frame)
	stomp.send('/app/stomp1.do',{},msg);
})

function test(){
	stomp.send('/app/stomp1.do',{},msg);
}

function test2(){
	stomp.subscribe('/app/stomp2.do',function(msg){
		console.log("subscribemapping:"+JSON.parse(msg.body).content);
	})
	
}

function test3(){
	stomp.subscribe('/topic/hello',function(msg){
		console.log("topicHello:"+JSON.parse(msg.body).content);
	})
	
}

</script>
</html>