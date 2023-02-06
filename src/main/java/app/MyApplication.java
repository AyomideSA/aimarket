package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 1. Run App in Intellj or Through command line
// For command line, go to project source (aimarket folder) and then use the following command
//  mvn spring-boot:run

// 2. Go to this link: http://localhost:8080/.  A webpage should appear there now.

@SpringBootApplication
public class MyApplication {
  public static void main(String[] args) {
    SpringApplication.run(MyApplication.class, args);
  }
}

//Very important stuff here
