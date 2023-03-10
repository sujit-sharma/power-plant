package com.sujit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sujit")
public class PowerPlantApplication {

  public static void main(String[] args) {
    SpringApplication.run(PowerPlantApplication.class, args);
  }
}
