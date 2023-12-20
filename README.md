# Resort-Management-System
  We are trying to create a RESTful application (you only need to write the backend part of it; no UI is necessary) for a Resort Management System. Imagine an online system where 
  customers can make reservations for and pay for different resort options. During the first phase of the project, understand the requirements and design a class diagram for it, then 
  code all the entities.

# Goal
  The goal of this project is to give you hands-on, practical experience with building a RESTful system of microservices using Spring, Spring MVC, Spring Boot, Spring Data JPA and 
  Spring Cloud Services.

# Features:
----
* The resort sells many types of products (rooms, villas, cottages, beach tents, etc.)
* products are normally sold based on a nightly rate (total price depends on the number of nights of stay)
* Each product has a name, description and a nightly rate. It also has a maximum capacity or number of beds.
* Customers can place a "Reservation" for a number of products for a number of nights.
* Each "Item" in a "Reservation" has a reference to a "Product"
* Item has a check-in date and a checkout date as well as number of occupants.
* Each customer has a billing address and a shipping address
* What distinguishes a billing address from a shipping address is the Address Type (Billing orShipping).
* An address has a State and a State belongs to a Country
* A Reservation can be in multiple states: New, Placed, Processed (payment is processed), Arrived, Departed or Cancelled
* To use the system, each customer has to create a "User" account (with a username and a password).
* There are two basic user types in the system: Admin and Client.

## Libraries Used
----
* [Validation](https://beanvalidation.org) - Jakarta Bean Validation Constrain once, validate everywhere.
* [spring security](https://docs.spring.io/spring-security/reference/getting-spring-security.html) - starter that aggregates Spring Security-related dependencies.
* [Lombok](https://projectlombok.org) -  spicing up your java Never write another getter or equals method again.
* [Mysql](https://dev.mysql.com/downloads/connector/j/5.1.html) - our Database Driver .
* [Sonarlint](https://marketplace.visualstudio.com/items?itemName=SonarSource.sonarlint-vscode) - Use with SonarQube & SonarCloud for optimal team performance..

## Architecture

<img src="https://github.com/gl0rious/Resort-Management-System/blob/staging/archImg.png" >

## Unit Test

<img src="https://github.com/gl0rious/Resort-Management-System/blob/staging/testimg.png" >

## Agile Methodology(Trello)

<img src="https://github.com/gl0rious/Resort-Management-System/blob/main/agile1Img.png" >

<img src="https://github.com/gl0rious/Resort-Management-System/blob/main/agile2Img.png" >


## Presention
* [Presention](https://docs.google.com/presentation/d/1iH4saG0KtScrz6FN7dTskRmYbwO6VzbS/edit?usp=sharing&ouid=109376219380936959710&rtpof=true&sd=true) - check our Presentation.

  

