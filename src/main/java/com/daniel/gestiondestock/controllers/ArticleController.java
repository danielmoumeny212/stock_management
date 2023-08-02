package com.daniel.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.daniel.gestiondestock.dto.ArticleDto;
import com.daniel.gestiondestock.services.ArticleService;

@RestController
public class ArticleController {
   
  @Autowired
  private   ArticleService  articleService;


  @GetMapping("/articles")
  public List<ArticleDto>  article (){
     return articleService.findAll();
  }
  
}
