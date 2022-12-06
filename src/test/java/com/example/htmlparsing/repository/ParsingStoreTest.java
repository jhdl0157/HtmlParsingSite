package com.example.htmlparsing.repository;

import com.example.htmlparsing.common.ConvertType;
import com.example.htmlparsing.domain.parsing.Parsing;
import com.example.htmlparsing.infrastructure.ParsingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class ParsingStoreTest {

    @Autowired
    private ParsingRepository parsingRepository;

    @Test
    @DisplayName("Parsing 값을 잘 저장 하는지")
    void t1(){
        Parsing parsing=new Parsing("test.com", ConvertType.TAG,3,"testQuot","testRemiain");
        Parsing saveParsing=parsingRepository.save(parsing);

        Assertions.assertThat(parsing).isSameAs(saveParsing);
        Assertions.assertThat(parsing.getUrl()).isEqualTo(saveParsing.getUrl());
        Assertions.assertThat(parsing.getId()).isNotNull();
    }
    @Test
    @DisplayName("find기능이 제대로 동작하는지 확인")
    void t2(){
        Parsing parsing=new Parsing("test.com", ConvertType.TAG,3,"testQuot","testRemiain");
        parsingRepository.save(parsing);

        Parsing findParsing=parsingRepository.findByUrlAndConvertTypeAndInvide("test.com",ConvertType.TAG,3);
        Assertions.assertThat(parsingRepository.count()).isEqualTo(1);
        Assertions.assertThat(findParsing.getUrl()).isEqualTo("test.com");
        Assertions.assertThat(findParsing.getInvide()).isEqualTo(3);
        Assertions.assertThat(findParsing.getQuotient()).isEqualTo("testQuot");
        Assertions.assertThat(findParsing.getRemainder()).isEqualTo("testRemiain");
    }

}
