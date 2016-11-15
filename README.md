## RESTFul HTTPS API (web service) with Spring Boot.

[![Build Status](https://travis-ci.org/OKaluzny/spring-rest-service-https.svg?branch=master)](https://travis-ci.org/OKaluzny/spring-rest-service-https)

### Tools:

* Build Automation - [Maven 3.3.x](https://maven.apache.org/)
* Static Analysis - [FindBugs 3.0.1](http://findbugs.sourceforge.net/)
* Continuous Integration - [Travis CI](https://travis-ci.org)
* Embedded Web Server - [Apache Tomcat](http://tomcat.apache.org/)
* Relation database - [MySQL database](https://www.mysql.com/)
* Hibernate (as JPA implementation)
* Spring Security (as basic authentication).

### This is what my REST API does:

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