package org.experiment.dubbo.demo.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author neil
 */
@SpringBootApplication
@EnableDubboConfiguration
public class DemoProvider {

  public static void main(String[] args) {
    SpringApplication.run(DemoProvider.class, args);
  }
}