package explore.jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisMain {

    public static void main(String[] args) {
        JedisPool masterJedisPool = new JedisPool("127.0.0.1", 16379);
        Jedis masterJedis = masterJedisPool.getResource();

        String value = new SimpleDateFormat().format(new Date());
        masterJedis.set("key", value);
        System.out.println(value);

        JedisPool slaveJedisPool = new JedisPool("127.0.0.1", 26379);
        Jedis slaveJedis = slaveJedisPool.getResource();
        String value2 = slaveJedis.get("key");

        System.out.println(value2);

        masterJedis.close();
        masterJedisPool.close();
        slaveJedis.close();
        slaveJedisPool.close();
    }
}
