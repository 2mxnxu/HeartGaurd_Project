package HeartGuard.Board.service;

import HeartGuard.Board.model.dto.BoardDto;
import HeartGuard.Board.model.dto.ReplyDto;
import HeartGuard.Board.model.entity.BoardEntity;
import HeartGuard.Board.model.entity.ReplyEntity;
import HeartGuard.Board.model.respository.BoardEntityRepository;
import HeartGuard.Board.model.respository.ReplyEntityRepository;
import HeartGuard.User.model.entity.UserEntity;
import HeartGuard.User.model.respository.UserEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    private final BoardEntityRepository boardEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final ReplyEntityRepository replyEntityRepository;

    //댓글 등록
    public boolean postReply(ReplyDto replyDto, int loginUno, BoardEntity boardEntity) {
        Optional<UserEntity> optionalUserEntity = userEntityRepository.findById(loginUno);
        if (optionalUserEntity.isEmpty()) return false;

        UserEntity userEntity = optionalUserEntity.get();  // UserEntity를 가져옴

        ReplyEntity replyEntity = replyDto.toEntity(boardEntity, userEntity);  // 수정된 부분
        ReplyEntity saveEntity = replyEntityRepository.save(replyEntity);

        if (saveEntity.getRno() < 0) return false;
        return true;
    }


    //게시물 별 댓글 조회
    public List<ReplyDto> viewReply(Long bno){
    List<ReplyEntity> replyEntityList;
    if( bno != null && bno>0){
        replyEntityList = replyEntityRepository.findByBoardEntityBno(bno);
    }else{
        replyEntityList = replyEntityRepository.findAll();
    }
        return replyEntityList.stream()
                .map(ReplyDto::toDto)
                .collect(Collectors.toList());



    //    List<ReplyDto> replyDtoList = replyEntityList.stream()
//            .map(ReplyDto::toDto)
//            .collect(Collectors.toList());
//    return replyDtoList;

    }



}
