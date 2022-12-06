package com.example.htmlparsing.interfaces.parsing;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
/**
 * inner class를 사용하여 응집도를 높임
 *
 */
public class ParsingDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class ParserRequest{
        @NotEmpty(message = "url은 필수값입니다")
        @URL
        private String url;

        @NotEmpty(message = "type은 필수값입니다")
        private String type;

        @Min(value = 1,message = "1이상의 값을 적어주세요")
        private int invide;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class ParserResponse {
        private String quotient;
        private String remainder;
    }
}
