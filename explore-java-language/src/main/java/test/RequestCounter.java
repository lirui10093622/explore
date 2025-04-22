package test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class RequestCounter {

    /**
     * 在内存中统计每个key的请求次数，假设key的基数不超过1000。
     * 比如： incRequestCount(“k1”);
     * incRequestCount(“k1”);
     * getRequestCount(“k1”)==2;
     * 注意：这些方法可能会被多个线程同时调用。
     */
    private static final Map<String, Long> counter = new ConcurrentHashMap<String, Long>();

    public void incRequestCount(String key) {
        synchronized (counter) {
            Long count = counter.getOrDefault(key, 0L) + 1;
            counter.put(key, count);
        }
    }

    public long getRequestCount(String key) {
        return counter.getOrDefault(key, 0L);
    }
}