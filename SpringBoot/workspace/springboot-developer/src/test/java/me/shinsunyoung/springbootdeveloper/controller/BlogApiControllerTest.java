package me.shinsunyoung.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.shinsunyoung.springbootdeveloper.domain.Article;
import me.shinsunyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyoung.springbootdeveloper.dto.UpdateArticleRequest;
import me.shinsunyoung.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private BlogRepository blogRepository;
    @BeforeEach
    public void mockMvcSetup(){
        // Controller를 실행할때 사용할 mockMvc설정
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();
        blogRepository.deleteAll();
    }
    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception{
        // given : 데이터 및 사전 준비
        final String url = "/api/articles"; // 실행할 컨트롤러의 주소
        final String title = "title"; // 저장할 데이터
        final String content = "content"; // 저장할 데이터
        final String writer = "writer";
        // Post실행시 전달할 객체
        final AddArticleRequest userRequest = new AddArticleRequest(title,content,writer);
        // userRequest 객체를 JSON형식의 문자열로 변경
        final String requestBody = objectMapper.writeValueAsString(userRequest);
        // when : 컨트롤러 실행 및 결과 저장
        ResultActions result = mockMvc.perform(post(url)
                // RestController는 JSON을 기본 데이터 형식이기 때문에 JSON으로 설정
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                // 보낼 데이터 설정
                .content(requestBody));
        //then : 결과가 예상과 일치하는지 확인
        // 통신코드가 201 created가 맞는지 확인
        result.andExpect(status().isCreated());
        // DB의 모든 데이터를 저장
        List<Article> articles = blogRepository.findAll();
        // 저장된 데이터가 1개가 맞는지 확인
        assertThat(articles.size()).isEqualTo(1);
        // title데이터가 일치하는지 확인
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        // content데이터가 일치하는지 확인
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }
    @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공한다.")
    @Test
    public void findAllArticles() throws Exception{
        final String url = "/api/articles"; // 실행할 컨트롤러의 주소
        final String title = "title"; // 저장할 데이터
        final String content = "content"; // 저장할 데이터
        // 새로운 데이터 저장
        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        // 전체 조회 Controller실행
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));
        // 결과 확인
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }
    @DisplayName("findArticle: 블로그 글 조회에 성공한다.")
    @Test
    public void findArticle() throws Exception{
        final String url = "/api/articles/{id}"; // 실행할 컨트롤러의 주소
        final String title = "title"; // 저장할 데이터
        final String content = "content"; // 저장할 데이터
        // 새로운 데이터 저장
        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        // 위에서 저장한 article 조회 Controller실행
        final ResultActions resultActions = mockMvc.perform(
                // url에 있는 {id}에 값을 설정
                get(url,savedArticle.getId()));
        // 결과 확인
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }
    @DisplayName("deleteArticle: 블로그 글 삭제에 성공한다.")
    @Test
    public void deleteArticle() throws Exception{
        final String url = "/api/articles/{id}"; // 실행할 컨트롤러의 주소
        final String title = "title"; // 저장할 데이터
        final String content = "content"; // 저장할 데이터
        // 새로운 데이터 저장
        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        // 위에서 저장한 article 삭제 Controller실행
        final ResultActions resultActions = mockMvc.perform(
                // url에 있는 {id}에 값을 설정
                delete(url,savedArticle.getId()))
                // url실행과 동시에 HTTP응답코드가 200번인지 확인
                .andExpect(status().isOk());
        // 결과 확인
        List<Article> articles = blogRepository.findAll();
        assertThat(articles).isEmpty();

    }
    @DisplayName("updateArticle: 블로그 글 수정에 성공한다.")
    @Test
    public void updateArticle() throws Exception{
        final String url = "/api/articles/{id}"; // 실행할 컨트롤러의 주소
        final String title = "title"; // 저장할 데이터
        final String content = "content"; // 저장할 데이터
        // 새로운 데이터 저장
        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        // 수정할 데이터를 작성
        final String newTitle = "new title";
        final String newContent = "new Content";
        UpdateArticleRequest request =
                new UpdateArticleRequest(newTitle, newContent);
        // 위에서 저장한 article 삭제 Controller실행
        final ResultActions resultActions = mockMvc.perform(
                        // url에 있는 {id}에 값을 설정
                        put(url,savedArticle.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(request)));
                // url실행과 동시에 HTTP응답코드가 200번인지 확인
        // 결과 확인
        resultActions.andExpect(status().isOk());
        Article article = blogRepository.findById(savedArticle.getId()).get();
        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }
}