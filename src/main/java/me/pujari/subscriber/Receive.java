package me.pujari.subscriber;

import com.rabbitmq.client.*;
import me.pujari.common.connection.ConnFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * @Author : Pushkarraj Pujari
 * @Since  : 03/10/17
 * */
public class Receive {
    private static Channel channel;
    private static String exchangeName;
    private static String exchangeType;
    private static String connectionName;
    private static String routingKey;
    private static String queueName;


    static {
        try {
            connectionName = "Receive";
            exchangeType = "topic";
            exchangeName = "T1_Ex";
            routingKey = "R1";
            channel = new ConnFactory(connectionName).getChannel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            channel.exchangeDeclare(exchangeName,exchangeType);
            queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName,exchangeName,routingKey);
            System.out.println("Creating consumer ..... ");
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException, UnsupportedEncodingException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                }
            };
            System.out.println("Ready to consume");
            channel.basicConsume(queueName,true,consumer);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
