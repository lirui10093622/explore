package explore.redisson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class Cluster {

    public static RedissonClient createClient() {
        Config config = new Config();
        config.useClusterServers().addNodeAddress("redis://localhost:16379", "redis://localhost:36379");
        // config.useClusterServers().setPassword("root");

        return Redisson.create(config);
    }

    public static void cluster_high_available() throws Exception {
        {
            RedissonClient client = createClient();

            {
                RLock lock = client.getLock("lock");
                lock.lock();
                System.out.println("锁成功");
                Thread.sleep(10 * 1000);
                System.out.println("锁释放");
                lock.unlock();
            }

            System.out.println("请先把主redis服务停掉......");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            reader.readLine();
            {
                RLock lock = client.getLock("lock");
                lock.lock();
                System.out.println("锁成功");
                Thread.sleep(10 * 1000);
                System.out.println("锁释放");
                lock.unlock();
            }
        }
    }
}
