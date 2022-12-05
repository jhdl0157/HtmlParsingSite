package com.example.htmlparsing.domain.parsing;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParsingServiceImplTest {
    @Mock
    JsoupExecutor jsoupExecutor;

    ParsingServiceImpl parsingService=new ParsingServiceImpl(jsoupExecutor);
    private final String HTML_TEST ="<!DOCTYPE html>\n" +
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
    @Test
    void 태그제거_테스트() {
        String deleteTag=parsingService.convertAccordingType(HTML_TEST,ConvertType.TAG);
        String expect="alerthiTitle1234hi";
        assertEquals(expect,deleteTag);
    }
    @Test
    void 텍스트_출력_테스트() {
        String text=parsingService.convertAccordingType(HTML_TEST,ConvertType.TEXT);
        String expect="DOCTYPEhtmlhtmllangenscriptalerthiscriptheadmetacharsetUTF8titleTitle1234titleheadbodyhibodyhtml";
        assertEquals(expect,text);
    }

    @Test
    void 텍스트를_숫자와_영어로_분리하기(){
        String html="DOCTYPEhtmlhtmllangenscriptalerthiscriptheadmetacharsetUTF8titlAAABBBCCCeTitle1234titleheadbodyhibodyhtml";
        ParsingResult parsingResult=parsingService.divideTextAndNumbers(html);
        System.out.println("영어만 출력 : "+parsingResult.getEnglish());
        System.out.println("숫자만 출력 : "+parsingResult.getNumbers());
        String expect="AAAaaaaaaBBBbbCCCCcccDddddEeeeeeeeeeFghhhhhhhhiiiiiiillllllllmmmmnnOooPpprrrrsssTTTtttttttttttttUYyy";
        String numExpect="12348";
        assertEquals(expect,parsingResult.getEnglish());
        assertEquals(numExpect,parsingResult.getNumbers());
    }
    @Test
    void 합치기테스트_영어갯수가_숫자갯수보다_많을_경우(){
        String result=parsingService.combineAlphaAndNumber(new ParsingResult("AaBbCcDdEeFfGg","1234"));
        String expect="A1a2B3b4CcDdEeFfGg";
        assertEquals(expect,result);
    }
    @Test
    void 합치기테스트_영어갯수가_숫자갯수보다_같을_경우(){
        String result=parsingService.combineAlphaAndNumber(new ParsingResult("AaBbCcDdEe","1122334455"));
        String expect="A1a1B2b2C3c3D4d4E5e5";
        assertEquals(expect,result);
    }
    @Test
    void 합치기테스트_영어갯수가_숫자갯수보다_적을_경우(){
        String result=parsingService.combineAlphaAndNumber(new ParsingResult("AaBbCcDdEe","11223344556677"));
        String expect="A1a1B2b2C3c3D4d4E5e56677";
        assertEquals(expect,result);
    }
    @Test
    void 합치기테스트_숫자만_존재할_경우(){
        String result=parsingService.combineAlphaAndNumber(new ParsingResult("","11223344556677"));
        String expect="11223344556677";
        assertEquals(expect,result);
    }
    @Test
    void 합치기테스트_영어만_존재할_경우(){
        String result=parsingService.combineAlphaAndNumber(new ParsingResult("AAABBBBBCCCCDDD",""));
        String expect="AAABBBBBCCCCDDD";
        assertEquals(expect,result);
    }
}