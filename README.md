# Ecommerce User Management

> This application manages user profiles to create, update, delete and find a user.

> The projects has been developed using the following technology stacks.
   **Java, Spring Boot, Maven, Embedded Tomcat, Embedded Postgresql, Lombok, Swagger-UI, Spring JDBC, Spring REST**

> Build the project using Maven

```sh
mvn clean install
```

> To run the project as a spring-boot application

```sh
mvn spring-boot:run
```

After successfully started the application, the application will be listening to 8082 port. The embedded postgresql installer depending upon the OS environment would be automatically downloaded and installed in tmp location during first set up when you run the above command.

The following curl commands can be used to manage user profiles

> Postgresql properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=user
spring.datasource.password=pass
spring.datasource.show_sql=true
spring.datasource.driver-class-name=org.postgresql.Driver
```


> To create a new user

```sh
curl --data @newuser.json -H "Content-Type: application/json" -X POST http://localhost:8082/usermanagement/api/v1/user/create

Output: {"id":1,"username":"sample","password":"pass","firstName":"Sample","lastName":"Sample","email":"sample@sample.com","phone":"12345","enabled":true}
```

You can find the newuser.json file under **src/test/resources** folder.

> To update an existing user

```sh
curl --data @updateuser.json -H "Content-Type: application/json" -X PUT http://localhost:8082/usermanagement/api/v1/user/1

Output:  {"id":1,"username":"sample","password":"abcd","firstName":"Sample","lastName":"Sample","email":"sample1@sample.com","phone":"123456","enabled":true}
```

You can find the updateuser.json file under **src/test/resources** folder.

> To fetch an existing user details

```sh
curl -X GET http://localhost:8082/usermanagement/api/v1/user/1﻿

Output:  {"id":1,"username":"sample","password":"abcd","firstName":"Sample","lastName":"Sample","email":"sample1@sample.com","phone":"123456","enabled":true}
```

> To delete an existing user

```sh
curl -X DELETE http://localhost:8082/usermanagement/api/v1/user/1﻿
```

You can also try out all these APIs using the following swagger-ui url - http://localhost:8082/usermanagement/documentation/swagger-ui/

[SWAGGER-UI](http://localhost:8082/usermanagement/documentation/swagger-ui/)