package com.example.htmlparsing.service;

import com.example.htmlparsing.domain.parsing.ParsingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@ActiveProfiles("test")
public class ParsingServiceTest {
    @Autowired
    private ParsingService parsingService;

    @Test
    @DisplayName("값을 넣고 결과값 확인")
    void t1(){
        var result=parsingService.getQuotientAndRemainder("https://terms.naver.com/entry.naver?docId=962395&cid=48181&categoryId=48261","HTML 태그 제거",4);
        assertEquals("zz",result.getRemainder());
    }
}
