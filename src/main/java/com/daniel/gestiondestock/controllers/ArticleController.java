package com.daniel.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniel.gestiondestock.controllers.api.ArticleApi;
import com.daniel.gestiondestock.dto.ArticleDto;

import com.daniel.gestiondestock.services.implementation.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.daniel.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/articles")
@Tag(name = "Articles")
public class ArticleController implements ArticleApi {

  @Autowired
  private ArticleService articleService;

  @Override
  @Operation(description = "DELETE endpoint for articles", summary = "This is a summary of delete articles", responses = {
      @ApiResponse(description = "success", responseCode = "200")
  })
  
  public void delete(Integer id) {
    articleService.delete(id);
  }

  @Override
  @Operation(description = "GET enpoint for retrieving an article by its code ")
  @ApiResponses(value = {
      @ApiResponse(description = "success", responseCode = "200"),
      @ApiResponse(description = "Article not found with  Code", responseCode = "404")
  })
  public ArticleDto findByCodeArticle(String codeArticle) {
    return articleService.findByCodeArticle(codeArticle);
  }

  @Override
  @Operation(description = "POST endpoint for creating an article ")
  @ApiResponses(value = {
      @ApiResponse(description = "success", responseCode = "200"),
      @ApiResponse(description = "not valide article ", responseCode = "400")
  })
  public ArticleDto create(ArticleDto resource) {
    return articleService.save(resource);
  }

  @Override
  @Operation(description = "GET endpoint for getting all articles form db")
  @ApiResponse(description = "success", responseCode = "200")
  public List<ArticleDto> getAll() {
    return articleService.findAll();
  }

  @Override
  @Operation(description = "GET endpoint for getting an article by id")
  @ApiResponses(value = {
      @ApiResponse(description = "success", responseCode = "200"),
      @ApiResponse(description = "Article not found with  Code", responseCode = "404")
  })
  public ArticleDto getById(Integer id) {
    return articleService.findById(id);
  }

}
