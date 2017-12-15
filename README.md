# mongodb-with-springboot

Author - Waqar Syed
http://waqartechtalk.com/

This sample POC demonstarates the use of mongodb with Spring Boot
Refer to the pom.xml for all the required dependencies.

This application can be built using maven 3.2 or above.
From the command line type: mvn clean install

Running the application: From the command line type: mvn spring-boot:run 
OR Run the main method in class Application.java

UserController is the REST controller which exposes an API to get the userinfo from Mongo DB.

Resource URL:
POST, http://localhost:8080/user

Header: 
Content-Type = application/json

Request Body:

{
  "username":"admin@gmail.com",
  "password":"1234"
}


Response will be the json of user information from the matched momgo db collection.
For more information; please refer to my blog - http://waqartechtalk.com/
