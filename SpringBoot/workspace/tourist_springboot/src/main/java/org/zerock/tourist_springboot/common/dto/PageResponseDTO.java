package org.zerock.tourist_springboot.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PageResponseDTO<E> {
    // 현재 선택한 페이지
    private int page;
    // 한번에 출력할 데이터의 개수
    private int size;
    // 전체 데이터의 개수
    private int total;
    // 시작 페이지의 번호
    private int start;
    // 끝 페이지의 번호
    private int end;
    // 이전 페이지의 존재 여부
    private boolean prev;
    // 다음 페이지의 존재 여부
    private boolean next;

    private int last;
    // 게시글의 데이터 리스트
    private List<E> dtoList;
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,
                           List<E> dtoList,
                           int total){
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        // page를 10으로 나눈 값을 올림한 후 곱하기 10
        // 1 / 10 => 0.1 올림 => 1 * 10 => 10
        // 5 / 10 => 0.5 올림 => 1 * 10 => 10
        // 15/10 => 1.5 올림 => 2 * 10 => 20
        this.end = (int)(Math.ceil(this.page/10.0))*10;
        // 현재 페이지에 해당하는 첫번째 페이지
        this.start = this.end - 9;
        // 마지막 페이지를 계산
        // 123/10 = 12.3 올림 => 13
        // 100/10 = 10 올림 => 10
        // 75/10 = 7.5 올림 => 8
        this.last = (int)(Math.ceil((total/(double)size)));
        // end가 last보다 큰 경우 마지막 페이지를 last로 변경
        // 10 > 8 ? 8 : 10 = 8
        // 10 > 16 ? 16 : 10 = 10
        // 20 > 16 ? 16 : 20 = 16
        this.end = end > last ? last : end;
        // 첫페이지가 1보다 크면 true
        this.prev = this.start > 1;
        // 다음페이지가 있을 경우 true
        this.next = total > this.end * this.size;

    }
}








