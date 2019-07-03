package com.studymatcher.users;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.studymatcher.group.Group;
import com.studymatcher.profile.Profile;
import com.studymatcher.tasks.Task;

/**
 * This is the user class with user information 
 * @author pmandala
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
	  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String username; 
	private String firstname; 
	private String lastname; 
	private String school; 
	private String profession; 
	private String password; 
	private String cpassword;
	private String email; 
	
	/**
	 * This method gets the username 
	 * @return username as a string 
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method sets the username 
	 * @param username username of the user 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * This method gets the first name
	 * @return first name as a string 
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * This method sets the first name 
	 * @param firstname first name of the user 
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * This method gets the last name
	 * @return lastname as a string 
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * This method sets the last name 
	 * @param lastname last name of the user 
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * This method gets the school 
	 * @return school as a string
	 */
	public String getSchool() {
		return school;
	}
	
	/**
	 * This method sets the school 
	 * @param school school of the user 
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	
	/**
	 * This method gets the Profession
	 * @return Profession as a string 
	 */
	public String getProfession() {
		return profession;
	}
	
	/**
	 * This method sets the Profession 
	 * @param profession profession of the user 
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	/**
	 * This method gets the password 
	 * @return password as a string
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * This method sets the password
	 * @param password password of the user 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * This method gets the confirmed password 
	 * @return confirmed password as a string 
	 */
	public String getCpassword() {
		return cpassword;
	}
	
	/**
	 * This method sets the confirmed password 
	 * @param cpassword confirmed password of the user 
	 */
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	} 
	
	/**
	 * Describes the mapping between the user and the group 
	 * FetchType.LAZY is necessary here to prevent Spring from infinitely fetching user and group
	 * (circular reference). JSON ignore is also necessary to prevent infinitely 
	 * serializing user and group. A DTO is used to return groups for a user (or user for a group) 
	 */
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="group_user",
			joinColumns = {@JoinColumn(name="user_id")},
			inverseJoinColumns = {@JoinColumn(name = "group_id")})
	private Set<Group> Groups = new HashSet<>();
	
	/**
	 * This method gets the groups 
	 * @return groups as a Set Group
	 */
	public Set<Group> getGroups(){
		return Groups; 
	}
	
	/**
	 * This method sets the groups 
	 * @param Groups groups of the user 
	 */
	public void setGroups(Set<Group> Groups) {
		this.Groups = Groups; 
	}
	
	/**
	 *  This method is the toDTO for id and username 
	 * @return map 
	 */
	public Map<String, Object> toDTO(){
		Map<String, Object> map = new HashMap<>(); 
		map.put("id",  this.id); 
		map.put("username",  this.username); 
		map.put("email", this.email);
		return map; 
	}
	
	/**
	 * Describes the mapping between the user and profile 
	 * A DTO is used to return profiles for a user 
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="profile_id")
    private Profile profile;
	
	public Map<String, Object>toProfileDTO(){
		Map<String, Object>map = new HashMap<>();
		map.put("id", this.id);
		map.put("username", this.username);
		map.put("firstname", this.firstname);
		map.put("lastname", this.lastname);
		map.put("school", this.school);
		map.put("profession", this.profession);
		return map; 
	}
	
	/**
	 * This method gets the id 
	 * @return id as a int
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This method sets the id 
	 * @param id id of the user 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * This method gets the profile 
	 * @return profile 
	 */
	public Profile getProfile() {
		return profile;
	}
	
	/**
	 * This method sets the profile 
	 * @param profile profile of the user 
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	/**
	 * This metod gets the email
	 * @return email as a string 
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * This method sets the email 
	 * @param email email of the user 
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/**
	 * Describes the mapping between the user and the task 
	 * FetchType.LAZY is necessary here to prevent Spring from infinitely fetching user and task
	 * (circular reference). A DTO is used to return tasks for a user (or user for a task) 
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user", cascade = CascadeType.ALL)
	/**
	 * Describes the mapping between the user and the task 
	 * FetchType.LAZY is necessary here to prevent Spring from infinitely fetching user and task
	 * (circular reference). JSON ignore is also necessary to prevent infinitely 
	 * serializing user and task.
	 */
	private Set<Task> Tasks = new HashSet<>();

	/**
	 * This method gets the tasks 
	 * @return tasks 
	 */
	public Set<Task> getTasks() {
		return Tasks;
	}
	
	/**
	 * This method sets the task 
	 * @param tasks tasks of the user 
	 */
	public void setTasks(Set<Task> tasks) {
		Tasks = tasks;
	}
	
	
}
