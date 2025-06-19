package org.zerock.test1.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberVO {
    private String id;
    private String pw;
    private String email1;
    private String email2;
    private LocalDateTime regdate;
}
