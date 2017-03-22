package com.dragon.study.springboot.tutorial.param;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

/**
 * Created by dragon on 2017/3/19.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class })
public class Application {
  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.run(args);
  }
}
