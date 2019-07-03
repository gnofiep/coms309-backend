package com.studymatcher.meeting;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studymatcher.group.Group;

/**
 * Meeting class
 * @author dillonpeters
 *
 */
@Entity
@Table(name = "Meeting")
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String meeting_time;

	private String meeting_date;

	/**
	 * This method get the id of the meeting
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method get the meeting time
	 * @return the meeting_time
	 */
	public String getMeeting_time() {
		return meeting_time;
	}

	/**
	 * This method set the meeting  time
	 * @param meeting_time the meeting_time to set
	 */
	public void setMeeting_time(String meeting_time) {
		this.meeting_time = meeting_time;
	}

	/**
	 * This method get the meeting date
	 * @return the meeting_date
	 */
	public String getMeeting_date() {
		return meeting_date;
	}

	/**
	 * This method set the meeting date
	 * @param meeting_date the meeting_date to set
	 */
	public void setMeeting_date(String meeting_date) {
		this.meeting_date = meeting_date;
	}

	/**
	 * One group must have only one meeting Hence One-to-One mapping
	 * @JoinColumn annotation is used to join user with group meeting id. Spring
	 *             will automatically add the column called "user_id to group
	 *             meeting table.
	 */
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "meeting")
	private Group group;

	/**
	 * This method set the group with the meeting
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * This method return the group of the meeting
	 * @return the group of the meeting
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 *  Describes the mapping between the group and meeting 
	 * A DTO is used to return meeting for a group 
	 * @return map with all the selected details of the meeting
	 */
	public Map<String, Object> toDTO() {
		Map<String, Object> map = new HashMap<>();
		map.put("meeting_time", this.meeting_time);
		map.put("meeting_date", this.meeting_date);
		return map;
	}
}
