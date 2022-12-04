package com.example.htmlparsing.domain.parsing;

import com.example.htmlparsing.common.exception.NotMatchTypeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConvertType {
    TAG("HTML 태그 제거"),TEXT("TEXT 전체");
    private final String value;
    public static ConvertType validType(String type) {
        if (type.isEmpty()) {
            throw new NotMatchTypeException("타입이 없습니다.");
        }
        for (ConvertType convertType : ConvertType.values()) {
            if (convertType.getValue().equals(type)) {
                return convertType;
            }
        }
        throw new NotMatchTypeException("존재하지 않은 타입의 종류 입니다.");
    }
}
