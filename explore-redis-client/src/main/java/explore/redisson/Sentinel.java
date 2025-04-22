package explore.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class Sentinel {

    public static RedissonClient createClient() {
        Config config = new Config();
        config.useSentinelServers().addSentinelAddress("redis://localhost:16379", "redis://localhost:26379", "redis://localhost:36379");
        // config.useSentinelServers().setPassword("root");
        return Redisson.create(config);
    }

    
}