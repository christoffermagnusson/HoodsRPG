package org.kebab;

import java.util.UUID;


public class ClientConnection{


private UUID clientId;

	public ClientConnection(UUID clientId){
	if(clientId==null){
	throw new IllegalArgumentException();
	}
	this.clientId=clientId;
	
	}

	public UUID getClientId(){
		return this.clientId;
		
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

		ClientConnection client = (ClientConnection) obj;
		if(!(client.getClientId().equals(this.clientId))){
		return false;
		}
		
		return true;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime*result+((this.clientId==null) ? 0 : clientId.hashCode());
		return result;
	}

}
