# Running the web app
## Directions to run with in memory database (Through Jar File)
Use the following command to run the included jar file: **java -jar aimarket-0.0.1.jar**

## Directions to run with in memory database (Through IDE)
#### Prerequisites
- Maven must be installed on your machine <br>

Open the project with your desired IDE

Copy the following text into the application.properties file (located in src/main/resources/application.properties) 
> spring.datasource.url=jdbc:h2:mem:testdb <br>
spring.datasource.driverClassName=org.h2.Driver <br>
spring.datasource.username=sa <br>
spring.datasource.password=password <br>
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect <br>
spring.h2.console.enabled=true <br>
spring.jpa.hibernate.ddl-auto=create <br>

Run the project with the MyApplication class as your main class 


## Directions to run with a local SQL database (Through IDE)
#### Prerequisites
- SQL must be installed on your machine.
- A local database must exist on your machine with a username and password.
- Maven must be installed on your machine
#### Directions

Open the project with your desired IDE

Copy the following text into the application.properties file (located in src/main/resources/application.properties) <br> 
> spring.datasource.url=jdbc:mysql://localhost:3306/**[DBname]** <br>
spring.datasource.driverClassName=com.mysql.jdbc.Driver <br>
spring.datasource.username=**[DBusername]** <br>
spring.datasource.password=**[DBpassword]** <br>
spring.jpa.hibernate.ddl-auto=update <br>
- Replace [DBname], [DBusername] and [DBpassword] with the corresponding credentials.

Run the project with the MyApplication class as your main class

# Accessing the site
Once you have the app running in the background by following one of the above set of steps, visit http://localhost:8080/aimarket/home

Admin Credentials:
- email: admin@admin.com 
- password: adminpass



