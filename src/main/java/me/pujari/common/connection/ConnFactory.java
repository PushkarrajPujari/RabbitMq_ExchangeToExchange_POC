package me.pujari.common.connection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnFactory {
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    public ConnFactory(String connectionName) throws IOException, TimeoutException {
        this.connectionFactory = new ConnectionFactory();
        this.connection = connectionFactory.newConnection(connectionName);
        this.channel = connection.createChannel();
    }

    public Channel getChannel(){
        return channel;
    }
}
