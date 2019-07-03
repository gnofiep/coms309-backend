package com.studymatcher.messages;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studymatcher.group.Group;
import com.studymatcher.group.GroupRepository;
/**
 * This is the message controller where post and get method for saving and getting message from database are created 
 * @author pmandala
 *
 */
@RestController
public class MessageController {

	@Autowired
	private GroupRepository groupRepository;

	/**
	 * This method saves all the messages in a group chat to the database
	 * @param id group id  
	 * @param message The group message 
	 * @return "success" when all the messages are saved to the database 
	 */
	@RequestMapping(method = RequestMethod.POST, path = "group/{id}/add")
	public String addMessage(@PathVariable("id") int id, @RequestBody Message message) {
		Optional<Group> g = groupRepository.findById(id);
		
		if (g.isPresent()) {
			Group gr = g.get();

			Set<Message> message2 = gr.getMessage();
			message2.add(message);
			groupRepository.save(gr);

		} else {
			return "no group found!";
		}

		return "success";

	}
	
	/**
	 * This method gets all the message from the database to a specific group 
	 * @param id group id 
	 * @return All the messages from the database 
	 */
	@GetMapping(path="group/{id}/messages")
	public Set<Message> getMessage(@PathVariable("id") int id) {
		return groupRepository.findById(id).get().getMessage();
	}

}
