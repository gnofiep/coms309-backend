package com.studymatcher.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author pmandala
 *	This is the message repository class 
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
//	Optional<Message> findByMessage(String message); 

}