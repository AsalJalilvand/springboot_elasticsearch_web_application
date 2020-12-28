package com.sbu.springboot.Article.repository;

import com.sbu.springboot.Article.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    Page<Article> findByAuthor(String author, Pageable pageable);

    List<Article> findByTitle(String title);
    
//    List<Article> findAll();
         
}