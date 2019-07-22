package org.lr.explore.components.rabbitmq.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author neil
 * @email lirui10093622@163.com
 * @time 2019-07-18 15:14:34
 */
@Slf4j
public class RabbitProducer {

    public static final String EXCHANGE_NAME = "exchange_demo";
    public static final String ROUTING_KEY = "routingkey.*";
    public static final String QUEUE_NAME = "queue_demo";
    public static final String IP_ADDRESS = "192.168.199.104";//"localhost";
    public static final int PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(IP_ADDRESS);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root123");

        Connection connection = connectionFactory.newConnection();
        connection.addShutdownListener((e) -> {
            log.error("connection被关闭", e);
        });
        Channel channel = connection.createChannel();
        // 声明并绑定备份交换器和队列
        channel.exchangeDeclare("exchange.alternate", BuiltinExchangeType.FANOUT);
        channel.queueDeclare("queue.alternate", true, false, false, null);
        channel.queueBind("queue.alternate", "exchange.alternate", "");
        // 声明并绑定死信交换器和队列
        channel.exchangeDeclare("exchange.dlx", BuiltinExchangeType.FANOUT);
        channel.queueDeclare("queue.dlx", true, false, false, null);
        channel.queueBind("queue.dlx", "exchange.dlx", "routingkey");
        // 交换器参数
        Map<String, Object> exchangeArgs = new HashMap<>();
        // 队列参数
        Map<String, Object> queueArgs = new HashMap<>();
        queueArgs.put("x-dead-letter-exchange", "exchange.dlx");
        queueArgs.put("alternate-exchange", "exchange.alternate");
        // 声明交换器
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, exchangeArgs);
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, queueArgs);
        // 绑定队列
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        channel.addShutdownListener((e) -> {
            log.error("channel被关闭", e);
        });
        channel.addReturnListener(
            (int replyCode, String replyText, String exchange, String routingKey, BasicProperties properties, byte[] body) -> {
                log.warn("receive a return message. replyCode: {}, replyText: {}, exchange: {}, routingKey: {}, properties: {}, body: {}",
                    replyCode, replyText, exchange, routingKey, properties, new String(body));
            }
        );
        // channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                log.info("handleAck deliveryTag: {}, multiple: {}", deliveryTag, multiple);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                log.info("handleNack deliveryTag: {}, multiple: {}", deliveryTag, multiple);
            }
        });

        log.info("RabbitProducer started");

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String message;
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder()
            .deliveryMode(2)
        //    .expiration("1000")
            .build();
        // basicProperties = MessageProperties.PERSISTENT_TEXT_PLAIN;
        // channel.txSelect();
        while (!(message = consoleReader.readLine()).equals("stop")) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY+".unrouted", basicProperties, message.getBytes());
            // channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, true, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            // channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, true, true, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            // channel.txCommit();
        }

        // 关闭资源
        consoleReader.close();
        channel.close();
        connection.close();
    }
}
