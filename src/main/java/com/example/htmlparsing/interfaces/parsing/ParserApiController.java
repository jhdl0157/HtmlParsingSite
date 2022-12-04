package com.example.htmlparsing.interfaces.parsing;

import com.example.htmlparsing.common.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/parsings")
public class ParserApiController {
    @PostMapping()
    public void registerHtml(@RequestBody @Valid ParsingDto.ParserRequest parserRequest){
        if(parserRequest!=null){
            throw new EntityNotFoundException();
        }
        System.out.println(parserRequest.getUrl());
        System.out.println(parserRequest.getType());
        System.out.println(parserRequest.getInvide());
    }

}
