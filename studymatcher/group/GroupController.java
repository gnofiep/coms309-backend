package com.studymatcher.group;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studymatcher.users.User;
import com.studymatcher.users.UserRepository;
/**
 * This is the group controller where post and get method for groups are created
 * @author pmandala
 *
 */
@RestController
public class GroupController {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Get the list of all the groups and JSON object Once you run the SQL Commands
	 * in data.sql hit this URL "http://localhost:8090/groups to get all the groups
	 * @return group ListofGroup
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/groups")
	public List<Group> getAllGroups() {
		List<Group> group = groupRepository.findAll();
		return group;
	}

	/**
	 * Post the group information to the database and returns group id 
	 * "http://localhost:8090/groups"
	 * @param group Group
	 * @return group.getID Gives the id of the group
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/groups")
	public int postGroup(@RequestBody Group group) {
		groupRepository.save(group);
		return group.getId();
	}

	/**
	 * This method return all the users that are present
	 * in the group by using group id to find the group
	 * 
	 * @param id Group ID
	 * @return all users in a group
	 */
	@GetMapping("group/{id}/users")
	public Set<Map<String, Object>> getUsersForGroup(@PathVariable Integer id) {
		Optional<Group> group = groupRepository.findById(id);
		Set<Map<String, Object>> users = new HashSet<Map<String, Object>>();
		if (group.isPresent()) {
			for (User p : group.get().getUsers()) {

				/**
				 * THe project DTO method includes the group for that user. Maybe this
				 * information is not Needed for this Get mapping, but the point is you control
				 * what data is returned with a DTO
				 */
				users.add(p.toDTO());
			}
		}

		return users;
	}

	/**
	 * This method check whether both the user and group are present,
	 * then aonly will add the user into the existed group
	 * @param username Username
	 * @param id Group Id
	 * @return string "success or not group found"
	 */
	@RequestMapping(method = RequestMethod.POST, path = "groups/{id}/add/{username}")
	public String addUserToGroup(@PathVariable("username") String username, @PathVariable("id") int id) {
		Optional<Group> g = groupRepository.findById(id);
		Optional<User> u = userRepository.findByUsername(username);
		
		if (g.isPresent() && u.isPresent()) {
			Group gr = g.get();
			User user = u.get();
			
			Set<User> users1 = gr.getUsers();
			users1.add(user);
			Set<Group> groups1 = user.getGroups();
			groups1.add(gr);
			
			groupRepository.save(gr);
//				userRepository.save(user);
			

		} else {
			return "no group found!";
		}

		return "success";

	}
	
	/**
	 * Removing specific user from a specific group 
	 * @param username Username
	 * @param id Group Id
	 * @return string "success or no group found" 
	 */
	
	@RequestMapping(method = RequestMethod.POST, path = "groups/{id}/remove/{username}")
	public String removeUserFromGroup(@PathVariable("username") String username, @PathVariable("id") int id) {
		Optional<Group> g = groupRepository.findById(id);
		Optional<User> u = userRepository.findByUsername(username);
		
		if (g.isPresent() && u.isPresent()) {
			Group gr = g.get();
			User user = u.get();
			
			Set<User> users1 = gr.getUsers();
			users1.remove(user);
			Set<Group> groups1 = user.getGroups();
			groups1.remove(gr);
			
			groupRepository.save(gr);
//				userRepository.save(user);
			

		} else {
			return "no group found!";
		}

		return "success";

	}
	

	/**
	 * this method returns all the groups that the user is in
	 * @param username Username
	 * @return Groups that the user is in
	 */
	@GetMapping("user/{username}/groups")
	public Set<Map<String, Object>> getGroupsForUser(@PathVariable String username) {
		Optional<User> user = userRepository.findByUsername(username);
		Set<Map<String, Object>> Group = new HashSet<Map<String, Object>>();
		if (user.isPresent()) {
			for (Group g : user.get().getGroups()) {
				Group.add(g.toDTO());
			}
		}
		return Group;
	}

	/**
	 * This method will update the existing group name with the new group name from json,
	 * by finding the group with groupId from the url
	 * @param group New Group with the name changed
	 * @param groupId Id of group we are changing
	 * @return string "updated" after changing the groupname
	 */
	@RequestMapping(method=RequestMethod.POST, path="/groups/{groupId}")
	public String updateGroupName(@RequestBody Group group,@PathVariable Integer groupId ) {
		Optional<Group> groupInitial =  groupRepository.findById(groupId);
		groupInitial.get().setGroup_name(group.getGroup_name());
		groupRepository.save(groupInitial.get());
		return "updated";
	}

}
