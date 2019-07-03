package com.studymatcher.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author apurva
 * Creating the Server End point for the Websocket
 * Class group Messaging Stores the session of each group
 * Messages are stored and redirected as per the 
 * session of the group
 */
@ServerEndpoint("/websocket/group/{Id}/users")
@Component
public class GroupMessaging {
	
	private static Map<Session,String> sessionGroup = new HashMap<>();
	private static Map<String,Set<Session>> groupSessions = new HashMap<>();
	private final Logger logger = LoggerFactory.getLogger(GroupMessaging.class);
	
	/**
	 * 
	 * @param session Session
	 * @param Id Group ID 
	 * @throws IOException IOException
	 * 
	 * On Open first create group session and set if first session on 
	 * group Id, otherwise gets the id of the group session and adds 
	 * the session to the Hashmap 
	 */
	@OnOpen 
	public void onOpen(
		Session session, 
		@PathParam("Id") String Id) throws IOException { 
		logger.info("New session " + session.getId() + " for group " + Id);
		sessionGroup.put(session,Id); 
		
		// FIRST, create group session set if first session on groupid
		if (!groupSessions.containsKey(Id))
			groupSessions.put(Id, new HashSet<Session>());
		groupSessions.get(Id).add(session); 
		
	}
	
	/**
	 * 
	 * @param session Session
	 * @param message Message
	 * @throws IOException IOException
	 * Handles new messages 
	 * Stores the session of the message 
	 * and broadcast the message on groupId 
	 */
	@OnMessage
    public void onMessage(Session session, String message) throws IOException {
        // Handle new messages
    	logger.info("Entered into Message: Got Message:"+message);
    	String groupId = sessionGroup.get(session);
    	System.out.println("Broadcasting to group " + groupId);
    	broadcast(groupId, message);
    }
 
	/**
	 * 
	 * @param session Session
	 * @throws IOException IOException
	 * Close the session and removes it from the HashMap 
	 */
    @OnClose
    public void onClose(Session session) throws IOException{
    	logger.info("Entered into Close");

    	String gId = sessionGroup.remove(session);
    	groupSessions.get(gId).remove(session);
    }
 
    /**
     * 
     * @param session Session
     * @param throwable Throwable
     * Does error Handling
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    	logger.info("Entered into Error");
    }
    
    /**
     * 
     * @param groupId Group ID
     * @param message Message
     * @throws IOException IOException
     * Broadcast the message to a particular group 
     */
    private static void broadcast(String groupId, String message) 
    	      throws IOException {

    	Set<Session> sessions = groupSessions.get(groupId);
    	        sessions.forEach(session -> {
    	                try {
    	                	System.out.println("Messaging " + session.getId());
    	                	session.getBasicRemote().sendText(message);
    	                } catch (IOException e) {
    	                    e.printStackTrace();
    	                }
    	        });
    	    }

}
