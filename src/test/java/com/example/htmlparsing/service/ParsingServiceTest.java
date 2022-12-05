package com.example.htmlparsing.service;

import com.example.htmlparsing.domain.parsing.ParsingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@SpringBootTest()
@ActiveProfiles("test")
public class ParsingServiceTest {
    @Autowired
    private ParsingService parsingService;

    @Test
    @DisplayName("값을 넣고 결과값 확인")
    void t1(){
        var result=parsingService.getQuotientAndRemainder("https://google.com","HTML 태그 제거",10);

        assertTrue(result.getRemainder().length()<=10,"통과");
    }



}
