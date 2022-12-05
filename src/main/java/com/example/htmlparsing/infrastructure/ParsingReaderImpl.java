package com.example.htmlparsing.infrastructure;

import com.example.htmlparsing.common.ConvertType;
import com.example.htmlparsing.domain.parsing.Parsing;
import com.example.htmlparsing.domain.parsing.ParsingReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParsingReaderImpl implements ParsingReader {
    private final ParsingRepository parsingRepository;
    @Override
    public Parsing getParsing(String url, ConvertType convertType, int invide) {
        return parsingRepository.findByUrlAndConvertTypeAndInvide(url, convertType, invide);
    }

    @Override
    public Boolean exitParsing(String url, ConvertType convertType, int invide) {
        return parsingRepository.existsByUrlAndConvertTypeAndInvide(url, convertType, invide);
    }
}
