package HeartGuard.Board.service;

import HeartGuard.Board.Category.CategoryDto;
import HeartGuard.Board.Category.CategoryEntity;
import HeartGuard.Board.Category.CategoryEntityRepository;
import HeartGuard.Board.model.dto.BoardDto;
import HeartGuard.Board.model.entity.BoardEntity;
import HeartGuard.Board.model.entity.ImgEntity;
import HeartGuard.Board.model.respository.BoardEntityRepository;
import HeartGuard.Board.model.respository.ImgEntityRepository;
import HeartGuard.User.model.entity.UserEntity;
import HeartGuard.User.model.respository.UserEntityRepository;
import HeartGuard.Util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final ImgEntityRepository imgEntityRepository;
    private final FileUtil fileUtil;

    public boolean postBoard(BoardDto boardDto,int loginUno){
        Optional<UserEntity> optionalUserEntity = userEntityRepository.findById(loginUno);
        if(optionalUserEntity.isEmpty())return false;
        Optional<CategoryEntity> optionalCategoryEntity = categoryEntityRepository.findById(boardDto.getCno());
        BoardEntity boardEntity = boardDto.toEntity(optionalUserEntity.get(), optionalCategoryEntity.get());
        boardEntity.setUserEntity(optionalUserEntity.get());
        boardEntity.setCategoryEntity(optionalCategoryEntity.get());
        BoardEntity saveEntity = boardEntityRepository.save(boardEntity);
        if(saveEntity.getBno()<=0)return false;
        return true;
    }

        //카테고리별 게시물 전체조회
    public List<BoardDto> categoryall(Long cno){
        List<BoardEntity> boardEntityList;
        if(cno !=null && cno>0){
            boardEntityList = boardEntityRepository.findByCategoryEntityCno(cno);
        }else{
            boardEntityList = boardEntityRepository.findAll();
        }
        return boardEntityList.stream()
                .map(BoardDto ::toDto)
                .collect(Collectors.toList());
    }
    //게시물 개별조회
    public BoardDto viewBoard(long bno){
        Optional<BoardEntity> boardEntityOptional =
                boardEntityRepository.findById(bno);

        if(boardEntityOptional.isEmpty()) return null;

        BoardEntity boardEntity =
                boardEntityOptional.get();

        boardEntity.setBview(boardEntity.getBview()+1);
        return BoardDto.toDto(boardEntity);
    }

    //게시물 개별 삭제
    public boolean deleteBoard(long bno, int loginUno){
        Optional<BoardEntity> boardEntityOptional =
                boardEntityRepository.findById(bno);
        if(boardEntityOptional.isEmpty()) return false;
        BoardEntity boardEntity = boardEntityOptional.get();
        if(boardEntity.getUserEntity().getUno() != loginUno){
            return false;
        }
        boardEntityRepository.deleteById(bno);
        return true;
    }

    //게시물 수정(+이미지)
    public boolean updateBoard(BoardDto boardDto, int loginUno){
        Optional<BoardEntity> boardEntityOptional = boardEntityRepository.findById(boardDto.getBno());
        if(boardEntityOptional.isEmpty()) return false;
        BoardEntity boardEntity = boardEntityOptional.get();
        if(boardEntity.getUserEntity().getUno() != loginUno) return false;
        Optional<CategoryEntity> categoryEntityOptional = categoryEntityRepository.findById(boardDto.getBno());
        if(categoryEntityOptional.isEmpty()) return false;
        CategoryEntity categoryEntity = categoryEntityOptional.get();
        boardEntity.setBtitle(boardDto.getBtitle());
        boardEntity.setBcontent(boardDto.getBcontent());
        boardEntity.setBview(boardDto.getBview());
        boardEntity.setCategoryEntity(categoryEntity);
        List<MultipartFile> newFile = boardDto.getFiles();
        if(newFile != null && !newFile.isEmpty()){
            for(MultipartFile file : newFile){
                String saveFileName = fileUtil.fileUpload(file);
                if(saveFileName == null) throw new RuntimeException("파일 업로드 오류발생");
                ImgEntity imgEntity = ImgEntity.builder()
                        .iname(saveFileName)
                        .boardEntity(boardEntity)
                        .build();
                imgEntityRepository.save( imgEntity ); // 이미지 엔티티 저장(영속) , 자바객체 <--영속O--> DB테이블레코드
            }
        }
        return true;
    }

    //이미지 개별 조회
    public boolean deleteImage(long ino, int loginUno){
        Optional<ImgEntity> optionalImgEntity = imgEntityRepository.findById(ino);
        if(optionalImgEntity.isEmpty())return false;
        ImgEntity imgEntity = optionalImgEntity.get();
        if(imgEntity.getBoardEntity().getUserEntity().getUno() != loginUno) return false;
        String deleteFileName = imgEntity.getIname();
        boolean result = fileUtil.fileDelete(deleteFileName);
        if(result == false) throw new RuntimeException("파일 삭제 실패");
        imgEntityRepository.deleteById(ino);
        return true;
    }

    //카테고리 조회
    public List<CategoryDto>allCategory(){
        List<CategoryEntity> categoryEntityList = categoryEntityRepository.findAll();
        List<CategoryDto> categoryDtoList = categoryEntityList.stream()
                .map(CategoryDto::toDto)
                .collect(Collectors.toList());
        return categoryDtoList;
    }

    //검색 + 페이징처리
    public Page<BoardDto> allBoards(Long cno, int page, int size, String keyword){
        Pageable pageable = PageRequest.of(page-1,size, Sort.by(Sort.Direction.DESC,"bno"));
        Page<BoardEntity> boardEntities = boardEntityRepository.findBySearch(cno,keyword,pageable);
        Page<BoardDto> boardDtoList = boardEntities.map(BoardDto::toDto);
        return boardDtoList;
    }


}
