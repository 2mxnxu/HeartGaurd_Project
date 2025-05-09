//package HeartGuard.Socket.config;
//
//import HeartGuard.Socket.handler.WebSocketHandler_U;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig_U implements WebSocketConfigurer {
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler( new WebSocketHandler_U(), "/ws/user").setAllowedOrigins("*"); // 모든 도메인 허용
//    }
//}
