package HeartGuard.Board.Category;

import lombok.*;

@Getter @Setter@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private long cno;
    private String cname;

    public static CategoryDto
    toDto( CategoryEntity categoryEntity ){
        return CategoryDto.builder()
                .cno( categoryEntity.getCno()  )
                .cname( categoryEntity.getCname() )
                .build();
    }
}