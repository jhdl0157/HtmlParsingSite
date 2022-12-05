package com.example.htmlparsing.infrastructure;

import com.example.htmlparsing.common.ConvertType;
import com.example.htmlparsing.domain.parsing.Parsing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParsingRepository extends JpaRepository<Parsing,Long> {
    Parsing findByUrlAndConvertTypeAndInvide(String url, ConvertType convertType,int invide);
    Boolean existsByUrlAndConvertTypeAndInvide(String url, ConvertType convertType,int invide);
}
