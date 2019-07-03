package com.studymatcher.profile;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.studymatcher.users.User;

/**
 * This is profile class
 * 
 * @author pmandala
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;

	private String securityQuestion1Answer;

	private String state;

	private String username;

	private String securityQuestion2Answer;

	private String dateOfBirth;

	private String lastname;

	private String firstname;

	private String city;

	private String pincode;

	private String school;

	private String phoneNumber;

	private String address;

	private String securityQuestion2;

	private String securityQuestion1;

	/**
	 * This method gets Security Question 1 Answer
	 * 
	 * @return security Question 1 Answer string
	 */
	public String getSecurityQuestion1Answer() {
		return securityQuestion1Answer;
	}

	/**
	 * This method sets the Security Question 1 Answer
	 * 
	 * @param securityQuestion1Answer Security Question 1 Answer
	 */
	public void setSecurityQuestion1Answer(String securityQuestion1Answer) {
		this.securityQuestion1Answer = securityQuestion1Answer;
	}

	/**
	 * This method gets State the user is from
	 * 
	 * @return state of the user
	 */
	public String getState() {
		return state;
	}

	/**
	 * This method sets State the user is from
	 * 
	 * @param state state of the user
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * This method gets Security Question 2 Answer
	 * 
	 * @return security Question 2 Answer string
	 */
	public String getSecurityQuestion2Answer() {
		return securityQuestion2Answer;
	}

	/**
	 * This method sets the Security Question 2 Answer
	 * 
	 * @param securityQuestion2Answer Security Question 2 Answer 
	 */
	public void setSecurityQuestion2Answer(String securityQuestion2Answer) {
		this.securityQuestion2Answer = securityQuestion2Answer;
	}

	/**
	 * This method gets the date of birth
	 * 
	 * @return date of birth as a string
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * This method sets the date of birth
	 * 
	 * @param dateOfBirth date of birth of the user
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * This method gets the last name
	 * 
	 * @return lastname as a string
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * This method sets the last name
	 * 
	 * @param lastname lastname of the user
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * This method gets the first name
	 * 
	 * @return First name as a string
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * This method sets the first name
	 * 
	 * @param firstname Firstname of the user
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * This method gets the city
	 * 
	 * @return city as string
	 */
	public String getCity() {
		return city;
	}

	/**
	 * This method sets the city
	 * 
	 * @param city city of the user
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * This method gets the pin code
	 * 
	 * @return pin code as a string
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * This method sets the pin code
	 * 
	 * @param pincode pincode of the user
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * This method gets school
	 * 
	 * @return school as a string
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * This method sets school
	 * 
	 * @param school school of the of the user
	 */
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	 * This method gets the phone number
	 * 
	 * @return phone number as a string
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * This method sets the phone number
	 * 
	 * @param phoneNumber phone number of the user
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * This method gets the address
	 * 
	 * @return address as a string
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method sets the address
	 * 
	 * @param address address of the of the user
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * This method gets Security Question 2 Answer
	 * 
	 * @return security Question 2 Answer string
	 */
	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	/**
	 * This method sets Security Question 2 Answer
	 * 
	 * @param securityQuestion2  security Question 2 Answer
	 */
	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	/**
	 * This method sets Security Question 1 Answer
	 * 
	 * @return Security Question 1 Answer
	 */
	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	/**
	 * This method gets Security Question 1 Answer
	 * 
	 * @param securityQuestion1 security Question 1 Answer
	 */
	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	/**
	 * This method gets the User
	 * 
	 * @return user from the profile 
	 */
	public User getUser() {
		return user;
	}

	/**
	 * This method sets the User
	 * 
	 * @param user user from profile 
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ClassProfile [securityQuestion1Answer = " + securityQuestion1Answer + ", state = " + state
				+ ", securityQuestion2Answer = " + securityQuestion2Answer + ", dateOfBirth = " + dateOfBirth
				+ ", lastname = " + lastname + ", firstname = " + firstname + ", city = " + city + ", pincode = "
				+ pincode + ", username = " + getUser().getUsername() + ", school = " + school + ", phoneNumber = "
				+ phoneNumber + ", email = " + getUser().getEmail() + ", address = " + address
				+ ", securityQuestion2 = " + securityQuestion2 + ", securityQuestion1 = " + securityQuestion1 + "]";
	}

	/**
	 * This method gets the user id
	 * 
	 * @return id as an integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method sets the user id
	 * 
	 * @param id id of the profile 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method gets the username
	 * 
	 * @return username as a string
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method sets the username
	 * 
	 * @param username username of the user 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
