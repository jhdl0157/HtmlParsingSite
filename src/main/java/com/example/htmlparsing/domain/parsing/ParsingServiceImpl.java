package com.example.htmlparsing.domain.parsing;

import com.example.htmlparsing.common.exception.BaseException;
import com.example.htmlparsing.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ParsingServiceImpl implements ParsingService {
    private final JsoupExecutor jsoupExecutor;
    @Override
    public void getQuotientAndRemainder(String url,String type,int invide) {
        var html=getHtml(url);
        var StringToConvertType=ConvertType.validType(type);
        var convertHtml=convertAccordingType(html,StringToConvertType);
        var parsingData=divideTextAndNumbers(convertHtml);

        System.out.println(convertHtml);
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
        String al=streamString.sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.joining());

        String num=convertHtml.replaceAll(Regex.EXPECT_NUMBER.getRegexPattern(),"");
        var as=getSplitStream(num);
        String res=as.sorted().collect(Collectors.joining());
        return ParsingResult.builder()
                .english(al)
                .numbers(res)
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
