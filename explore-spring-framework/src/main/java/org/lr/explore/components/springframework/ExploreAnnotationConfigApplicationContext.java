package org.lr.explore.components.springframework;

import org.lr.explore.components.springframework.bean.HelloWorldService;
import org.lr.explore.components.springframework.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author neil
 * @email lirui10093622@163.com
 * @time 2019-07-22 21:09:12
 */
public class ExploreAnnotationConfigApplicationContext {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        HelloWorldService bean = context.getBean("helloWorldService", HelloWorldService.class);
        bean.sayHello("张三");
    }
}
