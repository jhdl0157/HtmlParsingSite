package com.example.htmlparsing.domain.parsing;

import com.example.htmlparsing.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                String deleteTag=html.replaceAll(Regex.DELETE_TAG.getRegexPattern(), "");
                String deleteMsg=deleteTag.replaceAll(Regex.DELETE_EXCEPT_ALPHA_AND_NUMBER.getRegexPattern(), "");
                return deleteMsg;
            }
        }
        throw new BaseException();
    }
//    private Map<String,Integer> divideTextAndNumbers(String ConvertHtml){
//
//    };
//    private String mixAlphaAndNumber(String alpha,String numbers){
//
//    };
}
