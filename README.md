## REST HTTPS API with Spring Boot and Angular JS.

[![Build Status](https://travis-ci.org/OKaluzny/springboot-rest-api-angularjs-https.svg?branch=master)](https://travis-ci.org/OKaluzny/springboot-rest-api-angularjs-https)

### Technology stack:

* Maven;
* FindBugs;
* Travis CI;
* Tomcat;
* Spring Boot;
* Spring MVC;
* Spring Data JPA;
* Hibernate (as JPA implementation);
* MySQL Relation Database;
* Spring Security (as basic authentication);
* Angular JS, HTML, CSS.

### This is what my REST API does:

Go to `http://localhost:8443` to test:

* POST request to `/api/v1/objects/` with a "object" object as JSON creates a new "object";
* GET request to `/api/v1/objects/` returns a list of "objects";
* GET request to `/api/v1/objects/1` returns the "object" with ID 1;
* PUT request to `/api/v1/objects/3` with a "object" object as JSON updates the "object" with ID 3;
* DELETE request to `/api/v1/objects/4` deletes the "object" with ID 4;
* DELETE request to `/api/v1/objects/` deletes all the "objects".

#####To run this application use:

```bash
mvn spring-boot:run
  ```

#####To run the SonarQube use:

```bash
mvn clean install sonar:sonar
```

### The view in the Postman:

![alt tag](http://i.piccy.info/i9/6b24a9828fea6183c943f6aedbff1bb5/1479141242/61292/1085055/rest10.jpg)