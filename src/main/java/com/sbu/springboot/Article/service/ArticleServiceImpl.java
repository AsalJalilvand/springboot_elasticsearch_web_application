package com.sbu.springboot.Article.service;

import com.sbu.springboot.Article.model.Article;
import com.sbu.springboot.Article.repository.ArticleRepository;
import com.sbu.springboot.EsConfig;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

@Service
public class
ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private EsConfig client;

    public void setBookRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public Article findOne(String id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.orElseGet(Article::new);
    }

    public List<Article> findAll() {
        List<Article> articles= new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        return articles;
    }

    public Page<Article> findByAuthor(String author, PageRequest pageRequest) {
        return articleRepository.findByAuthor(author, pageRequest);
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    public List<Article> search(BoolQueryBuilder boolQueryBuilder) {
        List<Article> articles= new ArrayList<>();
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();
        try {
            SearchHits<Article> hits = client.elasticsearchTemplate().search(searchQuery, Article.class);
            for(SearchHit hit:hits)
            {
                articles.add((Article) hit.getContent());
            }
            System.out.println("searched");

        } catch (Exception ex) {
            System.out.println("exeption occured");
        }
        return articles;
    }

}
