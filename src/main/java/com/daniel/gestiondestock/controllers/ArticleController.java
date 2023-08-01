package com.daniel.gestiondestock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.services.ArticleService;

@RestController
public class ArticleController {
   
  @Autowired
  private ArticleService articleService; 
  
}
