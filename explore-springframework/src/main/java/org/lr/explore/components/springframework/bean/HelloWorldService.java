package org.lr.explore.components.springframework.bean;

import org.springframework.stereotype.Component;

/**
 * @author neil
 * @email lirui10093622@163.com
 * @time 2019-07-22 21:12:21
 */
@Component
public class HelloWorldService {

    public void sayHello(String name) {
        System.out.println("hello world " + name);
    }
}
