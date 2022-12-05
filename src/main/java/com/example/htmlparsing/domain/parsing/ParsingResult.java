package com.example.htmlparsing.domain.parsing;

import com.example.htmlparsing.common.exception.BaseException;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

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
