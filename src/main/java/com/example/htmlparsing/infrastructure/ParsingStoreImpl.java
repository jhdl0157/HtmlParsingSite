package com.example.htmlparsing.infrastructure;

import com.example.htmlparsing.common.ErrorCode;
import com.example.htmlparsing.common.exception.BaseException;
import com.example.htmlparsing.domain.parsing.Parsing;
import com.example.htmlparsing.domain.parsing.ParsingStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
@RequiredArgsConstructor
public class ParsingStoreImpl implements ParsingStore {
    private final ParsingRepository parsingRepository;
    @Override
    public Parsing store(Parsing initParsing) {
        if(StringUtils.isEmpty(initParsing.getUrl())) throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        if(StringUtils.isEmpty(initParsing.getConvertType().getValue())) throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        if(initParsing.getInvide()==null) throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        if(StringUtils.isEmpty(initParsing.getQuotient())) throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        if(StringUtils.isEmpty(initParsing.getRemainder())) throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        return parsingRepository.save(initParsing);
    }
}
