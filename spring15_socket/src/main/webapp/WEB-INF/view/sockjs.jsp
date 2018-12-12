<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sockjs</title>
</head>
<body>
<script type="text/javascript" src="/spring15_socket/js/sockjs-0.3.4.min.js"></script>
<button onclick="test()">sockjs测试</button>

</body>
<script type="text/javascript">
var url= "http://localhost:9999/spring15_socket/socket.do";
var sock = new SockJS(url);
sock.open= function(){
	console.log("sock.open");
	sayHi();
}

function test(){
	sayHi();
}

sock.onmessage = function(e){
	console.log('receivedMessage'+e.data);
	setTimeout(function() {
		sayHi();
	}, 1000);
}

sock.onclose = function(){
	console.log('sock.close');
}
function sayHi(){
	console.log('sending...');
	sock.send('Hi');
}
</script>
</html>