package marcopolo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker // 表明这个配置类不仅配置了WebSocket，还配置了基于代理的STOMP消息
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/marcopolo").withSockJS();
	}

	/*
	 * 以“/app”为目的地的消息将会直接路由到带有@MessageMapping注解的控制器方法中。
	 * 而发送到代理上的消息，其中也包括@MessageMapping注解方法的返回值所形成的消息， 将会路由到代理上，
	 * 并最终发送到订阅这些目的地的客户端
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
////		启用了STOMP代理中继，如RabbitMQ或ActiveMQ
//		registry.enableStompBrokerRelay("/queue", "/topic")
////			//默认值
////			.setRelayHost("localhost")
////			.setRelayPort(61613)
////			.setClientLogin("marcopolo")
////			.setClientPasscode("guest")
//			;
		registry.enableSimpleBroker("/queue", "/topic");// 基于内存
		registry.setApplicationDestinationPrefixes("/app");
	}

}
