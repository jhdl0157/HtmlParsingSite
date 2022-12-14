package com.example.htmlparsing.application;

import com.example.htmlparsing.common.ErrorCode;
import com.example.htmlparsing.common.exception.BaseException;
import com.example.htmlparsing.domain.parsing.ParsingService;
import com.example.htmlparsing.interfaces.parsing.ParsingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 도메인 레이어와 그외 다른 서비스를 합쳐서 하나의 서비스로 만든다 그래서 Facade 라는 이름으로 사용한다.
 * 다른 애플리케이션 계층과의 상호 작용을 한다
 * ex) url을 찾고 알람을 보내준다. url 서비스와 알람도메인을 합쳐준다.
 */
@Component
@RequiredArgsConstructor
public class ParsingFacade {
    private final ParsingService parsingService;
    public ParsingDto.ParserResponse getCombineText(ParsingDto.ParserRequest parserRequest){
        if(parserRequest.getUrl().isEmpty() && parserRequest.getType().isEmpty() && Objects.isNull(parserRequest.getInvide())) throw new BaseException("입력 값에 문제가 있습니다.", ErrorCode.COMMON_INVALID_PARAMETER);
        return parsingService.getQuotientAndRemainder(parserRequest.getUrl(),parserRequest.getType(),parserRequest.getInvide());
    }
}
