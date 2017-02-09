package org.kebab;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



	public enum Command{
	

	/*
	* Incoming commands
	**/
	REQUESTLOGIN(Scope.NONE, Arrays.asList("username","password"),new REQUESTLOGINHandler()),
	CHATMESSAGE(Scope.NONE, Arrays.asList("username" ,"chatMessage"), new CHATMESSAGEHandler()),
	/*
	* Outgoing commands
	**/

	CONFIRMLOGIN(Scope.SELF),
	BROADCASTCHATMESSAGE(Scope.SERVER, Arrays.asList("username","chatMessage"));	

	private Scope scope;
	private List<String> params;
	private IHandler handler;

	private Command(Scope scope, List<String> params, IHandler handler){
		this(scope,params);
		this.handler=handler;
	}
	private Command(Scope scope, IHandler handler){
		this(scope);
		this.handler=handler;
	}
	private Command(Scope scope, List<String> params){
		this(scope);
		this.params=params;
	}
	private Command(Scope scope){
		this.scope=scope;
	}

	public Scope getScope(){
		return scope;
	}
	public List<String> getParams(){
		return params;
	}
	public IHandler getHandler(){
		return handler;
	}
   }


