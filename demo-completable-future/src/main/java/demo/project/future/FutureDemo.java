package demo.project.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.google.common.base.Stopwatch;
import demo.project.future.vendor.*;

public class FutureDemo {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Stopwatch watch = Stopwatch.createStarted();
        // 准备
        List<Callable<RequestVendorResult>> callableList = prepare();
        // 提交
        List<Future<RequestVendorResult>> futureList = submit(callableList);
        // 处理
        List<RequestVendorResult> resultList = handle(futureList);

        watch.stop();

        System.out.printf(watch.elapsed(TimeUnit.MILLISECONDS) + "ms");

        threadPool.shutdownNow();
    }

    private static List<Callable<RequestVendorResult>> prepare() {
        List<Callable<RequestVendorResult>> callableList = new ArrayList<>();
        // 上海迪士尼
        RequestVendorParam shangHaiDisneyParam = new RequestVendorParam();
        VendorShangHaiDisney shangHaiDisney = new VendorShangHaiDisney();

        // 北京环球影城
        RequestVendorParam universalStudiosBeijingParam = new RequestVendorParam();
        VendorUniversalStudiosBeijing universalStudiosBeijing = new VendorUniversalStudiosBeijing();

        // 黄山
        RequestVendorParam yellowMountainParam = new RequestVendorParam();
        VendorYellowMountain yellowMountain = new VendorYellowMountain();

        callableList.add(() -> shangHaiDisney.request(shangHaiDisneyParam));
        callableList.add(() -> universalStudiosBeijing.request(universalStudiosBeijingParam));
        callableList.add(() -> yellowMountain.request(yellowMountainParam));

        return callableList;
    }

    private static List<Future<RequestVendorResult>> submit(List<Callable<RequestVendorResult>> callableList) {
        List<Future<RequestVendorResult>> futureList = new ArrayList<>();
        for (Callable<RequestVendorResult> callable : callableList) {
            futureList.add(threadPool.submit(callable));
        }

        return futureList;
    }

    private static List<RequestVendorResult> handle(List<Future<RequestVendorResult>> futureList) throws ExecutionException, InterruptedException {
        List<RequestVendorResult> resultList = new ArrayList<>();
        for (Future<RequestVendorResult> future : futureList) {
            if (future.isDone()) {
                resultList.add(future.get());
            }
        }
        return resultList;
    }
}
