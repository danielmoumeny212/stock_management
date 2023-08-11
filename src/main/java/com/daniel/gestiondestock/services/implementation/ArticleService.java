package com.daniel.gestiondestock.services.implementation;

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
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.Article;
import com.daniel.gestiondestock.repository.ArticleRepository;
import com.daniel.gestiondestock.services.contracts.IArticleService;
import com.daniel.gestiondestock.validators.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleService implements IArticleService {

  private ArticleRepository repository;

  @Autowired
  public ArticleService(
      ArticleRepository articleRepository) {
    this.repository = articleRepository;
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Invalid Id provided to delete article");
    }
    this.repository.deleteById(id);
    ;
  }

  @Override
  public List<ArticleDto> findAll() {
    List<ArticleDto> articleDtos = repository.findAll()
        .stream()
        .map((article) -> DtoMapper.fromEntity(article, ArticleDto.class))
        .collect(Collectors.toList());
    return articleDtos;
  }

  @Override
  public ArticleDto findByCodeArticle(String codeArticle) {
    if (!StringUtils.hasLength(codeArticle)) {
      log.error("Article CODE is null");
      return null;
    }
    Optional<Article> articleOptional = repository.findArticleByCodeArticle(codeArticle);
    Article article = articleOptional.orElseThrow(
        () -> new EntityNotFoundException(
            "Article not found with  Code = " + codeArticle + "n'as été trouver dans la BDD",
            ErrorCodes.ARTICLE_NOT_FOUND));
    return DtoMapper.fromEntity(article, ArticleDto.class);
  }

  @Override
  public ArticleDto findById(Integer id) {
    if (id == null) {
      log.error("Article ID is null !");
      return null;
    }
    Optional<Article> articleOptional = repository.findById(id);
    Article article = articleOptional.orElseThrow(
        () -> new EntityNotFoundException("Aucun article avec l' ID " + id + " n'as été trouver dans la BDD",
            ErrorCodes.ARTICLE_NOT_FOUND));
    return DtoMapper.fromEntity(article, ArticleDto.class);

  }

  @Override
  public ArticleDto save(ArticleDto dto) {
    var errors = DtoValidator.validate(dto, "articles","id");
    if (!errors.isEmpty()) {
      log.error("Article is not valid");
      throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
    }
    var article = DtoMapper.toEntity(dto, Article.class);
    return DtoMapper.fromEntity(repository.save(article), ArticleDto.class);
  }

}
