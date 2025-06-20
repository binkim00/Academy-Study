package org.zerock.testproject;

import jakarta.persistence.*;
import lombok.*;

@ToString
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
// @Entity를 사용하면 클래스를 테이블로 만들어줌
@Entity
public class Member {
    //PrimaryKey 설정
    @Id
    // AutoIncreament 설정 혹은 시퀀스 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // name="컬럼명" , updatable = 변경가능한지설정
    @Column(name="id", updatable = false)
    private Long id;

    // nullable = null 입력이 가능한지 설정
    @Column(name="name", nullable = false)
    private String name;
}
