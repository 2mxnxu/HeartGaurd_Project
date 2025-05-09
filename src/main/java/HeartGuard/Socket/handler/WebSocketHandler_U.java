package HeartGuard.Socket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Vector;

@Component
public class WebSocketHandler_U extends TextWebSocketHandler {

    private static List<WebSocketSession> sessionList = new Vector<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        System.out.println("사용자" + session + "에 소켓 연결");
        System.out.println(sessionList);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessionList.remove(session);  // 사용자별 소켓 세션 제거
        System.out.println("사용자 " + sessionList + "의 소켓 연결 종료.");
    }
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println( sessionList );
        for( WebSocketSession session1 : sessionList ){
            session1.sendMessage( message );
        }
    }
}
