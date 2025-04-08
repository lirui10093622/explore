package org.experiment.dubbo.demo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.expirement.dubbo.demo.api.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neil
 * @email lirui10093622@163.com
 * @time 2018-09-04 18:35:13
 */
@RestController
@RequestMapping(value = "/api")
public class DemoController {

  @Reference(check = false)
  private DemoService demoService;

  //@HystrixCommand(fallbackMethod = "sayHelloFallback")
  //@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
  @RequestMapping(value = "hello")
  public String sayHello(String name) {
    return demoService.sayHello(name);
  }

  public String sayHelloFallback(String name) {
    return "hi " + name;
  }
}