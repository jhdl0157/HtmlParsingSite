package com.example.htmlparsing.infrastructure;

import com.example.htmlparsing.domain.JsoupExecutor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

@Component
public class JsoupExecutorImpl implements JsoupExecutor {
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
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
