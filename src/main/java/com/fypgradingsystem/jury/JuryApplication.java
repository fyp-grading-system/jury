package com.fypgradingsystem.jury;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class JuryApplication {

  public static void main(String[] args) {
    SpringApplication.run(JuryApplication.class, args);
  }
}
