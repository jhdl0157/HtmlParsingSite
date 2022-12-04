package com.example.htmlparsing.domain.parsing;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ParsingServiceImplTest {
    @Mock
    JsoupExecutor jsoupExecutor;

    @Test
    void asdasd() {
        ParsingServiceImpl parsingService=new ParsingServiceImpl(jsoupExecutor );
        String html="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<script ....>" +
                " alert('hi')"+
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
}