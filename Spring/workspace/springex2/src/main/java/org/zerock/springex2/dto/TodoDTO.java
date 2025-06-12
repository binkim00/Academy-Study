package org.zerock.springex2.dto;

import lombok.*;

import java.time.LocalDate;

// 2번 방법 : lombok 라이브러리의 어노테이션을 이용하여 추가
@Getter // Getter메서드 생성
@Setter // Setter메서드 생성
@NoArgsConstructor // 기본 생성자 생성 public TodoDTO(){}
@AllArgsConstructor // 모든 데이터를 저장하는 생성자 생성
@ToString // TodoDTO안에 있는 모든 데이터를 확인할 수 있는 toString을 생성
@Builder // TodoDTO생성에 사용하는 메서드 생성
@Data // @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
public class TodoDTO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;
    // 1번 방법 : alt+insert 단축키로 getter,setter,constructor 자동 생성

    //AllArgsConstructor로 생성한 생성자
//    public TodoDTO(Long tno, String title, LocalDate dueDate, boolean finished, String writer) {
//        this.tno = tno;
//        this.title = title;
//        this.dueDate = dueDate;
//        this.finished = finished;
//        this.writer = writer;
//    }
}
