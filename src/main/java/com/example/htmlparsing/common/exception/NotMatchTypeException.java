package com.example.htmlparsing.common.exception;

import com.example.htmlparsing.common.ErrorCode;

public class NotMatchTypeException extends BaseException{
    public NotMatchTypeException(){
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }
    public NotMatchTypeException(String msg){
        super(msg,ErrorCode.COMMON_INVALID_PARAMETER);
    }
}
