package com.daniel.gestiondestock.services.contracts;

import com.daniel.gestiondestock.dto.ArticleDto;

public interface IArticleService extends AbstractService<ArticleDto>{
  

  ArticleDto findByCodeArticle(String codeArticle);



}
