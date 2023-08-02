package com.daniel.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniel.gestiondestock.controllers.api.ArticleApi;
import com.daniel.gestiondestock.dto.ArticleDto;
import com.daniel.gestiondestock.services.ArticleService;
import static com.daniel.gestiondestock.utils.Constants.APP_ROOT;


@RestController
@RequestMapping(APP_ROOT)
public class ArticleController implements ArticleApi{
   
  @Autowired
  private   ArticleService  articleService;

  @Override
  public void delete(Integer id) {
      articleService.delete(id);
  }

  @Override
  public List<ArticleDto> findAll() {
    return articleService.findAll();
  }

  @Override
  public ArticleDto findByCodeArticle(String codeArticle) {
    return articleService.findByCodeArticle(codeArticle);
  }

  @Override
  public ArticleDto findById(Integer id) {
    return articleService.findById(id);
  }

  @Override
  public ArticleDto save(ArticleDto dto) {
    return articleService.save(dto);
  }


  
}
