package HeartGuard.Socket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessionMap = new HashMap<>();

    // 소켓에 연결되면 호출
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String hid = session.getUri().getPath().split("/")[2]; // URI에서 병원 ID 추출
        sessionMap.put(hid, session); // 병원 ID로 소켓 세션 저장
        System.out.println("병원 " + hid + "에 소켓 연결됨.");
    }

    // 소켓 연결이 종료되면 호출
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        String hid = session.getUri().getPath().split("/")[2];
        sessionMap.remove(hid);  // 병원별로 소켓 세션 제거
        System.out.println("병원 " + hid + "의 소켓 연결 종료.");
    }

    // 병원에 해당하는 소켓에 입장
    public void enterHospitalSocket(String hid) {
        WebSocketSession session = sessionMap.get(hid);  // 병원 ID에 해당하는 세션 찾기
        if (session != null) {
            System.out.println("병원 " + hid + "에 소켓 입장");
        } else {
            System.out.println("병원 " + hid + "에 대한 연결이 없습니다.");
        }
    }
}
