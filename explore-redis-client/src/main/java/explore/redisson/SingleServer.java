package explore.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class SingleServer {

    public static RedissonClient createClient() {
        Config config = new Config();

        config.useSingleServer().setAddress("redis://localhost:6379");
        config.useSingleServer().setDatabase(0);
        // config.useSingleServer().setPassword("root");

        return Redisson.create(config);
    }

    public static void longTimeLock() throws Exception {

        RedissonClient client = createClient();

        RLock lock = client.getLock("longTimeLock");
        lock.lock();
        Thread.sleep(5 * 60 * 1000);
        lock.unlock();
    }
}
