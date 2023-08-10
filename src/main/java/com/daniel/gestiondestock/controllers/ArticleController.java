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
  public ArticleDto findByCodeArticle(String codeArticle) {
    return articleService.findByCodeArticle(codeArticle);
  }

  @Override

  public ArticleDto create(ArticleDto resource) {
    return articleService.save(resource);
  }

  @Override
  public List<ArticleDto> getAll() {
    return articleService.findAll();
  }

  @Override
  public ArticleDto getById(Integer id) {
    return articleService.findById(id);

  }

}
