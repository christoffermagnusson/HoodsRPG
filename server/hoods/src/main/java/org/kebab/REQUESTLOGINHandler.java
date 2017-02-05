package org.kebab;

public class REQUESTLOGINHandler implements IHandler{
	

	@Override
	public void handle(Message message){
		Message response = new Message(Command.CONFIRMLOGIN,message.getSender());
		response.send();
	}
	
}
