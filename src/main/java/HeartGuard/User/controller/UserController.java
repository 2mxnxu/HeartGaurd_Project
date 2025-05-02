package HeartGuard.User.controller;

import HeartGuard.User.model.dto.UserDto;
import HeartGuard.User.service.UserService;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signUp(@RequestBody UserDto userDto){
        boolean result = userService.signUp(userDto);
        if(result){
            return ResponseEntity.status(201).body(true);
        }else {
            return  ResponseEntity.status(400).body(false);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
        String token = userService.login(userDto);
        if(token != null){return ResponseEntity.status(200).body(token);}
        else{return ResponseEntity.status(401).body("로그인 실패");}
    }

    @GetMapping("/info")
    public ResponseEntity<UserDto> info(@RequestHeader("Authorization") String token){
        System.out.println(token);
        UserDto userDto = userService.info(token);
        if(userDto != null){return ResponseEntity.status(200).body(userDto);}
        else{return ResponseEntity.status(403).build();}
    }

    @GetMapping("logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token){
        userService.logout(token);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestHeader("Authorization") String token,
                                          @RequestBody UserDto userDto){
        String uid = userService.extractUidFromToken(token);
        if(uid == null){
            return ResponseEntity.status(401).body(false);
        }
        userDto.setUid(uid);
        boolean result = userService.update(userDto);
        if(result){
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(404).body(false);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestHeader("Authorization") String token){
        String uid = userService.extractUidFromToken(token);
        if(uid == null){
            return ResponseEntity.status(401).body(false);
        }
        boolean result = userService.delete(uid);
        if(result){
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(404).body(false);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> getUsers(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "uno", required = false) Integer uno,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "keyword", required = false) String keyword) {
        String uid = userService.extractUidFromToken(token);

        // 관리자 인증 (ustate == 1)
        if (uid == null || !userService.isAdmin(uid)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Page<UserDto> result = userService.getUsers(uno, page - 1, keyword); // page는 0부터 시작
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(
            @RequestHeader("Authorization") String token,
            @RequestParam("uno") Integer uno) {

        String requesterUid = userService.extractUidFromToken(token);
        if (requesterUid == null || !userService.isAdmin(requesterUid)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한 없음");
        }

        boolean result = userService.deleteUser(uno, requesterUid);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패 (자기 자신 또는 존재하지 않음)");
        }
    }

    // 만약에 True 이면 관리자(관리자모드) false 이면 병원 info 한테 요청해서 null 아니면 병원 (병원 신고내역) null 이면 일반 회원 (신고하기)


}
