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

    // 마지막 페이지 번호
    private int last;

    // 실제 출력할 데이터 목록
    private List<E> dtoList;

    // Builder 어노테이션을 사용하여 전체 필드를 한번에 설정할 수 있도록 구성
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,
                           List<E> dtoList,
                           int total){
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        // 현재 페이지를 기준으로 마지막 페이지 그룹의 끝 번호 계산
        // 예: 1 ~ 10, 11 ~ 20 등으로 구성
        this.end = (int)(Math.ceil(this.page/10.0)) * 10;

        // 현재 페이지 그룹의 시작 번호
        this.start = this.end - 9;

        // 전체 데이터를 기반으로 한 진짜 마지막 페이지 번호
        this.last = (int)(Math.ceil(total / (double)size));

        // 계산된 end가 실제 마지막 페이지보다 크면 보정
        this.end = end > last ? last : end;

        // 첫 번째 그룹이 아니면 이전 버튼 존재
        this.prev = this.start > 1;

        // 현재 end 페이지가 전체 데이터를 모두 포함하지 못하면 다음 버튼 존재
        this.next = total > this.end * this.size;
    }
}
