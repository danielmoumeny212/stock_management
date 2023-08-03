package com.daniel.gestiondestock.services.contracts;

import java.util.List;

import com.daniel.gestiondestock.dto.ArticleDto;

public interface IArticleService {
  
  ArticleDto save(ArticleDto dto);
  ArticleDto findById(Integer id);
  ArticleDto findByCodeArticle(String codeArticle);
  
  List<ArticleDto> findAll();

  void delete(Integer id);


}
