package HeartGuard.Board.controller;

import HeartGuard.Board.model.dto.BoardDto;
import HeartGuard.Board.model.dto.ReplyDto;
import HeartGuard.Board.model.entity.BoardEntity;
import HeartGuard.Board.service.BoardService;
import HeartGuard.Board.service.ReplyService;
import HeartGuard.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReplyController {
    private final BoardService boardService;
    private final ReplyService replyService;
    private final UserService userService;

    @PostMapping("/post")
    public ResponseEntity<Boolean> postReply(
            @RequestHeader("Authorization") String token,
            @RequestBody ReplyDto replyDto
    ) {
        int loginUno;
        int loginUstate;
        try {
            loginUno = userService.info(token).getUno();
            loginUstate = userService.info(token).getUstate();
        } catch (Exception e) {
            return ResponseEntity.status(401).body(false); // 인증 실패
        }

        // 상태가 0이면 댓글 등록 차단
        if (loginUstate == 0) {
            return ResponseEntity.status(403).body(false); // 권한 없음
        }

        // 게시물 조회
        BoardEntity boardEntity = boardService.getBoardEntity(replyDto.getBno());
        if (boardEntity == null) {
            return ResponseEntity.status(404).body(false); // 게시물 없음
        }

        boolean result = replyService.postReply(replyDto, loginUno, boardEntity);
        if (!result) return ResponseEntity.status(400).body(false); // 등록 실패

        return ResponseEntity.status(201).body(true); // 등록 성공
    }




    @GetMapping("/view")
    public ResponseEntity<List<ReplyDto>>viewReply(
            @RequestParam long bno){
        List<ReplyDto> replyDtoList = replyService.viewReply(bno);
        return ResponseEntity.status(200).body(replyDtoList);
    }



}
