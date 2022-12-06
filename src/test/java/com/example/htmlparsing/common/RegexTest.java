package com.example.htmlparsing.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexTest {

    @Test
    void tagRegex() {
        String testHtml="<tag>안녕하세요a1b2c3d4</tag><tag>안녕하세요a1b2c3d4</tag><tag>안녕하세요a1b2c3d4</tag>";
        String res=Regex.tagRegex(testHtml);
        assertEquals("a1b2c3d4a1b2c3d4a1b2c3d4",res);
    }

    @Test
    void textRegex() {
        String testHtml="<tag>안녕하세요a1b2c3d4</tag><tag>안녕하세요a1b2c3d4</tag><tag>안녕하세요a1b2c3d4</tag>";
        String res=Regex.textRegex(testHtml);
        assertEquals("taga1b2c3d4tagtaga1b2c3d4tagtaga1b2c3d4tag",res);
    }
}