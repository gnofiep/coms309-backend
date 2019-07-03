package com.studymatcher.messages;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * This is the messages class 
 * @author pmandala
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements Serializable {
	
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id; 
		private String sender;
		private String time;
		private String body;

		/**
		 * This method gets the sender of the message 
		 * @return the sender as a string 
		 */
		public String getSender() {
			return sender;
		}
		
		/**
		 * This method sets the sender of the message 
		 * @param sender Sender
		 */
		public void setSender(String sender) {
			this.sender = sender;
		}
		
		/**
		 * This method gets the time when the message was sent 
		 * @return time as a string 
		 */
		public String getTime() {
			return time;
		}
		
		/**
		 * This method sets the time when the message was sent 
		 * @param time Time
		 */
		public void setTime(String time) {
			this.time = time;
		}
		
		/**
		 * This method gets the body of the message which was sent 
		 * @return body as a string 
		 */
		public String getBody() {
			return body;
		}
		
		/**
		 * This method sets the body of the message which was sent 
		 * @param body Group body
		 */
		public void setBody(String body) {
			this.body = body;
		}
		
		/**
		 * This method gets the id of the message which was sent 
		 * @return id as an int 
		 */
		public int getId() {
			return id;
		}
		
		/**
		 * This method sets the body of the message which was sent 
		 * @param id Message ID
		 */
		public void setId(int id) {
			this.id = id;
		}
		
	}
