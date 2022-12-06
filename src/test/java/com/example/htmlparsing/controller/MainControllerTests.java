package com.example.htmlparsing.controller;


import com.example.htmlparsing.interfaces.MainController;
import com.example.htmlparsing.interfaces.parsing.ParserApiController;
import com.example.htmlparsing.interfaces.parsing.ParsingDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MainControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("메인 화면 폼 가져오기")
    void t1() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/"))
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(MainController.class))
                .andExpect(handler().methodName("home"));
    }

    @Test
    @DisplayName("html 파싱 테스트")
    void t2() throws Exception{
        //GIVEN
        String content = objectMapper.writeValueAsString(new ParsingDto.ParserRequest("https://naver.com", "HTML 태그 제거",3));
        String resultParsingData="A0A0a0a0a0a0B0b0c0c0c0D0D0D1D1D1d1d1d1d1E1E1e1e1e1e1F2F2f2f2f2f2f3G3g3g3g4H4H5H5H6h6h6I7I7I7I7J8J8J8j8j9j9j9kllMMMMMMMMMMMMmmmmNNnnnOOoopQQQRRrSsTTttttttttuVWWwwwwwwXXxxYYyyyZZz";
        // WHEN
        ResultActions resultActions = mvc
                .perform(post("/api/v1/parsings")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(ParserApiController.class))
                .andExpect(handler().methodName("registerHtml"))
                .andExpect(jsonPath("result").value("SUCCESS"));
    }

    @Test
    @DisplayName("잘못된 URL_데이터 전송")
    void t3() throws Exception{
        //GIVEN
        String content = objectMapper.writeValueAsString(new ParsingDto.ParserRequest("jongwons.choi/spring-boot-security-lecture", "HTML 태그 제거",3));
        // WHEN
        ResultActions resultActions = mvc
                .perform(post("/api/v1/parsings")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is4xxClientError())
                .andExpect(handler().handlerType(ParserApiController.class))
                .andExpect(handler().methodName("registerHtml"))
                .andExpect(jsonPath("result").value("FAIL"))
                .andExpect(jsonPath("message").value("must be a valid URL"));
    }

    @Test
    @DisplayName("잘못된 type_데이터 전송")
    void t4() throws Exception{
        //GIVEN
        String content = objectMapper.writeValueAsString(new ParsingDto.ParserRequest("https://naver.com", "HTML 태그 추가",3));
        // WHEN
        ResultActions resultActions = mvc
                .perform(post("/api/v1/parsings")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is4xxClientError())
                .andExpect(handler().handlerType(ParserApiController.class))
                .andExpect(handler().methodName("registerHtml"))
                .andExpect(jsonPath("result").value("FAIL"))
                .andExpect(jsonPath("message").value("존재하지 않은 타입의 종류 입니다."));
    }

    @Test
    @DisplayName("0보다 작은 번들값 전송")
    void t5() throws Exception{
        //GIVEN
        String content = objectMapper.writeValueAsString(new ParsingDto.ParserRequest("https://naver.com", "HTML 태그 제거",0));
        // WHEN
        ResultActions resultActions = mvc
                .perform(post("/api/v1/parsings")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is4xxClientError())
                .andExpect(handler().handlerType(ParserApiController.class))
                .andExpect(handler().methodName("registerHtml"))
                .andExpect(jsonPath("result").value("FAIL"))
                .andExpect(jsonPath("message").value("1이상의 값을 적어주세요"));
    }
}
