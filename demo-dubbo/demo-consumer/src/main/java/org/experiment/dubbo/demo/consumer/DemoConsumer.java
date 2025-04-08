package org.experiment.dubbo.demo.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author neil
 * @email lirui10093622@163.com
 * @time 2018-09-04 18:18:15
 */
@EnableDubboConfiguration
@SpringBootApplication
//@EnableHystrix
//@EnableCircuitBreaker
//@EnableHystrixDashboard
public class DemoConsumer {

  public static void main(String[] args) {
    SpringApplication.run(DemoConsumer.class, args);
  }
}