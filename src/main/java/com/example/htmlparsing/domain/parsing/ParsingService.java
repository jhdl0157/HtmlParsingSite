package com.example.htmlparsing.domain.parsing;

import java.util.Map;

public interface ParsingService {
    //순서
    // 1. url로 텍스트를 가져온다.
    // 2. type에 따라 html 을 변환시킨다.
    // 3. 변환된 text에서 문자와 숫자를 나눈다.
    // 4. 숫자, 문자를 정렬한다.
    // 5. 교차 출력을 만든다
    // 6. number값에 따라서 문자를 나눈다.
    void getQuotientAndRemainder(String url,String type,int invide);
}
