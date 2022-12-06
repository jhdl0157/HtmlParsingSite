package com.example.htmlparsing.domain.parsing;

import com.example.htmlparsing.common.ConvertType;
/**
 * 읽기 기능을 위한 Reader 클래스
 */
public interface ParsingReader {
    Parsing getParsing(String url, ConvertType convertType,int invide);
    Boolean exitParsing(String url, ConvertType convertType,int invide);
}
