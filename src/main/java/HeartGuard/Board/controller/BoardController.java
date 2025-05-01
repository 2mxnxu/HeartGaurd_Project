package HeartGuard.Board.controller;

import HeartGuard.Board.Category.CategoryDto;
import HeartGuard.Board.model.dto.BoardDto;
import HeartGuard.Board.service.BoardService;
import HeartGuard.Hospital.service.HospitalService;
import HeartGuard.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    //1. 게시물 등록
    @PostMapping("/post")
    public ResponseEntity<Boolean> postBoard(
            @RequestHeader("Authorization") String token,
            @ModelAttribute BoardDto boardDto) {

        int loginUno;
        try {
            loginUno = userService.info(token).getUno();
        } catch (Exception e) {
            return ResponseEntity.status(401).body(false);
        }
        boolean result = boardService.postBoard(boardDto, loginUno);
        if (result == false) return ResponseEntity.status(400).body(false);
        return ResponseEntity.status(201).body(true);
    }//f e

    //2.카테고리별 게시물 전체조회
    @GetMapping("/categoryall")
    public ResponseEntity<List<BoardDto>>categoryall(
            @RequestParam(required = false)long cno){
        List<BoardDto> boardDtoList = boardService.categoryall( cno );
        return ResponseEntity.status(200).body(boardDtoList);
    }

    //3.게시물 개별 조회
    @GetMapping("/view")
    public ResponseEntity<BoardDto> viewBoard(@RequestParam long bno){
        BoardDto boardDto = boardService.viewBoard(bno);
        if(boardDto == null){
            return ResponseEntity.status(404).body(null);
        }else{
            return ResponseEntity.status(200). body(boardDto);
        }
    }

    //4.게시물 개별 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteBoard(
            @RequestHeader("Authorization") String token,
            @RequestParam int bno){
        int loginUno;
        try{
            loginUno = userService.info(token).getUno();
        }catch (Exception e){
            return ResponseEntity.status(401).body(false);
        }
        boolean result = boardService.deleteBoard(bno,loginUno);
        if(result==false) return ResponseEntity.status(400).body(false);
        return ResponseEntity.status(200).body(true);
    }
    //5.카테고리 조회
    @GetMapping("/category")
    public ResponseEntity<List<CategoryDto>> allCategory(){
        List<CategoryDto>categoryDtoList = boardService.allCategory();
        return ResponseEntity.status(200).body(categoryDtoList);
    }

    //+검색 페이징(무한 스크롤)
    @GetMapping("/all")
    public ResponseEntity<Page<BoardDto>> allBoards(
            @RequestParam(required = false) Long cno,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false)String keyword){
        Page<BoardDto> boardDtoList = boardService.allBoards(cno,page,size,keyword);
        return ResponseEntity.status(200).body(boardDtoList);
    }

}
