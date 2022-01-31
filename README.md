# ReadingIsGood book-store BackEnd Application
Getir ReadingIsGood Book Store Microservices Project

This project is produced by 5 microservices project. 
These are ;
  - Book Service
  - Order Service
  - User Service
  - Api Gateway Service
  - Service Discovery Service

Book service runs for book operations(creating, updating, deleting etc.) and store operations. Order service uses this service for updating book store.

Order service runs for order operation. If user wants to place order, this service runs actively. It gets order informations and goes to book service for stock informations. If stock is enough for ordering, the order can be happened. If stock is not enough, placing order process is cut of by this service. This service also can returns monthly order statistics.

User service runs for creating user and generating JWT token. User is created after sign up operation. JWT token is produced after sign in operation. This token is used as a bearer token for authenticating services.

Service Discovery service runs for naming server. Other microservices are clients for this service and they are added to service discovery server after running up automatically. In this way services can call each others without url and ports. This information is known only service discovery server. For this service, netflix eureka server was used. 

Api Gateway service runs for managing microservices from one point. General loggging processes and service authentication processes is done in this service. This service also puts user id information to request header. Thus other microservices can be used user information and they can complete domain information with user information. In this microservice, spring cloud gateway library was used.

In this project mongo db is used as a database.

Swagger 3 was used in the project. Service informations can be seen from swagger home paths.

Project can be deployed with docker. After producing docker images, it can be run with docker compose yaml file.(In bookservice folder)
