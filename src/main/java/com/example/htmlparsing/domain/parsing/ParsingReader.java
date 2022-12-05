package com.example.htmlparsing.domain.parsing;

import com.example.htmlparsing.common.ConvertType;

public interface ParsingReader {
    Parsing getParsing(String url, ConvertType convertType,int invide);
    Boolean exitParsing(String url, ConvertType convertType,int invide);
}
