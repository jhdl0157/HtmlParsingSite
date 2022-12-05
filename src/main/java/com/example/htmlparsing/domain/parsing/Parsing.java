package com.example.htmlparsing.domain.parsing;

import com.example.htmlparsing.common.ConvertType;
import com.example.htmlparsing.common.ErrorCode;
import com.example.htmlparsing.common.exception.BaseException;
import com.example.htmlparsing.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.util.StringUtils;

import javax.persistence.*;


@Entity
@Getter
@Slf4j
@NoArgsConstructor
@Table(name="Parsings")
public class Parsing extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    @Enumerated(EnumType.STRING)
    private ConvertType convertType;
    private Integer invide;

    @Column(columnDefinition = "LONGTEXT")
    private String quotient;
    private String remainder;
    @Builder
    public Parsing(String url,ConvertType convertType,Integer invide,String quotient,String remainder){
        if(StringUtils.isEmpty(url)) throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        if(StringUtils.isEmpty(convertType.getValue())) throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        if(invide==null) throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        if(StringUtils.isEmpty(quotient)) throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        if(StringUtils.isEmpty(remainder)) throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        this.url=url;
        this.convertType=convertType;
        this.invide=invide;
        this.quotient=quotient;
        this.remainder=remainder;
    }
}
