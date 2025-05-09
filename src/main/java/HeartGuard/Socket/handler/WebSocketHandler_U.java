package HeartGuard.Socket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketHandler_U extends TextWebSocketHandler {

    private static Map<String, WebSocketSession> sessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = session.getId(); // 세션 ID를 키로 사용
        sessionMap.put(sessionId, session);
        System.out.println("사용자 " + sessionId + "에 소켓 연결");
        System.out.println(sessionMap);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        sessionMap.remove(sessionId); // 세션 ID로 제거
        System.out.println("사용자 " + sessionId + "의 소켓 연결 종료.");
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(sessionMap);
        for (WebSocketSession targetSession : sessionMap.values()) {
            targetSession.sendMessage(message);
        }
    }
}
