/**
This file is part of the course CSC5002.

Copyright (C) 2017-2019 Télécom SudParis

This is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This software platform is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with the muDEBS platform. If not, see <http://www.gnu.org/licenses/>.

Initial developer(s): Denis Conan
Contributor(s):
 */
package vlibtour.vlibtour_client_group_communication_system;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
/**
 * This class is the client application of the tourists.
 * 
 * @author Denis Conan
 */
public class VLibTourGroupCommunicationSystemClient {
	/**
	 * the name of the exchange
	 */
	private static String EXCHANGE_NAME= null; 
	/**
	 * the name of the queue
	 */
	private Channel channel;
	private String queueName;
	private String routingKey ;
	private String bindingKey;
	private Consumer consumer;
	
	public VLibTourGroupCommunicationSystemClient (String userId , String groupId , String tourId ) throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    channel = connection.createChannel(); 
	    EXCHANGE_NAME= groupId+userId;
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
	    queueName = tourId+"_"+userId;
	    routingKey = userId+"."+"all"+"."+String.class;
	    bindingKey =tourId+"_"+userId;
	    
	}
	
	public void publish (String message) throws UnsupportedEncodingException, IOException {
		
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
	}

    public  void addConsumer(Consumer consumer) {
    	
    	  consumer = new DefaultConsumer(channel);
    	  this.consumer=consumer;
    }
    
    public void startConsumption () throws IOException {
    	boolean autoAck = true;
    	channel.basicConsume(queueName, autoAck,consumer);
    }
    
    
    
	
}
