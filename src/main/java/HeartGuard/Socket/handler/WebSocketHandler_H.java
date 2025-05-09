package HeartGuard.Socket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Component
public class WebSocketHandler_H extends TextWebSocketHandler {

    private static List<WebSocketSession> sessionList = new Vector<>();

    // 소켓에 연결되면 호출
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add( session); // 병원 ID로 소켓 세션 저장
        System.out.println("병원 " + session + "에 소켓 연결됨.");
        System.out.println( sessionList );
    }

    // 소켓 연결이 종료되면 호출
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessionList.remove(session);  // 병원별로 소켓 세션 제거
        System.out.println("병원 " + sessionList + "의 소켓 연결 종료.");
    }

    // 서버 소켓이 메시지를 받고 메시지를 보내는 함수
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println( sessionList );
        for( WebSocketSession session1 : sessionList ){
            session1.sendMessage( message );
        }
    }
}
