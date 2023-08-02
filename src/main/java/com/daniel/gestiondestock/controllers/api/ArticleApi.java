package com.daniel.gestiondestock.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import com.daniel.gestiondestock.dto.ArticleDto;


public interface ArticleApi {
    
  @PostMapping(value="/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ArticleDto save(@RequestBody ArticleDto dto);
  
  @GetMapping(value ="/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  ArticleDto findById(@PathVariable("idArticle") Integer id);

  @GetMapping(value ="/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  ArticleDto findByCodeArticle(String codeArticle);
  
  @GetMapping(value ="/articles/all", produces=MediaType.APPLICATION_JSON_VALUE)
  List<ArticleDto> findAll();
  
  @DeleteMapping(value="/articles/delete/{idArticle}")
  void delete(@PathVariable("idArticle") Integer id);
  

}
