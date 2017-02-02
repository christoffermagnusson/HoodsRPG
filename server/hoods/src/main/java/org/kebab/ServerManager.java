package org.kebab;


import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

public class ServerManager{

	private Set<ClientConnection> clients = new HashSet<>();
	private static ServerManager instance;	

	private ServerManager(){
		
	}

	public static ServerManager getInstance(){
		if(instance==null){
			instance = new ServerManager();
		}
		return instance;
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
