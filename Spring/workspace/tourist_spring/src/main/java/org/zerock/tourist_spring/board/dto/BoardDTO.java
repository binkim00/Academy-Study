package org.zerock.tourist_spring.board.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class BoardDTO {
    private Integer num;
    private String title;
    private String content;
    private String id;
    private Timestamp postdate;
    private Integer visitcount;
}

