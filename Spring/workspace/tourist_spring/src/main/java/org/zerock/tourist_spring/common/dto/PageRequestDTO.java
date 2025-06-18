package org.zerock.tourist_spring.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    @Min(value=1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value=10)
    @Max(value=100)
    private int size = 10;

    private String searchType;
    private String searchWord;

    private String link;

    public int getSkip(){
        return (page - 1) * size;
    }

    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();

            if (this.searchType != null && !this.searchType.isEmpty()) {
                builder.append("&searchType=").append(this.searchType);
            }

            if (this.searchWord != null && !this.searchWord.isEmpty()) {
                builder.append("&searchWord=").append(this.searchWord);
            }

            link = builder.toString();
        }
        return link;
    }
}
