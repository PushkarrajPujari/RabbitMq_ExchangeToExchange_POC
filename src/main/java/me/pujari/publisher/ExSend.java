package me.pujari.publisher;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import me.pujari.common.connection.ConnFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ExSend {
    private static Channel channel;
    private static String exchangeName;
    private static String exchangeType;
    private static String connectionName;
    private static String routingKey;
    private static String message;

    static {
        try {
            connectionName = "EXSend";
            exchangeType = "topic";
            exchangeName = "T2_Ex";
            routingKey = "R1";
            message = "My First Message From Ex to Ex";
            channel = new ConnFactory(connectionName).getChannel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            channel.exchangeDeclare(exchangeName,exchangeType);
            channel.exchangeBind("T1_Ex",exchangeName,"R1");
            channel.basicPublish(exchangeName,routingKey,null,message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
