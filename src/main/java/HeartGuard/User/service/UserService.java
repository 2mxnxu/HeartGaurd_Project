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

    public boolean signUp(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPwd = passwordEncoder.encode(userDto.getUpwd());
        userDto.setUpwd(hashedPwd);
        UserEntity userEntity = userDto.toEntity();
        UserEntity saveEntity = userEntityRepository.save(userEntity);
        return saveEntity.getUno() >= 1;
    }

    public String login(UserDto userDto) {
        UserEntity userEntity = userEntityRepository.findByUid(userDto.getUid());
        if (userEntity == null) return null;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean inMatch = passwordEncoder.matches(userDto.getUpwd(), userEntity.getUpwd());
        if (!inMatch) return null;

        String token = jwtUtil.createToken1(userEntity.getUid());
        System.out.println(">> 발급 토큰 " + token);

        stringRedisTemplate.opsForValue().set("RECCENT_LOGIN:" + userDto.getUid(), "true", 1, TimeUnit.DAYS);
        return token;
    }

    public UserDto info(String token) {
        // ✅ Bearer 접두어 제거
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String uid = jwtUtil.validateToken1(token);
        if (uid == null) return null;

        UserEntity userEntity = userEntityRepository.findByUid(uid);
        if (userEntity == null) return null;

        return userEntity.toDto();
    }

    public void logout(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String uid = jwtUtil.validateToken1(token);
        jwtUtil.deleteToken1(uid);
    }

    public boolean update(UserDto userDto) {
        UserEntity userEntity = userEntityRepository.findByUid(userDto.getUid());
        if (userEntity == null) return false;

        if (userDto.getUpwd() != null && !userDto.getUpwd().isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPwd = passwordEncoder.encode(userDto.getUpwd());
            userEntity.setUpwd(hashedPwd);
        }

        if (userDto.getUname() != null) {
            userEntity.setUname(userDto.getUname());
        }

        userEntityRepository.save(userEntity);
        return true;
    }

    public boolean delete(String uid) {
        UserEntity userEntity = userEntityRepository.findByUid(uid);
        if (userEntity == null) return false;

        userEntityRepository.delete(userEntity);
        return true;
    }

    public String extractUidFromToken(String token) {
        return jwtUtil.validateToken1(token);
    }

    public Page<UserDto> getUsers(Integer uno, int page, String keyword) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("uno").descending());

        if (uno != null) {
            Optional<UserEntity> user = userEntityRepository.findByUno(uno);
            List<UserDto> single = user.map(u -> List.of(u.toDto())).orElse(List.of());
            return new PageImpl<>(single, pageable, single.size());
        }

        Page<UserEntity> result;
        if (keyword != null && !keyword.isBlank()) {
            result = userEntityRepository.findByUnameContaining(keyword, pageable);
        } else {
            result = userEntityRepository.findAll(pageable);
        }

        return result.map(UserEntity::toDto);
    }

    public boolean isAdmin(String uid) {
        UserEntity user = userEntityRepository.findByUid(uid);
        return user != null && user.getUstate() == 1;
    }

    public boolean deleteUser(Integer targetUno, String requesterUid) {
        Optional<UserEntity> targetOpt = userEntityRepository.findById(targetUno);
        if (targetOpt.isEmpty()) return false;

        UserEntity target = targetOpt.get();

        if (target.getUid().equals(requesterUid)) return false;

        userEntityRepository.deleteById(targetUno);
        return true;
    }
}
