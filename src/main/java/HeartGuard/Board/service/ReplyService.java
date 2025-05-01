package HeartGuard.Board.service;

import HeartGuard.Board.model.respository.BoardEntityRepository;
import HeartGuard.User.model.respository.UserEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    private final BoardEntityRepository boardEntityRepository;
    private final UserEntityRepository userEntityRepository;



}
