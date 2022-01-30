
# Graduation Project 

## About The Project 

Building a Restful API for the Loan Application System, which can have the loan application requests and return the loan result with SMS to the customer according to the relevant criteria, using the Spring Boot framework and Frontend.

## Table of Contents
- [Project Architecture](#project-architecture)
- [Technologies](#technologies)
- [Installing](#installing)
    - [Maven](#maven)
    - [Docker](#docker)
- [Usage](#usage)
    - [Description](#description)
    - [Endpoints](#endpoints)
    - [Application UI](#application-ui)
- [TODO](#todo)
- [Contributing](#contributing)
## Project Architecture

RESİM GELECEK BURAYA

## Technologies
- Oracle JDK 11
- RabbitMQ
- PostgreSQL
- Maven
- Spring Data JPA
- Spring Cloud Gateway
- Eureka Server
- JUnit 5
- SpringFox Swagger 2
- Lombok
- React
- Tailwind CSS

## Installation

### Maven
**1. Clone the application**

```bash
git clone KENDİ URL VER https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-rhancav
```
**2. Go to the project directory**
```bash
cd burayı da değiş gittigidiyor-graduation-project-rhancav
```
**3. Run**
```bash
mvnw spring-boot:run
```
### Docker
**1. Clone the application**

```bash
git clone KENDİ URL VER https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-rhancav
```
**2. Go to the project directory**
```bash
cd BURAYIDA DEĞİŞTİR gittigidiyor-graduation-project-rhancav
```
**3. Run**
```bash
docker-compose up
```
## Usage

### Description
There are prerequisites for the customer to apply for a loan. These are ID number, name-surname, monthly income, telephone number, date of birth and collateral amount.
The customer's credit score is calculated based on the last digit of the identification number.
After the customer makes an application, the customer's information is sent to the credit service, and the customer's credit status and limit are calculated.
Finally, the notification service notifies the customer of the credit status to the user by sms.
In addition, new users can be defined in the system, existing customers can be updated or deleted.
A completed loan application can only be queried with the ID number and date of birth.

## Endpoints

- To Check All Service Queues with RabbitMQ http://localhost:15672/#/

- To Check All Service with Eureka http://127.0.0.1:8761

### Customer Service

- To Test Customer Service endpoints with Swagger http://localhost:8081/swagger-ui.html#/

| Endpoint | Method |  Params | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|    `/customer/create` | POST | CustomerDetailDTO | Post the Customer detail to save databases. Request a body which contains `name`, `lastName`, `national-id`, `income`, `birthday`, `phoneNumber` and the `DepositDetailDTO` of the customer.                                                              |
|    `/customer/update` | POST | national-id, CustomerDetailDTO | Post the Customer detail to update databases. Request a body which contains `national-id` and  `CustomerDetailDTO`.
|    `/customer/delete` | GET | national-id, birthday  | Get the Customer detail to delete from databases. Request a body which contains `national-id` and  `birthday`.
|    `/customer/detail/nationa-id/birthday` | GET | national-id, birthday  |  Get the Customer detail to query from databases. Request a body which contains `national-id` and  `birthday`.                                                                 |

- To Test Credit Service endpoints with Swagger http://localhost:8082/swagger-ui.html#/

### Credit Service

| Endpoint | Method |  Params | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |                                                          |
|    `/credit/get` | GET | national-id, birthday | GET the Loan Application Detail to query from databases. Request a body which contains `national-id` and  `CustomerDetailDTO`.

## Application User Interface

I chose React and Tailwind Css for the frontend of my project because In my opinion, they can help me. 
I needed a total of 6 pages for the requirements of the project and I provided the communication between the pages with routers. 
There is no validation control on the frontend. Therefore, care should be taken when entering data.
Finally, if you send an incorrect request, you will receive a warning message. If you send correct request,you will receive a approval message.

BURAYA RESİM VE AÇIKLAMLAR GELCEK