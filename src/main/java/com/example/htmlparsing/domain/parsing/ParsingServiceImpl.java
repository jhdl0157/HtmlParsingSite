package com.example.htmlparsing.domain.parsing;


import com.example.htmlparsing.common.ConvertType;
import com.example.htmlparsing.common.ErrorCode;
import com.example.htmlparsing.common.exception.BaseException;
import com.example.htmlparsing.interfaces.parsing.ParsingDto;
import com.example.htmlparsing.util.ParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
public class ParsingServiceImpl implements ParsingService {
    private final JsoupExecutor jsoupExecutor;
    private final ParsingReader parsingReader;
    private final ParsingStore parsingStore;
    @Override
    @Transactional
    public ParsingDto.ParserResponse getQuotientAndRemainder(final String url, final String type, final int invide) {
        if(url.isEmpty() && type.isEmpty() && Objects.isNull(invide)) throw new BaseException("입력 값에 문제가 있습니다.", ErrorCode.COMMON_INVALID_PARAMETER);
        if(parsingReader.exitParsing(url, ConvertType.validType(type),invide)){
            var parsing=parsingReader.getParsing(url, ConvertType.validType(type),invide);
            return ParsingDto.ParserResponse
                    .builder()
                    .remainder(parsing.getRemainder())
                    .quotient(parsing.getQuotient())
                    .build();
        }
        val html=getHtml(url);
        val StringToConvertType= ConvertType.validType(type);
        val convertHtml= ParserUtil.convertAccordingType(html,StringToConvertType);
        val parsingData=ParserUtil.divideTextAndNumbers(convertHtml);
        val combineEnglishAndNumbers=ParserUtil.combineAlphaAndNumber(parsingData);
        val quotient=combineEnglishAndNumbers.substring(0,(combineEnglishAndNumbers.length() / invide) * invide);
        val remainder=combineEnglishAndNumbers.substring(combineEnglishAndNumbers.length()-combineEnglishAndNumbers.length()%invide);
        var newParsing= Parsing.builder()
                .url(url)
                .convertType(StringToConvertType)
                .invide(invide)
                .quotient(quotient)
                .remainder(remainder).build();
        parsingStore.store(newParsing);
        return ParsingDto.ParserResponse
                .builder()
                .quotient(quotient)
                .remainder(remainder)
                .build();
    }
    private String getHtml(String url){
        return jsoupExecutor.parseUrl(url);
    };
}
