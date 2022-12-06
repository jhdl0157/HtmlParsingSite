package com.example.htmlparsing.common.exception;

import com.example.htmlparsing.common.ErrorCode;

public class UrlException extends BaseException{
    public UrlException(){
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }
    public UrlException(String msg){
        super(msg,ErrorCode.COMMON_INVALID_PARAMETER);
    }
}
