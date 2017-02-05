package org.kebab;

import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.AckRequest;

import java.util.Iterator;

public class ServerLauncher {

	
	
    public static void main(String[] args) throws InterruptedException {
	

	System.out.println("Starting server...");

        Configuration config = new Configuration();
        config.setHostname("ec2-34-196-203-68.compute-1.amazonaws.com");
        config.setPort(8000);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("JSONEvent", JSONEvent.class, new DataListener<JSONEvent>() {
            @Override
            public void onData(SocketIOClient client, JSONEvent data, AckRequest ackRequest) {
                System.out.println("Message received from: " + client.getRemoteAddress().toString());
		System.out.println(data.getRawData());
        	FrontHandler.getInstance().handleMessage(new ClientConnection(client),data.getRawData());	


		 }
        });

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("Client connected: " + client.getRemoteAddress().toString());
		ServerManager.getInstance().addClient(new ClientConnection(client));
		}
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("Client disconnected: " + client.getRemoteAddress().toString());
            	ServerManager.getInstance().removeClient(new ClientConnection(client));
		}
        });
	

        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }
	
	}
	
