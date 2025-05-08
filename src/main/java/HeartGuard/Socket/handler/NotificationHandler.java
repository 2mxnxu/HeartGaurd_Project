package HeartGuard.Socket.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationHandler extends TextWebSocketHandler {

    // 접속 중인 모든 관리자 세션 목록
    private static final CopyOnWriteArrayList<WebSocketSession> adminSessions = new CopyOnWriteArrayList<>();

    // 클라이언트가 WebSocket에 연결했을 때 호출됨
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        adminSessions.add(session);
        System.out.println("[WebSocket 연결됨] 세션 ID: " + session.getId());
    }

    // 클라이언트가 WebSocket 연결을 끊었을 때 호출됨
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        adminSessions.remove(session);
        System.out.println("[WebSocket 연결 종료] 세션 ID: " + session.getId());
    }

    // 클라이언트로부터 메시지를 수신했을 때 호출됨
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String msg = message.getPayload();

        // 콘솔 로그로 출력
        System.out.println(msg);

        // 모든 관리자 세션에 메시지 전송
        sendNotificationToAdmins(msg);
    }

    // 현재 연결된 모든 관리자에게 메시지 전송
    public static void sendNotificationToAdmins(String message) throws IOException {
        for (WebSocketSession session : adminSessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }
}
