package com.daniel.gestiondestock.services;

import java.util.List;

import com.daniel.gestiondestock.dto.ArticleDto;

public interface ArticleService {
  
  ArticleDto save(ArticleDto dto);
  ArticleDto findById(Integer id);
  ArticleDto findByCodeArticle(String codeArticle);
  
  List<ArticleDto> findAll();

  void delete(Integer id);


}
