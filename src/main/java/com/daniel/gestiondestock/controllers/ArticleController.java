package com.daniel.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniel.gestiondestock.controllers.api.ArticleApi;
import com.daniel.gestiondestock.dto.ArticleDto;

import com.daniel.gestiondestock.services.implementation.ArticleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.daniel.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/articles")
public class ArticleController implements ArticleApi {

  @Autowired
  private ArticleService articleService;

  @Override
  @ApiOperation(value = "Delete a Article by ID", response = ArticleDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "article found successfully and retrieved"),
      @ApiResponse(code = 404, message = "Article does not exist")
  })
  public void delete(Integer id) {
    articleService.delete(id);
  }

  @Override
  public ArticleDto findByCodeArticle(String codeArticle) {
    return articleService.findByCodeArticle(codeArticle);
  }

  @Override
  @ApiOperation(value = "create a new article", response = ArticleDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "article created successfully"),
      @ApiResponse(code = 400, message = "articledto is not valid")
  })
  public ArticleDto create(ArticleDto resource) {
    return articleService.save(resource);
  }

  @Override
  @ApiOperation(value = "Get all articles", responseContainer = "List<ArticleDto.class>")
  @ApiResponse(code = 200, message = "OK")
  public List<ArticleDto> getAll() {
    return articleService.findAll();
  }

  @Override
  @ApiOperation(value = "Find an article by its ID", notes = "this methods allows you to retrieve an article by its ID", response = ArticleDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "article found successfully and retrieved"),
      @ApiResponse(code = 404, message = "Article does not exist")
  }

  )
  public ArticleDto getById(Integer id) {
    return articleService.findById(id);

  }

}
