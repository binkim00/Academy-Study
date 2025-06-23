package org.zerock.testproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {


    @Autowired
    protected MockMvc mockMvc;


    @Autowired
    private WebApplicationContext context;

    //    JSON : 웹에서 통신에 사용하는 데이터 형식
    //            [ ] : 배열, 여러가지 데이터를 넣을때 사용
    //    { } : 객체, key:value로 이루어진 데이터
    // [
    //    {"id":"1", "name":"홍길동"},
    //    {"id":"2", "name":"이순신"},
    //    {"id":"3", "name":"남가람"}
    // ]

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @DisplayName("quiz(): GET /quiz?code=1 이면 응답 코드는 201, 응답 본문은 Created!를 리턴한다.")
    @Test
    public void getQuiz1() throws Exception {
        // given
        // uri : controller에서 실행할 주소 설정
        final String url = "/quiz";


        // when
        // Controller를 실행하기 위한 코드
        // perform(get방식(주소).param(파라미터키, 파라미터값))
        final ResultActions result = mockMvc.perform(get(url)
                .param("code", "1")
        );


        // then
        // Controller의 실행결과 저장되는 result
        // 응답 결과의 status가 201이고 돌려준 문장이 Created!이면 성공
        result
                .andExpect(status().isCreated())
                .andExpect(content().string("Created!"));
    }


    @DisplayName("quiz(): GET /quiz?code=2 이면 응답 코드는 400, 응답 본문은 Bad Request!를 리턴한다.")
    @Test
    public void getQuiz2() throws Exception {
        // given
        final String url = "/quiz";


        // when
        final ResultActions result = mockMvc.perform(get(url)
                .param("code", "2")
        );


        // then
        result
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request!"));
    }


    @DisplayName("quiz(): POST /quiz?code=1 이면 응답 코드는 403, 응답 본문은 Forbidden!를 리턴한다.")
    @Test
    public void postQuiz1() throws Exception {
        // given
        final String url = "/quiz";


        // when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(1)))
        );


        // then
        result
                .andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden!"));
    }


    @DisplayName("quiz(): POST /quiz?code=13 이면 응답 코드는 200, 응답 본문은 OK!를 리턴한다.")
    @Test
    public void postQuiz13() throws Exception {
        // given
        final String url = "/quiz";


        // when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(13)))
        );


        // then
        result
                .andExpect(status().isOk())
                .andExpect(content().string("OK!"));
    }
}
