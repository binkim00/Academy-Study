package org.zerock.test1.vo;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {
    private Long tno;
    private String title;
    private String writer;
    private LocalDate dueDate;
    private boolean finished;

    public void changeTodo(String title, LocalDate dueDate, boolean finished) {
        this.title = title;
        this.dueDate = dueDate;
        this.finished = finished;
    }
}
