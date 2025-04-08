package org.experiment.es;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ElasticSearchApplication {

  public static void main(String[] args) {
    SpringApplication.run(ElasticSearchApplication.class);
  }
}