package HeartGuard.Board.service;

import HeartGuard.Board.Category.CategoryEntity;
import HeartGuard.Board.Category.CategoryEntityRepository;
import HeartGuard.Board.model.dto.BoardDto;
import HeartGuard.Board.model.entity.BoardEntity;
import HeartGuard.Board.model.respository.BoardEntityRepository;
import HeartGuard.User.model.entity.UserEntity;
import HeartGuard.User.model.respository.UserEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardEntityRepository boardEntityRepository;
    private final CategoryEntityRepository categoryEntityRepository;
    private final UserEntityRepository userEntityRepository;

//    public boolean postBoard(BoardDto boardDto,int loginUno){
//        Optional<UserEntity> optionalUserEntity = userEntityRepository.findById(loginUno);
//        if(optionalUserEntity.isEmpty())return false;
//        Optional<CategoryEntity> optionalCategoryEntity = categoryEntityRepository.findById(boardDto.getCno());
//        BoardEntity boardEntity = boardDto.toEntity();
//        boardEntity.setUserEntity(optionalUserEntity.get());
//        boardEntity.setCategoryEntity(optionalCategoryEntity.get());
//        BoardEntity saveEntity = boardEntityRepository.save(boardEntity);
//        if(saveEntity.getBno()<=0)return false;
//        return true;
//    }
//
//    //카테고리별 게시물 전체조회
//    public List<BoardDto> allBoards(Long cno){
//        List<BoardEntity> boardEntityList;
//        if(cno !=null && cno>0){
//            boardEntityList = boardEntityRepository.findByCategoryEntityCno(cno);
//        }else{
//            boardEntityList = boardEntityRepository.findAll();
//        }
//        return boardEntityList.stream()
//                .map(BoardDto ::toDto)
//                .collect(Collectors.toList());
//    }
}
