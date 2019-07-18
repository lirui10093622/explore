package org.lr.explre.components.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author neil
 * @email lirui10093622@163.com
 * @time 2019-07-18 15:14:34
 */
public class RabbitProducer {

    public static final String EXCHANGE_NAME = "exchange_demo";
    public static final String ROUTING_KEY = "routingkey_demo";
    public static final String QUEUE_NAME = "queue_demo";
    public static final String IP_ADDRESS = "localhost";
    public static final int PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(IP_ADDRESS);
        connectionFactory.setPort(PORT);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        String message = "hello world";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        // 关闭资源
        channel.close();
        connection.close();
    }
}
