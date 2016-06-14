package ru.darkvader.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import ru.darkvader.interceptor.HttpSessionIdHandshakeInterceptor;

/**
 * Created by Khairullin on 12/03/16.
 */
@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = "ru.darkvader.web")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/room_ws").withSockJS().setInterceptors(new HttpSessionIdHandshakeInterceptor());
    }

}