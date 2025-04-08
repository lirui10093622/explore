package org.experiment.dubbo.demo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.expirement.dubbo.demo.api.DemoService;
import org.springframework.stereotype.Component;

@Component
@Service
public class DemoServiceImpl implements DemoService {

  @Override
  public String sayHello(String msg) {
    return "hello " + msg;
  }
}