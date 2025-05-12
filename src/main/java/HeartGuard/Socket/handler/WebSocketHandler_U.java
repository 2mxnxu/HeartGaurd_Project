package HeartGuard.Socket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketHandler_U extends TextWebSocketHandler {

    private static Map<String, WebSocketSession> sessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        URI uri = session.getUri();
        String path = uri.getPath(); // 예: "/ws/user/01012345678"
        System.out.println("path = " + path);
        String[] parts = path.split("/"); // URI를 "/"로 분리
         String phone = parts[3]; // 전화번호는 네 번째 부분

        sessionMap.put(phone, session);
        System.out.println("WebSocket 연결됨: " + sessionMap);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("수신 메시지: " + payload);

        String 내용 = payload.split("/")[0];
        String 병원위치 = payload.split("/")[1];
        String 신고자번호 = payload.split("/")[2];

        for( String phone : sessionMap.keySet() ){
            if( phone.equals(신고자번호) ){
                sessionMap.get( phone ).sendMessage( new TextMessage( 내용 + " - " + 병원위치) );
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션 종료 시 등록된 번호 제거
        sessionMap.values().remove(session);
        System.out.println("WebSocket 연결 종료: " + session.getId());
    }
}
