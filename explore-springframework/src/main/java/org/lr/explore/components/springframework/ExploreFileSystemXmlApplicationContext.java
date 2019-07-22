package org.lr.explore.components.springframework;

import org.lr.explore.components.springframework.bean.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author neil
 * @email lirui10093622@163.com
 * @time 2019-07-22 21:09:12
 */
public class ExploreFileSystemXmlApplicationContext {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
        HelloService bean = context.getBean("helloService", HelloService.class);
        bean.sayHello("张三");
    }
}
