package com.studymatcher.group;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.studymatcher.messages.Message;
import com.studymatcher.meeting.Meeting;
import com.studymatcher.users.User;

/**
 * This group class is an entity of group that has a many-to-many relationship
 * with user, thus spring will form a join table between user and group
 * 
 *
 */
@Entity
@Table(name="sgroup")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	private String group_name;

	/**
	 * This method get the group name of the group
	 * @return the group name
	 */
	public String getGroup_name() {
		return group_name;
	}

	/**
	 * This method set the group name of the group
	 * @param group_name Group Name
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	} 
	
	/**
	 * This method get the id of the group
	 * @return Group Id
	 */
	public Integer getId() {
		return id; 
	}
	
	/**
	 * This method set the id of the group
	 * @param id Group Id
	 */
	public void setId(Integer id) {
		this.id = id; 
	}
	/**
	 * Here use JsonIgnore and setting fetch to lazy to prevent 
	 * infinitely circulation between group and user
	 */
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "Groups")
	private Set<User> users = new HashSet<>(); 

	/**
	 * This method get the users from the group
	 * @return the users Group user
	 */
	public Set<User> getUsers(){
		return users;
	}
	/**
	 * This method set users into a group
	 * @param users Group User 
	 */
	public void setUsers(Set<User> users) { 
		this.users = users; 
	}
	
	/**
	 * This method will just get id and group name of the group.
	 * @return group with just id and group name
	 */
	public Map<String, Object> toDTO(){
		Map<String, Object> map = new HashMap<>();
		map.put("id", this.id);
		map.put("group_name", this.group_name);	
		return map; 
	}
	
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="group_id")
	private Set<Message> message = new HashSet<>();

	public Set<Message> getMessage() {
		return message;
	}

	public void setMessage(Set<Message> message) {
		this.message = message;
	}



	/**
	 * Describes the mapping between the group and the meeting 
	 * FetchType.LAZY is necessary here to prevent Spring from infinitely fetching group and meeting
	 * (circular reference). JSON ignore is also necessary to prevent infinitely 
	 * serializing meeting and group. A DTO is used to return group for a meeting (or meeting for a group) 
	 */
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="meeting_id")
	private Meeting meeting;

	/**
	 * This method get the meeting of the group
	 * @return the meeting
	 */
	public Meeting getMeeting() {
		return meeting;
	}

	/**
	 * This method set the meeting for the group
	 * @param meeting the meeting to set
	 */
	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
	
	
}
