package com.studymatcher.tasks;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.studymatcher.users.User;

/**
 * this task class is an entity of task that has a many-to-one relationship
 * with user 
 * @author pfong
 *
 */
@Entity
@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 

	private String task_date;
	private String task_name;
	private Boolean task_completed;
	private String task_description;

	/**
	 * This method get the id of the task
	 * @return the id of the task
	 */
	public int getId() {
		return id;
	}

	/**
	 * this method set the id of the task
	 * @param id Id of the task
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * this method get the date of the task
	 * @return date of the task
	 */
	public String getTask_date() {
		return task_date;
	}

	/**
	 * this method set the date of the task
	 * @param task_date Date String of the task
	 */
	public void setTask_date(String task_date) {
		this.task_date = task_date;
	}

	/**
	 * this method get the date of the task
	 * @return date of the task
	 */
	public String getTask_name() {
		return task_name;
	}

	/**
	 * this method set the name of the task
	 * @param task_name Name of the task
	 */
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	/**
	 * this method get to check whether the task is completed or not
	 * @return true if task is completed, otherwise false 
	 */
	public Boolean getTask_completed() {
		return task_completed;
	}

	/**
	 * this method mark the task whether it has been completed or not
	 * @param task_completed Has the task been completed
	 */
	public void setTask_completed(Boolean task_completed) {
		this.task_completed = task_completed;
	}

	/**
	 * this method get the description of the task
	 * @return the description of task
	 */
	public String getTask_description() {
		return task_description;
	}

	/**
	 * this method set the description of the task
	 * @param task_description New task description
	 */
	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}
	 
	/**
	 * this method help to return specific details of the task, 
	 * such that it would not include the user id, 
	 * just the id, date, name, description, and the check of completeness 
	 * of the task.
	 * @return details of the task
	 */
	public Map<String, Object> toDTO(){
		Map<String, Object> map = new HashMap<>();
		map.put("id", this.id);
		map.put("task_date", this.task_date);
		map.put("taskName", this.task_name);
		map.put("task_completed", this.task_completed);
		map.put("task_description", this.task_description);
		return map; 
	}
	
	/**
	 * many tasks must have only one user
	 * Hence Many-to-One mapping
	 * @JoinColumn annotation is used to join 
	 * user with task id. Spring will
	 * automatically add the column called 
	 * user_id to Task table, where 
	 * user id and task id are
	 * associated 
	 */
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	private User user; 
	
	/**
	 * this method get all the details of the user 
	 * that associate with the task
	 * @return the user
	 */
	public User getUser() { 
		return user;
	}

	/**
	 * this method will set the task to associate with a user 
	 * @param user User we are setting a task for
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}