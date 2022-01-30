
# Graduation Project 

## About The Project 

Building a Restful API for the Loan Application System, which can have the loan application requests and return the loan result with SMS to the customer according to the relevant criteria, using the Spring Boot framework and Frontend.

## Table of Contents
- Project Architecture
- Technologies
- Installing]
    - Maven
    - Docker
- Usage
    - Description
    - Endpoints
    - Application User Interface
- TODO
- License
- Contact
## Project Architecture

![Untitled Diagram](https://user-images.githubusercontent.com/82765835/151706136-1ea46d74-d0a5-4c74-a17d-46c156499041.jpg)

## Technologies

- Oracle JDK 11
- RabbitMQ
- PostgreSQL
- Maven
- Mapstruct
- Lombok
- Swagger
- Spring Data JPA
- Spring Cloud Gateway
- Eureka Server
- JUnit 5
- SpringFox Swagger 2
- Lombok
- React
- Tailwind CSS
- Jib

## Installation

**To run local**

**1. Clone the application from Github**

**2. Run Docker Compose.yml**

**3. Run Eureka-Server**

**4. Run Api-Gateway**

**5. Run Customer Service**

**6. Run Credit Service**

**7. Run Notification Service**

**8. Open APP-UI and write npm start to terminal**

**9. Good Job 👍**

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
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |                                                         
|    `/credit/get` | GET | national-id, birthday | GET the Loan Application Detail to query from databases. Request a body which contains `national-id` and  `CustomerDetailDTO`.   |

## Application User Interface

I chose React and Tailwind Css for the frontend of my project because In my opinion, they can help me. 
I needed a total of 6 pages for the requirements of the project and I provided the communication between the pages with routers. 
There is no validation control on the frontend. Therefore, care should be taken when entering data.
Finally, if you send an incorrect request, you will receive a warning message. If you send correct request,you will receive a approval message.

## Main Page

There are 2 different buttons on the main page. The application form button is for application procedures. The second button is for post-application processes.

<img width="1440" alt="Ekran Resmi 2022-01-30 18 12 52" src="https://user-images.githubusercontent.com/82765835/151706230-abfd42d4-3300-4624-91f2-a91260921b90.png">

After clicking the application form button, you will come to the application screen. On this screen, the customer's information is obtained and an application is created by clicking the last application button. 

!!Reminding!! Data should be entered as in the picture.

<img width="1440" alt="Ekran Resmi 2022-01-30 18 16 51" src="https://user-images.githubusercontent.com/82765835/151706263-d2fe55b0-7007-420a-be30-a4b2a2209022.png">

You should click the second button to learn the process of updating and deleting the user or the application result.

<img width="1440" alt="Ekran Resmi 2022-01-30 18 17 29" src="https://user-images.githubusercontent.com/82765835/151706843-2d6b8cc9-5ca9-4a5c-8cbd-02d769da148a.png">

After clicking the button, you will see 3 different buttons. You have to click the relevant button for what you want to do.

<img width="1440" alt="Ekran Resmi 2022-01-30 18 18 10" src="https://user-images.githubusercontent.com/82765835/151706294-95709fa4-9979-4bc2-b1b1-8ecc90a2d3d7.png">

If you want to delete a customer, you must enter the correct customer national ID number and date of birth, otherwise you will see an error message.

<img width="1440" alt="Ekran Resmi 2022-01-30 18 18 50" src="https://user-images.githubusercontent.com/82765835/151706353-7b4b4b3d-2d77-469a-b765-84849d9066b8.png">

If you want to update the customer, you can change the fields you want to update (but not all fields are updated), and if you do not enter the ucustomer national ID number correctly, you will see an error message.

<img width="1440" alt="Ekran Resmi 2022-01-30 18 19 48" src="https://user-images.githubusercontent.com/82765835/151706313-eb948c03-714d-4423-8380-309d2a0991eb.png">

If you want to query a loan application result , you must enter the correct customer national ID number and date of birth, otherwise you will see an error message.

<img width="1440" alt="Ekran Resmi 2022-01-30 18 20 10" src="https://user-images.githubusercontent.com/82765835/151706332-b3760906-44cf-4e73-8730-7b455a2a4f83.png">

## To Do List

* Frontend can be improved
* Notification can send sms instead of logging
* Config Server can be added

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Süleyman Kaybaşı - [Linkedin](https://www.linkedin.com/in/suleymankayabasi/)
