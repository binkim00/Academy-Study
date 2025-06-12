package org.zerock.springex2.VO;

import lombok.*;

import java.time.LocalDate;
// VO의 경우 @Setter는 설정하지 않는다
// 특정 열은 변경하면 안되기 때문에
@Getter // Getter메서드 생성
@NoArgsConstructor // 기본 생성자 생성 public TodoDTO(){}
@AllArgsConstructor // 모든 데이터를 저장하는 생성자 생성
@ToString // TodoDTO안에 있는 모든 데이터를 확인할 수 있는 toString을 생성
@Builder
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;
    public void changeTodo(String title, LocalDate dueDate, boolean finished) {
        this.title = title;
        this.dueDate = dueDate;
        this.finished = finished;
    }
}
