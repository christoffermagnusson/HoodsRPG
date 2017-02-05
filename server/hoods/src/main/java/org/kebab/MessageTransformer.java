package org.kebab;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


public class MessageTransformer{


	public static String prepare(Message message) throws MessageException{
		Command command = message.getCommand();
		Map<String,Object> params = message.getParams();
		List<String> definedParams = command.getParams();

		JSONObject obj = null;
		if(definedParams != null){
			if(definedParams.size()!=params.size()){
				throw new MessageException("Wrong paramater amount : "+params.toString());
			}
			obj = new JSONObject();
			for(String key : definedParams){
				Object value = params.containsKey(key) ? params.get(key) : null;
				obj.put(key,value);
			} 
		}

	JSONObject result = new JSONObject();
	result.put("command",command.toString());
	result.put("params",obj);
	return result.toString();
	}

	
	public static Message parse(String rawMessage)throws MessageException{
		JSONParser parser = new JSONParser();
		JSONObject jsonObj;
		
		try{
			Object obj = parser.parse(rawMessage);
			jsonObj = (JSONObject) obj;
		} catch(ParseException pe){
			throw new MessageException("Parsing error : "+pe.getMessage());
		}
		  catch(Exception e){
			throw new MessageException("General error : "+e.getMessage());
		}

		String rawCommand;
		Object rawParams;
		
		if(jsonObj.containsKey("command") && jsonObj.containsKey("params")){
			rawCommand = (String) jsonObj.get("command");
			rawParams = jsonObj.get("params");
		} else {
			throw new MessageException("Incorrect message format : " + jsonObj.toString());
		}

		

		Command command;
		try{
			command = Command.valueOf(rawCommand.toUpperCase());
		} catch(IllegalArgumentException iae){
			throw new MessageException("Incorrect format of command : "+ rawCommand);
		}

		if(command.getParams() != null){
		JSONObject jsonParams;
		if(rawParams !=null){	
			try{
				jsonParams = (JSONObject) rawParams;
			} catch(ClassCastException cce){
				throw new MessageException("Incorrect parameters : "+rawParams.toString());
			}
		} else {
			throw new MessageException("Empty params");
  			}
		 if(command.getParams().size() != jsonParams.size()){
			throw new MessageException("Wrong parameter amount : " + jsonParams.size());
		} 
		
		
		Map<String,Object> messageParams = new HashMap<>();
		for(String param : command.getParams()){
			Object value = (jsonParams.containsKey(param)) ? jsonParams.get(param) : null;
			messageParams.put(param, value);
		}
		return new Message(command, messageParams);
		} else {
			return new Message(command);
		}	

}		



}
