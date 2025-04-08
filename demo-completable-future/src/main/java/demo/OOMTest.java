package demo;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    public static void main(String[] args) {
        Runtime.getRuntime();
    }
}

class Data extends Thread {
    public void run() {
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}