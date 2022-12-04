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
    @ToString
    public static class ParserRequest {
        @NotEmpty(message = "url 는 필수값입니다")
        @URL
        private String url;

        @NotEmpty(message = "type 는 필수값입니다")
        private String type;

        @NotEmpty(message = "invide 는 필수값입니다")
        @Min(1)
        private int invide;
    }

//    @Getter
//    @ToString
//    public static class ParserResponse {
//        private final String partnerToken;
//        private final String partnerName;
//        private final String businessNo;
//        private final String email;
//        private final Partner.Status status;
//
//        public ParserResponse(PartnerInfo partnerInfo) {
//            this.partnerToken = partnerInfo.getPartnerToken();
//            this.partnerName = partnerInfo.getPartnerName();
//            this.businessNo = partnerInfo.getBusinessNo();
//            this.email = partnerInfo.getEmail();
//            this.status = partnerInfo.getStatus();
//        }
//    }
}
