package marcopolo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//将MarcoHandler映射到“/marco”上
		//withSockJS是为了使用SockJS，如果不加的话，前端将使用低层级的WebSocket（代码也要改）
//		registry.addHandler(marcoHandler(), "/marco");
		registry.addHandler(marcoHandler(), "/marco").withSockJS();
	}
  
	@Bean
	public MarcoHandler marcoHandler() {
		return new MarcoHandler();
	}

}
