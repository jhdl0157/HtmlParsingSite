package com.example.htmlparsing.domain.parsing;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParsingServiceImplTest {
    @Mock
    JsoupExecutor jsoupExecutor;

    ParsingServiceImpl parsingService=new ParsingServiceImpl(jsoupExecutor );
    @Test
    void 태그제거_테스트() {
        String html="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<script ....>" +
                " alert(' hi')"+
                "</script>" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title??!!@1234</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "hi" +
                "</body>\n" +
                "</html>";
        String asd=parsingService.convertAccordingType(html,ConvertType.TAG);
        System.out.println(asd);
    }
    @Test
    void 텍스트_출력_테스트() {
        String html="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<script ....>" +
                " alert(' hi')"+
                "</script>" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title??!!@1234</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "hi" +
                "</body>\n" +
                "</html>";
        String asd=parsingService.convertAccordingType(html,ConvertType.TEXT);
        System.out.println(asd);
    }

    @Test
    void 텍스트를_숫자와_영어로_분리하기(){
        String html="DOCTYPEhtmlhtmllangenscriptalerthiscriptheadmetacharsetUTF8titlAAABBBCCCeTitle1234titleheadbodyhibodyhtml";
        ParsingResult parsingResult=parsingService.divideTextAndNumbers(html);
        System.out.println(parsingResult.getEnglish());
        System.out.println(parsingResult.getNumbers());
        String expect="AAAaaaaaaBBBbbCCCCcccDddddEeeeeeeeeeFghhhhhhhhiiiiiiillllllllmmmmnnOooPpprrrrsssTTTtttttttttttttUYyy";
        String numExpect="81234";
        assertEquals(expect,parsingResult.getEnglish());
        assertEquals(numExpect,parsingResult.getNumbers());
    }
}