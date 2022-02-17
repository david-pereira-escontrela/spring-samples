package com.samples.cafe;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
/**
 * https://spring.io/quickstart
 * http://localhost:8080/hello
 * http://localhost:8080/actuator
 * http://localhost:8080/actuator/bindings
 */
public class CafeApplication {

  private static final Logger LOGGER = getLogger(CafeApplication.class);

  public static void main(String[] args) {

    SpringApplication.run(CafeApplication.class, args);
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

    String toret = String.format("Hello %s!", name);
    LOGGER.info(toret);

    return toret;
  }
}