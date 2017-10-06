package me.pujari.publisher;

import com.rabbitmq.client.Channel;
import me.pujari.common.connection.ConnFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author :- Pushkarraj Pujari
 * @Since  :- 29/09/17
 * */
public class Send {
    private static Channel channel;
    private static String exchangeName;
    private static String exchangeType;
    private static String connectionName;
    private static String routingKey;
    private static String message;

    static {
        try {
            connectionName = "Send";
            exchangeType = "topic";
            exchangeName = "T1_Ex";
            routingKey = "R1";
            message = "My First Message";
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
            System.out.println("Sending Message");
            channel.basicPublish(exchangeName,routingKey,null,message.getBytes());
            System.out.println("Message Sent "+message);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
