package com.studymatcher.profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studymatcher.users.UserRepository;
/**
 * 
 * @author pmandala
 * This is profile Controller where profile Post and Get method are created 
 */
@RestController
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired 
	private UserRepository userRepository; 
	
	/**
	 * This method returns all profiles in the database 
	 * @return profiles from database 
	 */
	@RequestMapping(method= RequestMethod.GET, path = "/profile")
	public List<Profile> getAllProfile(){
		List<Profile> profile =  profileRepository.findAll();
		return profile;
	}
	
	/* Get the user details of a profile. Note that 
	 * profile and user are different entities but they 
	 * are relared via one to on mapping 
	 * 
	 * We also get the group used for the users from the 
	 * many to many mapping. A DTO is used here to avid 
	 * a circular reference between users and groups.
	 * 
	 * THE DTO is just a map, but a POJO can also be used
	 * 
	 * Once you run the SQL Commands in data.sql into your
	 * corresponding workbench, hit this URL
	 * "http://localhost:8090/profile/1" in your browser and 
	 * match your database to get more sense of how it works. 
	 * Replace 1 with 2,3, or 4 
	 */
	
	/**
	 * This method returns specific profiles of the specified ids' in the database 
	 * @param id id of the profile 
	 * @return profiles of specified ids' from database
	 */
	@RequestMapping(method= RequestMethod.GET, path ="/profile/{id}")
	public Map<String, Object> getUserForProfile(@PathVariable Integer id){
		if (userRepository.findById(id).isPresent())
			return profileRepository.findByUserId(id).get().getUser().toProfileDTO();
		else
			return new HashMap<String, Object>();
	}
	
	/**
	 * This method returns a profile of a specific user
	 * @param username username of the user 
	 * @return profile of user
	 */
	@RequestMapping(method= RequestMethod.GET, path ="/profile/{username}/users")
	public Map<String, Object> getUserForProfile(@PathVariable String username){
		if (userRepository.findByUsername(username).isPresent())
			return profileRepository.findByUsername(username).get().getUser().toProfileDTO();
		else
			return new HashMap<String, Object>();
	}
	
	
}
