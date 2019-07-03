package com.studymatcher.users;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studymatcher.profile.Profile;
import com.studymatcher.tasks.Task;
import com.studymatcher.tasks.TaskRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * This the User Controller where all the Post and Get method are located realted to the user 
 * @author pmandala
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaskRepository taskRepository;

	/**
	 * Get the list of all User, this list will contain the user list and JSON
	 * object Once you run the SQL commands in data.sql into your corresponding
	 * workbench, hit this URL "http://localhost:8090/users" in your browser and
	 * match your database to get more sense of how it works.
	 * @return user 
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/user")
	public List<User> getAllUsers() {
		List<User> user = userRepository.findAll();
		return user;
	}

	/**
	 * Get the information of a particular user and JSON Object Once you run the SQL
	 * commands in data.sql into your corresponding workbench, hit this URL
	 * "http://localhost:8090/users/{username}" in your browser and match your
	 * database to get more sense of how it works.
	 * @return user 
	 * @param username username 
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/user/{username}")
	public User getUserByUsename(@PathVariable String username) {
		User user = userRepository.findByUsername(username).get();
		return user;
	}

	/**
	 * Post the information about the user in the database hit this URL
	 * "http://localhost:8090/users" in your browser
	 * 
	 * @param user the user we want data for
	 * @return string "success" if user is post, else "already exist" if the user
	 *         exists
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/user")
	public String postUser(@RequestBody User user) {
		User u = userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail());
		if (u != null) {
			return "already exists";
		}
		user = userRepository.save(user);
		return "success";
	}

	/**
	 * Posts the information of a particular user's profile to the database Once you
	 * run the SQL commands in data.sql into your corresponding workbench, hit this
	 * URL "http://localhost:8090/users/{username}/profile" and save the profile of
	 * @param username username of the user 
	 * @param profile profile of the user 
	 * @return "success" if the user has a profile and user exists or "no user
	 *         found"
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/user/{username}/profile")
	public String postProfile(@PathVariable("username") String username, @RequestBody Profile profile) {

		Optional<User> u = userRepository.findByUsername(username);
		if (u.isPresent()) {
			User user = u.get();
			profile.setUser(user);
			user.setProfile(profile);
			userRepository.save(user);
		} else {
			return "no user found!";
		}

		return "success";
	}

	/**
	 * Get the information of a particular user's profile Once you run the SQL
	 * commands in data.sql into your corresponding workbench, hit this URL
	 * "http://localhost:8090/users/{username}/profile" in your browser and match
	 * your database to get more sense of how it works.
	 * @param username username of the user 
	 * @param profile profile of the user 
	 * @return "success" if the user's profile is in the database or "no user found"
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/user/{username}/profile")
	public String getProfile(@PathVariable("username") String username, @RequestBody Profile profile) {

		Optional<User> u = userRepository.findByUsername(username);
		if (u.isPresent()) {
			User user = u.get();
			profile.getUser();
			user.getProfile();
			userRepository.save(user);
		} else {
			return "no user found!";
		}

		return "success";
	}

	/**
	 * Post the information about the user in the database hit this URL
	 * "http://localhost:8090/userTask" in your browser
	 * @param username username of the user
	 * @param task tasks of the user 
	 * @return "success" if the user's task is posted the database
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/userTask/{username}")
	public String postTask(@RequestBody Task task, @PathVariable String username) {
		Optional<User> user = userRepository.findByUsername(username);
		task.setUser(user.get());
		user.get().getTasks().add(task);
		User u = userRepository.save(user.get());
		return "sucess";
	}

	/**
	 * This method return all the tasks which a user have, by searching the user
	 * with the given username
	 * 
	 * @param username username of the user 
	 * @return all tasks of the user
	 */
	@GetMapping("user/{username}/tasks")
	public Set<Map<String, Object>> getTasksForUser(@PathVariable String username) {
		Optional<User> user = userRepository.findByUsername(username);
		Set<Map<String, Object>> Task = new HashSet<Map<String, Object>>();
		if (user.isPresent()) {
			for (Task t : user.get().getTasks()) {
				Task.add(t.toDTO());
			}
		}
		return Task;
	}

	/**
	 * This method is removing a task from the user,where it first finding the user
	 * by username and finding the task by taskname then only remove the task
	 * 
	 * @param username Username for the user
	 * @param taskName Name of the task
	 * @return string "sucess" if sucessfully remove the task from user
	 */
	@DeleteMapping("/userRemoveTask/{username}/{taskName}")
	public String removeTask(@PathVariable("username") String username, @PathVariable("taskName") String taskName) {
		Optional<User> user = userRepository.findByUsername(username);
		Task Tdelete = null;
		if (user.isPresent()) {
			for (Task t : user.get().getTasks()) {
				if (t.getTask_name().equals(taskName)) {
					Optional<Task> task = taskRepository.findById(t.getId());
					Tdelete = task.get();
				} else {
				}
			}
			user.get().getTasks().remove(Tdelete);
			taskRepository.deleteById(Tdelete.getId());
			userRepository.save(user.get());
			taskRepository.flush();
		} else
			return "no user found!";
		return "succes";
	}

}
