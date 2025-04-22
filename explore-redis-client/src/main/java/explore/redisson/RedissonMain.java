package explore.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonMain {

    public static void main(String[] args) throws Exception {
        Cluster.cluster_high_available();
    }

    public static RedissonClient createClient() {
        Config config = new Config();

        // 单机
        config.useSingleServer().setAddress("redis://localhost:6379");
        config.useSingleServer().setDatabase(0);
        config.useSingleServer().setPassword("root");

        // 复制
        config.useReplicatedServers();
        config.useReplicatedServers().addNodeAddress("redis://localhost:16379", "redis://localhost:26379", "redis://localhost:36379");
        config.useReplicatedServers().setPassword("root");

        // 主从
        config.useMasterSlaveServers().setMasterAddress("redis://localhost:6379");
        config.useMasterSlaveServers().addSlaveAddress("redis://localhost:16379", "redis://localhost:26379", "redis://localhost:36379");
        config.useMasterSlaveServers().setPassword("root");

        // 哨兵
        config.useSentinelServers().addSentinelAddress("redis://localhost:16379", "redis://localhost:26379", "redis://localhost:36379");
        config.useSentinelServers().setPassword("root");

        // 集群
        config.useClusterServers().addNodeAddress("redis://localhost:16379", "redis://localhost:26379", "redis://localhost:36379");
        config.useClusterServers().setPassword("root");

        return Redisson.create(config);
    }
}
