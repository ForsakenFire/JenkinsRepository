package spring15_socket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * sockjs失效
 * @author TD
 *
 */
@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer{

	/**
	 * websocket处理器的注册中心
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//注册handler启用sockjs
		//sockjs可以自动选择备选方案使用websocket
		registry.addHandler(handler(), "socket.do").withSockJS();
	}
	@Bean
	public WebSocketHandler handler() {
		return new spring15_socket.handler.WebSocketHandler();
	}
	
	
}
