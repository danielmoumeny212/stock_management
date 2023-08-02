package com.daniel.gestiondestock.services.impl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.daniel.gestiondestock.dto.ArticleDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.model.Article;
import com.daniel.gestiondestock.repository.ArticleRepository;
import com.daniel.gestiondestock.services.ArticleService;
import com.daniel.gestiondestock.validators.ArticleValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService{
   
  private ArticleRepository articleRepository;

  @Autowired
  public ArticleServiceImpl(
    ArticleRepository articleRepository
  ){
    this.articleRepository = articleRepository;
  }
  @Override
  public void delete(Integer id) {
    if(id == null){
      log.error("Invalid Id provided to delete article");
    }
    this.articleRepository.deleteById(id);;
  }

  @Override
  public List<ArticleDto> findAll() {
     List<ArticleDto> articleDtos = articleRepository.findAll()
            .stream()
            .map(ArticleDto::fromEntity)
            .collect(Collectors.toList());
    return articleDtos;
  }


  @Override
  public ArticleDto findByCodeArticle(String codeArticle) {
    if(!StringUtils.hasLength(codeArticle)){
       log.error("Article CODE is null");
       return null;
    }
    Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->
          new EntityNotFoundException("Aucun article avec le Code = "+ codeArticle + "n'as été trouver dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND)
        );
  }

  @Override
  public ArticleDto findById(Integer id) {
    if (id == null) {
      log.error("Article ID is null !");
      return null; 
    }

    Optional<Article> article = articleRepository.findById(id);
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->
          new EntityNotFoundException("Aucun article avec l'ID  = "+ id + "n'as été trouver dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND)
        );

  }

  @Override
  public ArticleDto save(ArticleDto dto) {
    List<String> errors =  ArticleValidator.validate(dto);
    if(!errors.isEmpty()){
      log.error("Article is not valid");
      throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID,errors);
    }

    return ArticleDto.fromEntity(articleRepository.save(
       ArticleDto.toEntity(dto)
    )
    );
  }
  
}
