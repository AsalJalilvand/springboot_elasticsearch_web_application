# Spring Boot Hello Wrold with Elasticsearch and Thymeleaf

This is a started project for building a web application with Spring Boot, Elasticsearch and Thymeleaf.

**Goal**: Save, update, search and delete documents in an Elasticsearch repository with a web interface.

## Structure
`````
├───src
│   └───main
│       ├───java
│       │   └───com
│       │       └───sbu
│       │           └───springboot
│       │               │   EsConfig.java
│       │               │   LibraryController.java
│       │               │   WebApplication.java
│       │               │
│       │               └───Article
│       │                   ├───model
│       │                   │       Article.java
│       │                   │
│       │                   ├───repository
│       │                   │       ArticleRepository.java
│       │                   │
│       │                   └───service
│       │                           ArticleService.java
│       │                           ArticleServiceImpl.java
│       │
│       ├───resources
│       │   │   application.properties
│       │   │
│       │   ├───public
│       │   │       index.html
│       │   │
│       │   └───templates
│       │           insert.html
│       │           search.html
│       │           test.html
│       │           update.html
│       │
│       └───webapp
│           │   hello.jsp
│           │
│           ├───META-INF
│           │       context.xml
│           │
│           └───WEB-INF

`````

* WebApplication is the starting point of the program.
* The homepage (index.html) must be in resources/public
* Put all thymeleaf templates in resources/templates folder

## Dependencies
- JDK 1.8 or upper
- tomcat 9
- springboot 2.3
- elasticsearch 7.10 
- kibana 7.10 (optional if you want show a dashboard on homepage)

## Run
I use IntelliJ IDEA and run the WebApplication file without
much hassle. The homepage will be available at http://localhost:8081.
* make sure you have a running Elasticsearch server at port 9300. You can
change the confing in Esconfig file.
* If you'd like to show a dashboard/chart etc. with Kibana, also have Kibana up
and running. Have a look at this [discussion](https://discuss.elastic.co/t/embed-dashboard-or-visualization-on-an-external-web-page-with-specified-filters/133961/2) to see how to do it. You must have
created the dashboard/charts beforehand. In the screenshot, I have already added
a few articles and created a dashboard.

If you had any problems, perhaps follow the install 
guide on [spring boot tutorial](https://hellokoding.com/spring-boot-hello-world-example-with-jsp/
).

## Maintenance
I made this project in around September 2018 when I was learning about working with Elasticsearch and 
Spring Boot. I used this [tutorial](https://hellokoding.com/spring-boot-hello-world-example-with-jsp/
) as a starting point for Spring Boot. For Elasticsearch part, I've used [Bealdung's tutorial](https://www.baeldung.com/spring-data-elasticsearch-tutorial).
Since the Spring Data Elasticsearch's API has changed a lot since the first time I made
this project, I've tried to update the deprecated parts and the project works fine
as of December 2020.
For now, I don't intend to maintain this repository for future updates of the APIs 
(but I might change my mind since I like Elasticsearch!).

## References
- [Spring Boot JSP Tutorial with Example](https://hellokoding.com/spring-boot-hello-world-example-with-jsp/)
(I used Thymeleaf instead of JSP)
- [Introduction to Spring Data Elasticsearch](https://www.baeldung.com/spring-data-elasticsearch-tutorial)