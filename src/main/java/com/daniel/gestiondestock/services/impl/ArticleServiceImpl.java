package com.daniel.gestiondestock.services.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.ArticleDto;
import com.daniel.gestiondestock.services.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{

  @Override
  public void delete(Integer id) {
    
  }

  @Override
  public List<ArticleDto> findAll() {
    return null;
  }

  @Override
  public ArticleDto findByCodeArticle(String codeArticle) {
    return null;
  }

  @Override
  public ArticleDto findById(Integer id) {
    return null;
  }

  @Override
  public ArticleDto save(ArticleDto dto) {
    return null;
  }
  
}
