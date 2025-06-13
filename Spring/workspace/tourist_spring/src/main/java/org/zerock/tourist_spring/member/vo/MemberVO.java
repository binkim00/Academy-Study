package org.zerock.tourist_spring.member.vo;

import lombok.Data;
import java.util.Date;

@Data
public class MemberVO {
    private String id;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String gender;
    private boolean agree;
    private String content;
    private Date regidate;
}

//      | 순서 | 항목                    | 설명            |
//        | -- | --------------------- | ------------- |
//        | 1  | DB 테이블 생성          | 데이터 저장 구조 설계  |
//        | 2  | DTO 생성               | 데이터 전달용 클래스   |
//        | 3  | Mapper 인터페이스 & XML | DB 연동         |
//        | 4  | Service & ServiceImpl | 비즈니스 로직       |
//        | 5  | Controller            | 요청 처리         |
//        | 6  | JSP                   | 사용자 화면 (View) |
