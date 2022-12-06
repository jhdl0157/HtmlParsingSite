package com.example.htmlparsing.infrastructure;

import com.example.htmlparsing.common.ErrorCode;
import com.example.htmlparsing.common.exception.BaseException;
import com.example.htmlparsing.common.exception.UrlException;
import com.example.htmlparsing.domain.parsing.JsoupExecutor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

/**
 * 다른 html을 가져오는 서비스가 있다고 하면 @Order 어노테이션을 활용하여 손쉽게 순서를 바꿀수 있다.
 */
@Component
public class JsoupExecutorImpl implements JsoupExecutor {

    /**
     * url에따른 html 반환
     *
     * @param url 가져올 url
     * @return html 데이터를 반환
     */
    @Override
    public String parseUrl(String url) {
        try {
            Connection.Response response = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .timeout(60000)
                    .execute();
            Document document = response.parse();
            return document.html();
        } catch (IllegalArgumentException | UnknownHostException e) {
            throw new UrlException("잘못된 url 값입니다.");
        } catch (Exception e) {
            throw new BaseException(ErrorCode.COMMON_SYSTEM_ERROR);
        }
    }
}
