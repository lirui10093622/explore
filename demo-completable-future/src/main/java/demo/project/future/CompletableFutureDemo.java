package demo.project.future;

import demo.project.future.vendor.RequestVendorResult;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<RequestVendorResult> future = new CompletableFuture<>();
        future.isDone();

    }
}
