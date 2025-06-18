package org.zerock.tourist_spring.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;
    private int last;
    private List<E> dtoList;


    private PageRequestDTO pageRequestDTO;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,
                           List<E> dtoList,
                           int total) {

        this.pageRequestDTO = pageRequestDTO;
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;
        this.start = this.end - 9;
        this.last = (int)(Math.ceil((total / (double)size)));

        this.end = Math.min(this.end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }
}









