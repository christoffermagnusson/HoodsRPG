package org.kebab;

import java.util.logging.Logger;

public class FrontHandler{

	private static FrontHandler instance;

	private FrontHandler(){}

	public static FrontHandler getInstance(){
		if(instance ==null){
			instance = new FrontHandler();
		}
	return instance;
	}

	public void handleMessage(ClientConnection clientConnection, String rawMessage){
		try{
			Logger.getLogger(FrontHandler.class.getName()).fine(rawMessage);
			
			Message message = MessageTransformer.parse(rawMessage);
			message.setSender(clientConnection);
			
			Command command = message.getCommand();
			IHandler handler = command.getHandler();
			if(handler != null){
				handler.handle(message);
			}

			String data = (command.getParams()!=null) ? MessageTransformer.prepare(message) : rawMessage;
			clientConnection.broadcast(data, command.getScope());
		} catch(MessageException me){
			Logger.getLogger(FrontHandler.class.getName()).warning(me.getMessage());
		}
			
	}

}

