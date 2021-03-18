#iTravel

User 
----

|Sr.No |    API Name   | HTTP Method  |       Path      | Status Code |               Description                  | 
|:-----|:--------------|:------------:|:----------------|:------------|:-------------------------------------------|
|(1)   | GET Users     |GET           |/api/users       |200(OK)      | All User resources are fetched(pagination).|
|(2)   | POST Users    |POST          |/api/users       |201(Created) | A new User resource is created.            |
|(3)   | GET  Users    |GET           |/api/users/{id}  |200(OK)      | Get a single resource with id.             |
|(4)   | PUT  Users    |PUT           |/api/users/{id}  |200(OK)      | Update a single resource's state           |
|(5)   | DELETE Users  |POST          |/api/users/{id}  |204(NoContent)| Delete a single resource using id.        |
|(6)   | PATCH Users   |POST          |/api/users/{id}  |201(Created) | A new User resource is created.            |





<br />
<br />

Tools and Technologies
----------------------

- Spring boot ( `v2. 4. 3`)
    - spring-doc open api 
    - hibernate-validator 
    - lombok
- JDK(`v11.0.9)`
- Spring Framework
- Hibernate
- JPA
- MySQL(`v8.0.23`)
- Maven(`v4.0.0`)
- Intellij IDE(`2020.3.2`)


<br />

Application Properties File
---------------------------
    spring.datasource.url=jdbc:mysql://localhost:3306/itravel?useSSL=false
    spring.datasource.username = hendrix
    spring.datasource.password= password
    spring.jpa.hibernate.ddl-auto=create
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect