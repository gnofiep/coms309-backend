package com.studymatcher.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 
 * @author apurva
 * Group Messaging Configuration 
 */
@Configuration
public class GroupMessagingConfig {
	
	
	/**
	 * 
	 * @return Server EndPoint Exporter()
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter(){  
        return new ServerEndpointExporter();  
    }  

}
