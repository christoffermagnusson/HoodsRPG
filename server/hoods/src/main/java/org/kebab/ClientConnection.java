package org.kebab;

import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

import com.corundumstudio.socketio.SocketIOClient;

public class ClientConnection{


private SocketIOClient client;

	public ClientConnection(SocketIOClient client){
	if(client==null){
	throw new IllegalArgumentException();
	}
	this.client=client;
	
	}

	public SocketIOClient getClient(){
		return this.client;
		
	}

	public void broadcast(String message, Scope scope) throws MessageException{
		if(scope == scope.NONE || scope == null){
			return;
		}
		
		Set<ClientConnection> recipients;

		if(scope == scope.SERVER){
			recipients = ServerManager.getInstance().getClients();
		}
		else if (scope == scope.SELF){
			recipients = new HashSet<>();
			recipients.add(this);
		} else{
		return;
		}

		for(ClientConnection clientConnection : recipients){
			JSONEvent event = new JSONEvent(message);
			clientConnection.client.sendEvent("HoodsEvent",event);
		}	
	}
	
	@Override
	public boolean equals(Object obj){
		if(this == obj){
		return true;
		}
		if(obj==null){
		return false;
		}
		if(!(obj instanceof ClientConnection)){
		return false;
		}

		ClientConnection otherClient = (ClientConnection) obj;
		if(!(otherClient.client.equals(this.client))){
		return false;
		}
		
		return true;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime*result+((this.client==null) ? 0 : client.hashCode());
		return result;
	}

}
