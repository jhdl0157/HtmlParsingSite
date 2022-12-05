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
        if(Objects.isNull(parsingResult)) throw new EntityNotFoundException();
        StringBuffer sb=new StringBuffer();
        String english=parsingResult.getEnglish();
        String numbers=parsingResult.getNumbers();
        if(english.isEmpty()) return sb.append(numbers).toString();
        if(numbers.isEmpty()) return sb.append(english).toString();
        for(int i=1;i<=english.length();i++){
            if((i>1) && (numbers.length()>=i-1)){
                sb.append(numbers.charAt(i-2));
            }
            sb.append(english.charAt(i-1));
        }
        if(english.length()<=numbers.length()){
            int a=numbers.length()-english.length();
            sb.append(numbers.substring(numbers.length()-a-1));
        }
        return sb.toString();
    };
public Stream<String> getSplitStream(String text) {
    return Pattern.compile("").splitAsStream(text).sorted();
}
}
