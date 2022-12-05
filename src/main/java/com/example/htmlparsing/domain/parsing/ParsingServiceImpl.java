package com.example.htmlparsing.domain.parsing;

import com.example.htmlparsing.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
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
                .numbers(num)
                .build();
    };
//    private String combineAlphaAndNumber(String alpha,String numbers){
//
//    };
public Stream<String> getSplitStream(String text) {
    return Pattern.compile("").splitAsStream(text).sorted();
}
}
