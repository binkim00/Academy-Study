package org.zerock.tourist_spring.board.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardVO {
    private int num;
    private String title;
    private String content;
    private String id;
    private Timestamp postdate;
    private int visitcount;
}
