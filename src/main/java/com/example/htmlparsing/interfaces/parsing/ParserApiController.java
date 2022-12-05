package com.example.htmlparsing.interfaces.parsing;

import com.example.htmlparsing.application.ParsingFacade;
import com.example.htmlparsing.common.CommonResponse;
import com.example.htmlparsing.common.exception.EntityNotFoundException;
import com.example.htmlparsing.domain.parsing.ParsingService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/parsings")
@RequiredArgsConstructor
public class ParserApiController {
    private final ParsingFacade parsingFacade;
    @PostMapping()
    public CommonResponse registerHtml(@RequestBody @Valid ParsingDto.ParserRequest parserRequest){
        System.out.println(parserRequest.toString());
        val result=parsingFacade.getCombineText(parserRequest);
        System.out.println(result.toString());
        return CommonResponse.success(result);
    }

}
