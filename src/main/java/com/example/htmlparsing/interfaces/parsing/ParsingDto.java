package com.example.htmlparsing.interfaces.parsing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ParsingDto {
    @Getter
    @Setter
    public static class ParserRequest {
        @NotEmpty(message = "url은 필수값입니다")
        @URL
        private String url;

        @NotEmpty(message = "type은 필수값입니다")
        private String type;

        @NotEmpty(message = "invide은 필수값입니다")
        @Min(1)
        private int invide;
    }
}
