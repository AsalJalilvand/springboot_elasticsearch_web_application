package com.sbu.springboot.Article.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "library", type = "articles")
public class Article {

    @Id
    private String id;

    private String title;

    private List<String> author;

    @Field(type = FieldType.Integer)
    private int year;

    @Field(type=FieldType.Keyword)
    private String isbn;

    private String publisher;

    private String pub_type;

    private String abstrct;

    private List<String> tags;

    public Article() {
    }

    public Article(String title, List<String> author, int year, String isbn, String publisher, String pub_type, String articleAbstract, List<String> tags) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.publisher = publisher;
        this.pub_type = pub_type;
        this.abstrct = articleAbstract;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPub_type() {
        return pub_type;
    }

    public void setPub_type(String pub_type) {
        this.pub_type = pub_type;
    }

    public String getAbstrct() {
        return abstrct;
    }

    public void setAbstrct(String abstrct) {
        this.abstrct = abstrct;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ",<br />title=" + title + ",<br/>author=" + author + ",<br>year=" + year + ",<br>isbn=" + isbn + ",<br>publisher=" + publisher + ",<br>pub_type=" + pub_type + ",<br>abstract=" + abstrct + ",<br>tags=" + tags + '}';
    }

}
