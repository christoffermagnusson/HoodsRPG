package org.kebab;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Message{


	private ClientConnection sender;
	private Command command;
	private Map<String,Object> params;

	
	

	public Message(Command command, Map<String,Object> params, ClientConnection sender){
		this(command,params);
		this.sender=sender;
	}
	public Message(Command command, Map<String,Object> params){
		this(command);
		this.params=params;
	}
	public Message(Command command, ClientConnection sender){
		this(command);
		this.sender=sender;
	}
	public Message(Command command){
		this.command=command;
	}

	public Command getCommand(){
		return command;
	}
	public Map<String,Object> getParams(){
		return params;
	}
	public ClientConnection getSender(){
		return sender;
	}

	public void setSender(ClientConnection sender){
		this.sender=sender;
	}

	
	public void send(){
		if(sender == null){
			return;
		}
		try{
			this.sender.broadcast(MessageTransformer.prepare(this), command.getScope());
		}
		catch(Exception e){  // make new Exception
		Logger.getLogger(Message.class.getName()).warning(e.getMessage());
		}
	}





}
