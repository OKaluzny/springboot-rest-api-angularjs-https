## REST HTTPS API with Spring Boot and Angular JS.

[![Build Status](https://travis-ci.org/OKaluzny/springboot-rest-api-angularjs-https.svg?branch=master)](https://travis-ci.org/OKaluzny/springboot-rest-api-angularjs-https)

### Technology stack:

* Maven;
* FindBugs;
* Travis CI;
* Tomcat embedded;
* Spring Boot;
* JUnit
* Logback
* SLF4J
* Spring MVC;
* Spring Data JPA;
* Hibernate (as JPA implementation);
* MySQL Relation Database;
* Spring Security (as basic authentication);
* Angular JS, HTML, CSS.

### To run this application use:

```bash
mvn spring-boot:run
  ```

### This is what my REST API does:

Go to `https://localhost:8443` to test and must specify a username: `user` and password: `user`

* POST request to `/api/v1/objects/` with a "object" object as JSON creates a new "object";
* GET request to `/api/v1/objects/` returns a list of "objects";
* GET request to `/api/v1/objects/1` returns the "object" with ID 1;
* PUT request to `/api/v1/objects/3` with a "object" object as JSON updates the "object" with ID 3;
* DELETE request to `/api/v1/objects/4` deletes the "object" with ID 4;
* DELETE request to `/api/v1/objects/` deletes all the "objects".

#####To run the SonarQube use:

```bash
mvn clean install sonar:sonar
```

### The view in the Postman:

![alt tag](http://i.piccy.info/i9/665a540f876b8b1906ce5f8677343aaf/1479502060/67612/1085055/rest10.jpg)

### Open browser and browse at https://localhost:8443

![alt tag](http://i.piccy.info/i9/60aafd469a35780dbe5c15f9bdc136ea/1479501511/76368/1085055/htcb123.jpg)