package HeartGuard.Board.controller;

import HeartGuard.Board.model.dto.BoardDto;
import HeartGuard.Board.model.dto.ReplyDto;
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
    ){
        int loginUno;
        try{
            loginUno = userService.info(token).getUno();

        }catch (Exception e) {
            return ResponseEntity.status(401).body(false);
        }
        //boolean result = replyService.postReply(replyDto, loginUno, boardEntity);
        //if(result==false) return ResponseEntity.status(400).body(false);
        //return ResponseEntity.status(201).body(true);
        return null;
    }

    @GetMapping("/view")
    public ResponseEntity<List<ReplyDto>>viewReply(
            @RequestParam long bno){
        List<ReplyDto> replyDtoList = replyService.viewReply(bno);
        return ResponseEntity.status(200).body(replyDtoList);
    }



}
