package org.kebab;


import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

public class ServerManager{

	private Set<ClientConnection> clients = new HashSet<>();

	public ServerManager(){
	}

	public void addClient(ClientConnection client){
	clients.add(client);
	}

	public void removeClient(ClientConnection client){
	clients.remove(client);
	}

	public Set<ClientConnection> getClients(){
	return clients;
	}
	 
}
