package HeartGuard.Board.controller;

import HeartGuard.Board.service.BoardService;
import HeartGuard.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reply")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReplyController {
    private final BoardService boardService;
    private final UserService userService;

}
