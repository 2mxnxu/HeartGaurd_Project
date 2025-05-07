package HeartGuard.User.service;

import HeartGuard.User.model.dto.UserDto;
import HeartGuard.User.model.entity.UserEntity;
import HeartGuard.User.model.respository.UserEntityRepository;
import HeartGuard.Util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserEntityRepository userEntityRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public boolean signUp(UserDto userDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPwd = passwordEncoder.encode(userDto.getUpwd());
        userDto.setUpwd(hashedPwd);
        UserEntity userEntity = userDto.toEntity();
        UserEntity saveEntity = userEntityRepository.save(userEntity);
        if(saveEntity.getUno() >= 1){return true;}
        return false;
    }

    public String login(UserDto userDto){
        UserEntity userEntity = userEntityRepository.findByUid(userDto.getUid());
        if(userEntity == null){return null;}
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean inMath =passwordEncoder.matches(userDto.getUpwd() , userEntity.getUpwd());
        if(inMath == false){return null;}
        String token = jwtUtil.createToken1(userEntity.getUid());
        System.out.println(">> 발급 토큰 "+token);
        stringRedisTemplate.opsForValue().set("RECCENT_LOGIN:"+userDto.getUid(), "true", 1, TimeUnit.DAYS);
        return token;
    }

    public UserDto info(String token){
        String uid = jwtUtil.validateToken1(token);
        if(uid == null){return null;}
        UserEntity userEntity = userEntityRepository.findByUid(uid);
        if(userEntity == null){return null;}
        return userEntity.toDto();
    }

    public void logout(String token){
        String uid = jwtUtil.validateToken1(token);
        jwtUtil.deleteToken1(uid);
    }

    public boolean update(UserDto userDto) {
        UserEntity userEntity = userEntityRepository.findByUid(userDto.getUid());
        if (userEntity == null) {
            return false;
        }
        if (userDto.getUpwd() != null && !userDto.getUpwd().isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPwd = passwordEncoder.encode(userDto.getUpwd());
            userEntity.setUpwd(hashedPwd);
        }
        if (userDto.getUname() != null) {
            userEntity.setUname(userDto.getUname());
        }
        if (userDto.getUphone() != null) {
            userEntity.setUphone(userDto.getUphone());
        }
        userEntityRepository.save(userEntity);
        return true;
    }

    public boolean delete(String uid) {
        UserEntity userEntity = userEntityRepository.findByUid(uid);
        if (userEntity == null) {
            return false;
        }
        userEntityRepository.delete(userEntity);
        return true;
    }
    public String extractUidFromToken(String token) {
        return jwtUtil.validateToken1(token);
    }

    public Page<UserDto> getUsers(Integer uno, int page, String keyword) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("uno").descending());

        // 개별 조회
        if (uno != null) {
            Optional<UserEntity> user = userEntityRepository.findByUno(uno);
            List<UserDto> single = user.map(u -> List.of(u.toDto())).orElse(List.of());
            return new PageImpl<>(single, pageable, single.size());
        }

        // 검색 or 전체 조회
        Page<UserEntity> result;
        if (keyword != null && !keyword.isBlank()) {
            result = userEntityRepository.findByUnameContaining(keyword, pageable);
        } else {
            result = userEntityRepository.findAll(pageable);
        }

        return result.map(UserEntity::toDto);
    }

    // 관리자 여부 확인
    public boolean isAdmin(String uid) {
        UserEntity user = userEntityRepository.findByUid(uid);
        return user != null && user.getUstate() == 1;
    }

    public boolean deleteUser(Integer targetUno, String requesterUid) {
        // 삭제 대상
        Optional<UserEntity> targetOpt = userEntityRepository.findById(targetUno);
        if (targetOpt.isEmpty()) return false;

        UserEntity target = targetOpt.get();

        // 본인 삭제 방지
        if (target.getUid().equals(requesterUid)) return false;

        userEntityRepository.deleteById(targetUno);
        return true;
    }

}
