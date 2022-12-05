package com.example.htmlparsing.domain.parsing;

import com.example.htmlparsing.common.exception.BaseException;
import com.example.htmlparsing.common.exception.EntityNotFoundException;
import com.example.htmlparsing.interfaces.parsing.ParsingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParsingServiceImpl implements ParsingService {
    private final JsoupExecutor jsoupExecutor;
    @Override
    public ParsingDto.ParserResponse getQuotientAndRemainder(String url, String type, int invide) {
        val html=getHtml(url);
        val StringToConvertType=ConvertType.validType(type);
        val convertHtml=convertAccordingType(html,StringToConvertType);
        val parsingData=divideTextAndNumbers(convertHtml);
        val combineEnglishAndNumbers=combineAlphaAndNumber(parsingData);
        val quotient=combineEnglishAndNumbers.substring(0,(combineEnglishAndNumbers.length() / invide) * invide);
        val remainder=combineEnglishAndNumbers.substring(combineEnglishAndNumbers.length()-combineEnglishAndNumbers.length()%invide);
        return ParsingDto.ParserResponse
                .builder()
                .quotient(quotient)
                .remainder(remainder)
                .build();
    }
    private String getHtml(String url){
        return jsoupExecutor.parseUrl(url);
    };
    public String convertAccordingType(String html,ConvertType convertType){
        switch (convertType){
            case TAG: {
                return Regex.tagRegex(html);
            }
            case TEXT:{
                return Regex.textRegex(html);
            }
        }
        throw new BaseException();
    }
    public ParsingResult divideTextAndNumbers(String convertHtml){
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
    public String combineAlphaAndNumber(ParsingResult parsingResult){
        //TODO 에러 던지는 클래스 만들기
        if(Objects.isNull(parsingResult)) throw new EntityNotFoundException();
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
public Stream<String> getSplitStream(String text) {
    return Pattern.compile("").splitAsStream(text).sorted();
}
}
