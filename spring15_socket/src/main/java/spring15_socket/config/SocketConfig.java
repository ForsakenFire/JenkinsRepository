package spring15_socket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 若sockjs失效，请求变成/info错误：
 * 注意：springmvc的拦截路径需要额外设置"/"
 * @author TD
 * sockjs及普通websocket的配置
 *
 */
//@Configuration
//@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer{

	/**
	 * websocket处理器的注册中心
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//注册handler启用sockjs
		//sockjs可以自动选择备选方案使用websocket
		registry.addHandler(handler(), "/socket.do").withSockJS();
	}
	@Bean
	public WebSocketHandler handler() {
		return new spring15_socket.handler.WebSocketHandler();
	}
	
	
}
