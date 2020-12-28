package com.sbu.springboot;

import com.sbu.springboot.Article.model.Article;
import com.sbu.springboot.Article.service.ArticleServiceImpl;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LibraryController {

    @Autowired
    private ArticleServiceImpl articleServiceImpl;

    @Autowired
    private EsConfig client;
    
    @RequestMapping("/test")
    public String test(Map<String, Object> model)
    {
        String message = "testing thymeleaf";
        model.put("message", message);
        return "test";
    }

    @RequestMapping(value = "/insert", method = {RequestMethod.POST, RequestMethod.GET})
    public String insertDocument(HttpServletRequest request, Map<String, Object> model) {

        String message;
        if (request.getMethod().equals("GET")) {
            message = "Please fill in the form";
        } else {
            String title = request.getParameter("title");
            String[] authors = request.getParameter("author").split(",");
            ArrayList<String> author = new ArrayList<>(Arrays.asList(authors));

            String isbn = request.getParameter("isbn");
            String publisher = request.getParameter("publisher");
            String pub_type = request.getParameter("pub_type");
            String articleAbstract = request.getParameter("abstract");
            int year = Integer.parseInt(request.getParameter("year"));

            String[] tgs = request.getParameter("tags").split(",");
            ArrayList<String> tags = new ArrayList<>(Arrays.asList(tgs));

            Article article = new Article(title, author, year, isbn, publisher, pub_type, articleAbstract, tags);
            articleServiceImpl.save(article);
            message = "Successfully inserted the following article\n" + article.toString();
        }

        model.put("message", message);
        return "insert";
    }

    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchDocument(HttpServletRequest request, Model model) {
        String message = "";
        if (request.getParameter("update") != null) {
            String id = request.getParameter("update");
            request.getSession().setAttribute("articleID", id);
            message = "Fill in the update from";
            model.addAttribute("message", message);
            model.addAttribute("id",id);
            return "update";
        } else if (request.getParameter("delete") != null) {
            String id = request.getParameter("delete");
            Article article = articleServiceImpl.findOne(id);
            articleServiceImpl.delete(article);
            message = "Successfully deleted the article !";
        } else if (request.getParameter("search") != null) {

            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String title = request.getParameter("title");
            if (title.length() != 0) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("title", title));
            }

            String author = request.getParameter("author");
            if (author.length() != 0) {
                String[] authors = author.split(",");
                for (String a : authors) {
                    System.out.println(a);
                    boolQueryBuilder.must(QueryBuilders.matchQuery("author", a));
                }

            }

            String isbn = request.getParameter("isbn");
            if (isbn.length() != 0) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("isbn", isbn));
            }

            String publisher = request.getParameter("publisher");
            if (publisher.length() != 0) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("publisher", publisher));
            }

            String pub_type = request.getParameter("pub_type");
            if (pub_type.length() != 0) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("pub_type", pub_type));
            }

            String articleAbstract = request.getParameter("abstract");
            if (articleAbstract.length() != 0) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("abstrct", articleAbstract));
            }

            String year = request.getParameter("year");
            if (year.length() != 0) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("year", year));
            }

            String tag = request.getParameter("tags");
            if (tag.length() != 0) {
                String[] tags = tag.split(",");
                for (String t : tags) {
                    boolQueryBuilder.must(QueryBuilders.matchQuery("tags", t));
                }
            }

            ArrayList<Article> result = (ArrayList<Article>) articleServiceImpl.search(boolQueryBuilder);
            HashMap<String, String> articles = new HashMap<>();
            for (Article article : result) {
                articles.put(article.getId(), article.toString());
            }
            model.addAttribute("articles", articles);
            message = "a total of " + articles.size() + " articles found.";
        }
        //System.out.println(articleServiceImpl.findOne("AWYCudNtu71V92oy9KTF"));
        model.addAttribute("message", message);
        return "search";
    }

    @RequestMapping(value = "/search/all", method = {RequestMethod.POST, RequestMethod.GET})
    public String allDocument(HttpServletRequest request, Model model) {
        System.out.println("All is called");
        ArrayList<Article> all = (ArrayList<Article>) articleServiceImpl.findAll();
        HashMap<String, String> articles = new HashMap<>();
        for (Article article : all) {
            articles.put(article.getId(), article.toString());
        }
        model.addAttribute("articles", articles);
        String message = "a total of " + articles.size() + " articles found.";
        model.addAttribute("message", message);
        return "search";
    }

    @RequestMapping(value = "/search/update", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateDocument(HttpServletRequest request, Model model) {
        String id = (String) request.getSession().getAttribute("articleID");
        Article article = articleServiceImpl.findOne(id);
        String title = request.getParameter("title");
        if (title.length() != 0) {
            article.setTitle(title);
        }

        String author = request.getParameter("author");
        if (author.length() != 0) {
            String[] authors = author.split(",");
            ArrayList<String> authorlist = new ArrayList<>(Arrays.asList(authors));
            article.setAuthor(authorlist);
        }

        String isbn = request.getParameter("isbn");
        if (isbn.length() != 0) {
            article.setIsbn(isbn);
        }

        String publisher = request.getParameter("publisher");
        if (publisher.length() != 0) {
            article.setPublisher(publisher);
        }

        String pub_type = request.getParameter("pub_type");
        if (pub_type.length() != 0) {
            article.setPub_type(pub_type);
        }

        String articleAbstract = request.getParameter("abstract");
        if (articleAbstract.length() != 0) {
            article.setAbstrct(articleAbstract);
        }

        String year = request.getParameter("year");
        if (year.length() != 0) {
            article.setYear(Integer.parseInt(year));
        }

        String tag = request.getParameter("tags");
        if (tag.length() != 0) {
            String[] tags = tag.split(",");
            ArrayList<String> taglist = new ArrayList<>(Arrays.asList(tags));
            article.setTags(taglist);
        }

        articleServiceImpl.save(article);
        String message = "Article updated susseccfully<br>" + article.toString();
        model.addAttribute("message", message);
        model.addAttribute("id", id);
        return "update";

    }
}
