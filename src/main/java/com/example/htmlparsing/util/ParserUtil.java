package com.example.htmlparsing.util;

import com.example.htmlparsing.common.ConvertType;
import com.example.htmlparsing.common.ErrorCode;
import com.example.htmlparsing.common.Regex;
import com.example.htmlparsing.common.exception.BaseException;
import com.example.htmlparsing.domain.parsing.ParsingResult;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Slf4j
public class ParserUtil {
    /**
     * 입력한 타입에 따라 정규식 실행하기
     *
     * @param html htmlText
     * @param convertType 태그제거 or text가져오기
     */
    public static String convertAccordingType(String html, ConvertType convertType){
        switch (convertType){
            case TAG: {
                return Regex.tagRegex(html);
            }
            case TEXT:{
                return Regex.textRegex(html);
            }
        }
        throw new BaseException("잘못된 타입 값입니다.", ErrorCode.COMMON_INVALID_PARAMETER);
    }

    /**
     * 타입으로 필터링된 text를 숫자와 영어로 나눠주고 조건에 따른 정렬을 하고 객체 생성
     * (영어 : 대문자 -> 소문자 AaBb.. , 숫자 :  0123...)
     * @param convertHtml convertHtml
     */
    public static  ParsingResult divideTextAndNumbers(String convertHtml){
        String english= convertHtml.replaceAll(Regex.DELETE_NUMBER.getRegexPattern(),"");
        var streamString=getSplitStream(english);
        String sortedEnglish=streamString.sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.joining());

        String num=convertHtml.replaceAll(Regex.EXPECT_NUMBER.getRegexPattern(),"");
        var streamNumbers=getSplitStream(num);
        String sortedNumbers=streamNumbers.sorted().collect(Collectors.joining());
        log.info("[정렬된 문자] : {}",sortedEnglish);
        log.info("[정렬된 숫자] : {}",sortedNumbers);
        return ParsingResult.builder()
                .english(sortedEnglish)
                .numbers(sortedNumbers)
                .build();
    };

    /**
     * 숫자와 영어를 교차로 하나의 문자열 생성
     * (영어가 먼저 와야한다. ex) 영어 숫자 영어 숫자...)
     * @param parsingResult  영어와 숫자를 가진 객체
     */
    public static String combineAlphaAndNumber(ParsingResult parsingResult){
        if(Objects.isNull(parsingResult)) throw new BaseException(ErrorCode.COMMON_SYSTEM_ERROR);
        StringBuffer sb=new StringBuffer();
        String english=parsingResult.getEnglish();
        String numbers=parsingResult.getNumbers();
        int index = english.length() >= numbers.length() ? english.length() : numbers.length();
        for(int i=0; i<index; i++) {
            if(i<english.length()) sb.append(english.charAt(i));
            if(i<numbers.length()) sb.append(numbers.charAt(i));
        }
        return sb.toString();
    };

    /**
     * 문자열을 스트림으로 바꿔 반환
     * @param text  Stream sort를 사용하기 위한 text
     */
    private static Stream<String> getSplitStream(String text) {
        return Pattern.compile("").splitAsStream(text).sorted();
    }
}
