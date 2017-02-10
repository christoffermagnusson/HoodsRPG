package org.kebab;



public class CHATMESSAGEHandler implements IHandler{


	public CHATMESSAGEHandler(){}

		
	@Override
	public void handle(Message message){
		
		Message response = new Message(Command.BROADCASTCHATMESSAGE, message.getParams(),message.getSender());
		response.send();	
	}					

}
