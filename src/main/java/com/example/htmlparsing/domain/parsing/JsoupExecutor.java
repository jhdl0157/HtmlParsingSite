package com.example.htmlparsing.domain.parsing;
/**
 * Service라는 이름을 많이 가져가면 다른 사람이 서비스 로직을 찾기 번거롭기에 Excutor라는 이름을 지어줬다.
 * 인터페이스를 사용함으로써 ParsingService는 Jsoup서비스에 의존적이지 않다. 추후 Jsoup이 아닌 다른 html을 가져와야하는 상황에서도
 * 변경없이 사용이 가능하다. (DIP원칙 적용)
 */
public interface JsoupExecutor {
    String parseUrl(String url);

}
