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
        // 연결 초기화 시 클라이언트에서 핸드폰 번호를 메시지로 전송해야 함
        System.out.println("WebSocket 연결됨: " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("수신 메시지: " + payload);

        // 핸드폰 번호 등록
        if (payload.startsWith("register:")) {
            String phoneNumber = payload.split(":")[1];
            sessionMap.put(phoneNumber, session);
            System.out.println("등록된 핸드폰 번호: " + phoneNumber);
            return;
        }

        // 특정 번호로 메시지 보내기
        String[] parts = payload.split(":", 2);
        if (parts.length == 2 && parts[0].equals("sendTo")) {
            String targetPhone = parts[1];
            WebSocketSession targetSession = sessionMap.get(targetPhone);

            if (targetSession != null) {
                targetSession.sendMessage(new TextMessage("📩 특정 번호로 메시지 전송 완료"));
                System.out.println("메시지 전송: " + targetPhone);
            } else {
                System.out.println("해당 번호에 대한 세션이 존재하지 않음: " + targetPhone);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.values().remove(session); // 세션 제거
        System.out.println("WebSocket 연결 종료: " + session.getId());
    }
}
