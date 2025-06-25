package org.zerock.tourist_springboot.board.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="num", updatable = false)
    private Long num;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name="id")
    private String id;

    @Column(name="visitcount")
    private int visitCount;
    public void increaseVisitCount() {
        this.visitCount = this.visitCount + 1;
    }

    // 데이터가 처음 저장될때의 날짜 및 시간을 자동 저장
    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    // 데이터가 마지막에 수정된 날짜 및 시간을 자동 저장
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
