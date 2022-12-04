package com.example.htmlparsing.infrastructure;

import com.example.htmlparsing.common.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsoupExecutorImplTest {

    @Test
    void URL로_HTML가져오는_테스트() {
        String TARGET_URL="https://naver.com";
        JsoupExecutorImpl jsoupExecutor=new JsoupExecutorImpl();
        String html=jsoupExecutor.parseUrl(TARGET_URL);
        Assertions.assertNotNull(html);
    }

    @Test
    void 잘못된_URL로_HTML_가져오기(){
        String TARGET_URL="htt://naver.com";
        Assertions.assertThrows(EntityNotFoundException.class, () ->{
            JsoupExecutorImpl jsoupExecutor=new JsoupExecutorImpl();
            jsoupExecutor.parseUrl(TARGET_URL);
        });
    }
}