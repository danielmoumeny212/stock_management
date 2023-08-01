package com.daniel.gestiondestock.repository;
import  com.daniel.gestiondestock.model.Article;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;


public interface  ArticleRepository extends JpaRepository<Article, Integer> {
  
  // Request jdbc query to get articles
  // @Query("select a from article where codearticle = :code and designation = :designation")
  // List<Article> findCustomQuery(@Param("code") String c, @Param("designation") String d);
   
  // Native SQL queries 
  // @Query(value="select * from articles where code = :code", nativeQuery = true)
  // List<Article> findCustomNativeQuery(@Param("code") String c);
  

  // List<Article> findByCodeArticleIgnoreCaseAndDesignationIgnoreCase(String codeArticle, String designation);

  
}
