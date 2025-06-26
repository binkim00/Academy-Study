package org.zerock.tourist_springboot.common.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    // 아무것도 설정하지 않고 DTO를 만들었을 경우 0이 아닌 1이 설정됨
    @Builder.Default
    @Min(value=1)
    // 정수만 저장할 수 있도록 설정
    @Positive
    private int page = 1;

    // 최소값은 10, 최대값은 100으로 설정
    @Builder.Default
    @Min(value=10)
    @Max(value=100)
    private int size = 10;

    // 현재 페이지 정보를 문자열로 저장 (ex. page=1&size=10 등)
    private String link;

    // 검색 조건 (예: 제목, 작성자 등)
    private String[] types;

    // 검색 키워드
    private String keyword;

    // 완료 여부 (예: 체크박스)
    private boolean finished;

    // 검색 시작 날짜
    private LocalDate from;

    // 검색 종료 날짜
    private LocalDate to;

    // JPA에서 사용하는 Pageable 객체를 생성하는 메서드
    // 정렬 기준 컬럼명을 매개변수로 받아 PageRequest.of(...)를 생성함
    public Pageable getPageable(String sortBy) {
        // page는 1부터 시작하지만 JPA는 0부터 시작하므로 -1 처리
        // 예: page=2, size=10이면 11번째부터 10개를 조회
        return PageRequest.of(this.page - 1, this.size, Sort.by(sortBy).descending());
    }

    public String getLink() {
        StringBuilder builder = new StringBuilder();
        builder.append("page=").append(this.page);
        builder.append("&size=").append(this.size);

        if (finished) {
            builder.append("&finished=on");
        }

        if (types != null && types.length > 0) {
            for (String type : types) {
                builder.append("&types=").append(type);
            }
        }

        if (keyword != null) {
            try {
                builder.append("&keyword=").append(URLEncoder.encode(keyword, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (from != null) {
            builder.append("&from=").append(from.toString());
        }
        if (to != null) {
            builder.append("&to=").append(to.toString());
        }

        return builder.toString();
    }

}
