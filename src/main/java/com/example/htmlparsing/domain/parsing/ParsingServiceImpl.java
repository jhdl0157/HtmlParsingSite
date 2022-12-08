package com.example.htmlparsing.domain.parsing;


import com.example.htmlparsing.common.ConvertType;
import com.example.htmlparsing.interfaces.parsing.ParsingDto;
import com.example.htmlparsing.util.ParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ParsingServiceImpl implements ParsingService {
    private final JsoupExecutor jsoupExecutor;
    @Override
    public ParsingDto.ParserResponse getQuotientAndRemainder(final String url, final String type, final int invide) {
        val html= download_page(url);
        val StringToConvertType= ConvertType.validType(type);
        val convertHtml= ParserUtil.convertAccordingType(html,StringToConvertType);
        val parsingData=ParserUtil.divideTextAndNumbers(convertHtml);
        val combineEnglishAndNumbers=ParserUtil.combineAlphaAndNumber(parsingData);
        val quotient=ParserUtil.getQuotient(combineEnglishAndNumbers,invide);
        val remainder=ParserUtil.getReminder(combineEnglishAndNumbers,invide);
        return ParsingDto.ParserResponse
                .builder()
                .quotient(quotient)
                .remainder(remainder)
                .build();
    }
    private String download_page(String url){
        return jsoupExecutor.parseUrl(url);
    };
}
