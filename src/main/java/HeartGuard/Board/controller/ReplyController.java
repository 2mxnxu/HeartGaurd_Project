package HeartGuard.Board.controller;

import HeartGuard.Board.model.dto.ReplyDto;
import HeartGuard.Board.service.BoardService;
import HeartGuard.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReplyController {
    private final BoardService boardService;
    private final UserService userService;

//    @PostMapping("/post")
//    public ResponseEntity<Boolean> postReply(
//            @RequestHeader("Authorization") String token,
//            @ModelAttribute ReplyDto replyDto){
//        int loginUno;
//        try{
//            loginUno = userService.info(token).getUno();
//            if(userService.info(token).getUstate() ==1){
//
//            }
//        }catch (e)
//    }



}
