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
}
