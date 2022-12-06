package com.example.htmlparsing.domain.parsing;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ParsingResult {
    private String english;
    private String numbers;
    @Builder
    public ParsingResult(String english,String numbers){
        this.english=english;
        this.numbers=numbers;
    }
}
