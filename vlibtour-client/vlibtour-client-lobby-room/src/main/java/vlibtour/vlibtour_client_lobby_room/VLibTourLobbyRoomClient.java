/**
This file is part of the course CSC5002.

Copyright (C) 2019 Télécom SudParis

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
package vlibtour.vlibtour_client_lobby_room;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.tools.jsonrpc.JsonRpcClient;
import com.rabbitmq.tools.jsonrpc.JsonRpcException;
import vlibtour.vlibtour_lobby_room_api.VLibTourLobbyService;

/**
 * This class is the client application of the tourists.
 * 
 * @author Denis Conan
 */
public class VLibTourLobbyRoomClient {
	/**
	 * the connection to the RabbitMQ broker.
	 */
	private Connection connection;
	/**
	 * the channel for producing and consuming.
	 */
	private Channel channel;
	/**
	 * the RabbitMQ JSON RPC client.
	 */
	private JsonRpcClient jsonRpcClient;
	/**
	 * the Fibonacci service.
	 */
	private VLibTourLobbyService client;

	/**
	 * default constructor of the RPC client.
	 * 
	 * @throws IOException
	 *             the communication problems.
	 * @throws TimeoutException
	 *             broker to long to connect to.
	 * @throws JsonRpcException
	 *             JSON problem in marshaling or un-marshaling.
	 */
	public VLibTourLobbyRoomClient() throws IOException, TimeoutException, JsonRpcException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		jsonRpcClient = new JsonRpcClient(channel, client.EXCHANGE_NAME, client.BINDING_KEY);
		client = (VLibTourLobbyService) jsonRpcClient.createProxy(VLibTourLobbyService.class);
	}

	/**
	 * calls the create group service.
	 * 
	 * @param groupId
	 *            the id of the group to create.
	 * @param userId
	 *            the id of the creator.
	 * @return the url to connect to communication system
	 */
	public String createGroupAndJoinIt(final String groupId, final String userId ) {
		System.out.println(" [x] Requesting createGroupAndJoinIt (" + groupId + userId+ ")");
		String result = client.createGroupAndJoinIt(groupId, userId);
		System.out.println(" [.] Got '" + result + "'");
		return result;
	}

	/**
	 * calls the join group service.
	 * 
	 * @param groupId
	 *            the id of the group to create.
	 * @param userId
	 *            the id of the creator.
	 * @return the url to connect to communication system
	 */
	public String join(final String groupId, final String userId ) {
		System.out.println(" [x] Requesting join (" + groupId + userId+ ")");
		String result = client.joinAGroup(groupId, userId);
		System.out.println(" [.] Got '" + result + "'");
		return result;
	}
	/**
	 * closes the JSON RPC client, the channel and the connection with the broker.
	 * 
	 * @throws IOException
	 *             communication problem.
	 * @throws TimeoutException
	 *             broker to long to communicate with.
	 */
	public void close() throws IOException, TimeoutException {
		jsonRpcClient.close();
		if (channel != null) {
			channel.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	/**
	 * the main method of the example.
	 * 
	 * @param argv
	 *            the command line argument is the number of the call.
	 * @throws Exception
	 *             communication problem with the broker.
	 */
	public static void main(final String[] argv) throws Exception {
		VLibTourLobbyRoomClient rpcClient = new VLibTourLobbyRoomClient();
		rpcClient.createGroupAndJoinIt(argv[0], argv[1]);
		rpcClient.close();
	}
}
