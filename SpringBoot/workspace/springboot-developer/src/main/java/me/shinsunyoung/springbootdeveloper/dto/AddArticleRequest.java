package me.shinsunyoung.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;
    private String writer;
    public Article toEntity(String writer){
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .writer(writer)
                .build();
    }
}
