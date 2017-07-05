package com.webSocket.OrdServer;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/OrdServer")
public class OrdServer {
	private static final Set<Session> ordSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		ordSessions.add(userSession);
		System.out.println("連線成功");
	}

	@OnMessage
	public void onMessage(Session userSession, String signal) {
		for (Session session : ordSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(signal);
		}
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		ordSessions.remove(userSession);
		System.out.println("close");
	}

}
