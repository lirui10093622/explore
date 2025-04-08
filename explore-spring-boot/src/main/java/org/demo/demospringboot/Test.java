package org.demo.demospringboot;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    private static Object lock = new Object();

    private static AtomicInteger condition = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            private long seq = 0;

            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        condition.getAndSet(1);
                        System.out.println("wait()方法即将调用");
                        lock.wait(60 * 1000);
                        System.out.println("wait()方法已经退出");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    /*while (true) {
                        condition.getAndIncrement();
                    }*/
                }
            }
        });
        t1.setName("用户线程1");

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            public void run() {

                synchronized (lock) {
                    condition.getAndSet(2);
                    /*try {
                        Thread.sleep(100 * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }*/
                    while (true) {
                        System.out.println(condition.get());
                    }
                }
                // System.out.println("线程即将退出");
            }
        });
        t2.setName("用户线程2");
        t2.start();
    }
}
