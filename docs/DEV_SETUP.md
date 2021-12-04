# Database

## Create the database

1. Prerequisites:
   1. PostgreSQL
   2. pgAdmin
2. Create Database (in pgAdmin)
   1. Create a database in pgAdmin with the settings defined in [here](../src/main/resources/application.properties)
      1. name
      2. password
      3. user

## Initialization of default data in the database

Per default:
- if you start the application, [sample data](../src/main/resources/data.sql) from

If you want another behaviour on your local machine:
1. Add a new file (NAME can be replaced with any string, but it must match with NAME in point 3!):
````
src/main/resources/application-NAME.properties
````
2. In this file, add for example the following, to keep the data in your database on startup:
````
spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=false
spring.sql.init.mode=never
````
3. Add the following line to your VM-options:
````
-Dspring.profiles.active=NAME
````