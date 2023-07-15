
# Heliverse_1

# Tech Stack
- Java
- Hibernate
- Spring Framework
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Spring Security
- EmailSender

# Installation & Run
- Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database configuration.

# Note
- Don't Create Database By Default I will Pass the Database name heliverse_3
- jdbc:mysql://localhost:3306/heliverse_3?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false
- Don't Forget to change your username and password  for your local

```
    server.port=8888
    jdbc:mysql://localhost:3306/heliverse_3?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false
    spring.datasource.username=root
    spring.datasource.password=Amit@2655
    # API Root Endpoint
```
# STEPS :
- First we run the application
- Insert The roles through Hard coded
-   INSERT INTO role VALUES (1,'ROLE_ADMIN');

-   mysql> insert into role values(2,"ROLE_user");

-   mysql> insert into role values(3,"ROLE_CREATOR");

-   After that Do Sign Up Use Below Dummy Details
-   Then Do Sign up
-   Then Save the Customer In the Database

Sign Up :
           http://localhost:8888/api/auth/signup
           {
            "userName":"Amit",
             "email":"ajha@gmail.com",
              "dob":"2000-08-11",
              "phone":"1234567890",
             "password":"Amit@12345",
             "name":"ROLE_USER"
            }

```
# Just afetr signup ypu will get otp for email verification

and we need dto verify

/verify
http://localhost:8888/api/auth/signup
{
  "otp":otp from gmail
}

```
Login :
                 http://localhost:8888/api/auth/login

                 {
                   "username":"Amit",
                   "password":"Amit@12345"
                 }
                 
        Note : Please Copy the JWT Token and paste it into the Authentication
```
```

Note : Id Is Autogenerated

```
```



```   