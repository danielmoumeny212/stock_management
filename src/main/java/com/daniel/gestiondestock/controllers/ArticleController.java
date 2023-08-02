package com.daniel.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniel.gestiondestock.controllers.api.ArticleApi;
import com.daniel.gestiondestock.dto.ArticleDto;
import com.daniel.gestiondestock.services.impl.ArticleServiceImpl;
import static com.daniel.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/article")
public class ArticleController implements ArticleApi {

  @Autowired
  private ArticleServiceImpl articleService;

  @Override
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
