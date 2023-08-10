package com.daniel.gestiondestock.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.http.MediaType;
import com.daniel.gestiondestock.dto.ArticleDto;
import static com.daniel.gestiondestock.utils.Constants.APP_ROOT;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(APP_ROOT + "/articles/")
public interface ArticleApi extends AbstractApi<ArticleDto> {

  @ApiOperation(value = "Find an article by its code", notes = "this methods allows you to retrieve an article by its code", response = ArticleDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "article found successfully and retrieved"),
      @ApiResponse(code = 404, message = "Article does not exist")
  })
  @GetMapping(value = "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  ArticleDto findByCodeArticle(String codeArticle);

}
