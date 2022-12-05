package com.example.htmlparsing.domain.parsing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Regex {
    WHITE_SPACE("\\p{Space}+"),DELETE_TAG("<.*?>"),
    DELETE_EXCEPT_ALPHA_AND_NUMBER("[^\\p{Alnum}]+"),DELETE_NUMBER("\\p{Digit}+")
    ,EXPECT_NUMBER("[^\\p{Digit}]+");
    private final String regexPattern;

    public static String tagRegex(String html){
        String deleteTag=html.replaceAll(Regex.DELETE_TAG.getRegexPattern(), "");
        String deleteMsg=deleteTag.replaceAll(Regex.DELETE_EXCEPT_ALPHA_AND_NUMBER.getRegexPattern(), "");
        return deleteMsg;
    }
    public static String textRegex(String html){
        String deleteMsg=html.replaceAll(Regex.DELETE_EXCEPT_ALPHA_AND_NUMBER.getRegexPattern(), "");
        String deleteWhiteSpace=deleteMsg.replaceAll(Regex.WHITE_SPACE.getRegexPattern(),"");
        return deleteWhiteSpace;
    }
}
