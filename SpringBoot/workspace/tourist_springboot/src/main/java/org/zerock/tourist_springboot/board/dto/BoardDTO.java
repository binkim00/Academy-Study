package org.zerock.tourist_springboot.board.dto;

import lombok.*;
import org.zerock.tourist_springboot.board.domain.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long num;
    private String title;
    private String content;
    private String id;
    private int visitCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Board toEntity() {
        return Board.builder()
                .num(this.num)
                .title(this.title)
                .content(this.content)
                .id(this.id)
                .visitCount(this.visitCount)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

}
