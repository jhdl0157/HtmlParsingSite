package com.example.htmlparsing.interfaces.parsing;

import com.example.htmlparsing.application.ParsingFacade;
import com.example.htmlparsing.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/parsings")
@RequiredArgsConstructor
public class ParserApiController {
    private final ParsingFacade parsingFacade;
    @PostMapping()
    public CommonResponse registerHtml(@RequestBody @Valid ParsingDto.ParserRequest parserRequest){
        val result=parsingFacade.getCombineText(parserRequest);
        return CommonResponse.success(result);
    }

}
