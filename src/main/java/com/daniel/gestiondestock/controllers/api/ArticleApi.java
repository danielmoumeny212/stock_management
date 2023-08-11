package com.daniel.gestiondestock.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.http.MediaType;
import com.daniel.gestiondestock.dto.ArticleDto;

public interface ArticleApi extends AbstractApi<ArticleDto> {
  @GetMapping(value = "/code/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  ArticleDto findByCodeArticle(String codeArticle);

}
