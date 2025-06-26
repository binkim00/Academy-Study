package org.zerock.tourist_springboot.program.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProgramDTO {

    private Long pno;

    private String title;

    private String description;

    private String subtext;

    private String schedule;

    private String image;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
