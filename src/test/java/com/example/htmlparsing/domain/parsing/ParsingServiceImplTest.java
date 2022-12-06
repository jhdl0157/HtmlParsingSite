package com.example.htmlparsing.domain.parsing;

import com.example.htmlparsing.common.ConvertType;
import com.example.htmlparsing.util.ParserUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParsingServiceImplTest {
    private final String HTML_TEST ="<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<script ....>" +
            " alert(' hi')"+
            "</script>" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>TiAAABBBtle??!!@1234</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "hiCCCC23333333344444444444444444444455555555555555555555555555566666666666666666DDDD" +
            "</body>\n" +
            "</html>";

    private final String DELETE_TEXT="<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>deleteText1234</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "</body>\n" +
            "</html>";

    private final String DELETE_TAG="<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Title@#$$$$!@!!!!@123asdvzczxasd1234</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
    @Test
    void 태그제거_테스트() {
        String deleteTag= ParserUtil.convertAccordingType(HTML_TEST, ConvertType.TAG);
        String expect="alerthiTiAAABBBtle1234hiCCCC23333333344444444444444444444455555555555555555555555555566666666666666666DDDD";
        assertEquals(expect,deleteTag);
    }
    @Test
    void 텍스트_출력_테스트() {
        String text=ParserUtil.convertAccordingType(HTML_TEST,ConvertType.TEXT);
        String expect="DOCTYPEhtmlhtmllangenscriptalerthiscriptheadmetacharsetUTF8titleTiAAABBBtle1234titleheadbodyhiCCCC23333333344444444444444444444455555555555555555555555555566666666666666666DDDDbodyhtml";
        assertEquals(expect,text);
    }

    @Test
    void 텍스트를_숫자와_영어로_분리하기(){
        String html="DOCTYPEhtmlhtmllangenscriptalerthiscriptheadmetacharsetUTF8titlAAABBBCCCeTitle1234titleheadbodyhibodyhtml";
        ParsingResult parsingResult=ParserUtil.divideTextAndNumbers(html);
        System.out.println("영어만 출력 : "+parsingResult.getEnglish());
        System.out.println("숫자만 출력 : "+parsingResult.getNumbers());
        String expect="AAAaaaaaaBBBbbCCCCcccDddddEeeeeeeeeeFghhhhhhhhiiiiiiillllllllmmmmnnOooPpprrrrsssTTTtttttttttttttUYyy";
        String numExpect="12348";
        assertEquals(expect,parsingResult.getEnglish());
        assertEquals(numExpect,parsingResult.getNumbers());
    }
    @Test
    void 합치기테스트_영어갯수가_숫자갯수보다_많을_경우(){
        String result=ParserUtil.combineAlphaAndNumber(new ParsingResult("AaBbCcDdEeFfGg","1234"));
        String expect="A1a2B3b4CcDdEeFfGg";
        assertEquals(expect,result);
    }
    @Test
    void 합치기테스트_영어갯수가_숫자갯수보다_같을_경우(){
        String result=ParserUtil.combineAlphaAndNumber(new ParsingResult("AaBbCcDdEe","1122334455"));
        String expect="A1a1B2b2C3c3D4d4E5e5";
        assertEquals(expect,result);
    }
    @Test
    void 합치기테스트_영어갯수가_숫자갯수보다_적을_경우(){
        String result=ParserUtil.combineAlphaAndNumber(new ParsingResult("AaBbCcDdEe","11223344556677"));
        String expect="A1a1B2b2C3c3D4d4E5e56677";
        assertEquals(expect,result);
    }
    @Test
    void 합치기테스트_숫자만_존재할_경우(){
        String result=ParserUtil.combineAlphaAndNumber(new ParsingResult("","11223344556677"));
        String expect="11223344556677";
        assertEquals(expect,result);
    }
    @Test
    void 합치기테스트_영어만_존재할_경우(){
        String result=ParserUtil.combineAlphaAndNumber(new ParsingResult("AAABBBBBCCCCDDD",""));
        String expect="AAABBBBBCCCCDDD";
        assertEquals(expect,result);
    }

    @Test
    void 종합하여_테스트해보기(){
        String expect="A1A2A2a3a3a3a3a3a3B3B3B3b4b4C4C4C4C4C4c4c4c4D4D4D4D4D4d4d4d4d4E4e4e4e5e5e5e5e5e5e5F5g5h5h5h5h5h5h5h5h5i5i5i5i5i5i5i5l5l5l5l6l6l6l6l6m6m6m6m6n6n6O6o6o6P6p6p6r8rrrsssTTTtttttttttttttUYyy";
        String deleteTag= ParserUtil.convertAccordingType(HTML_TEST, ConvertType.TEXT);
        System.out.println(deleteTag);
        ParsingResult parsingResult=ParserUtil.divideTextAndNumbers(deleteTag);
        System.out.println(parsingResult.getEnglish());
        System.out.println(parsingResult.getNumbers());
        String result=ParserUtil.combineAlphaAndNumber(parsingResult);
        System.out.println(result);
        String quotient=ParserUtil.getQuotient(result,4);
        String remainder=ParserUtil.getReminder(result,4);
        System.out.println("quo : "+quotient);
        System.out.println("remainder : "+ remainder);
        assertEquals(expect,quotient);
        assertEquals("",remainder);
    }
}