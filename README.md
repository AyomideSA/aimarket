# Running the web app
## Directions to run with in memory database
Use the following command to run the included jar file: **java -jar aimarket-0.0.1.jar**

## Directions to run with a local SQL database
#### Prerequisites
- SQL must be installed on your machine.
- A local database must exist on your machine with a username and password.
#### Directions
- Copy the following text into the application.properties file (located in src/main/resources/application.properties) <br> 
> spring.datasource.url=jdbc:mysql://localhost:3306/**[DBname]** <br>
spring.datasource.driverClassName=com.mysql.jdbc.Driver <br>
spring.datasource.username=**[DBusername]** <br>
spring.datasource.password=**[DBpassword]** <br>
spring.jpa.hibernate.ddl-auto=update <br>
- Replace [DBname], [DBusername] and [DBpassword] with the corresponding credentials.



