package com.studymatcher.meeting;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studymatcher.group.Group;
import com.studymatcher.group.GroupRepository;
/**
 * This meeting controller contain endpoints to post and get meeting for a group
 * @author pfong
 *
 */
@RestController
public class MeetingController {
	
	@Autowired
	private MeetingRepository meetingRepository;

	@Autowired
	private GroupRepository groupRepository;
	
	/**
	 * This method will save the meeting from the json, and add it to the select group
	 * by finding the group using groupId which obtain from the url
	 * if there exist a meeting in the group, it will be updated with 
	 * the new meeting
	 * @param meeting JSON body for the meeting
	 * @param groupId Identification for the group
	 * @return string "sucess" when meeting get save
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/meeting/{groupId}")
	public String postMeeting(@RequestBody Meeting meeting,@PathVariable Integer groupId) {
		Optional<Group> g = groupRepository.findById(groupId);
		if (g.get().getMeeting() != null) {
			int meet= g.get().getMeeting().getId();
			Optional<Meeting> m = meetingRepository.findById(meet);
			Meeting mt= m.get();
			mt.setMeeting_date(meeting.getMeeting_date());
			mt.setMeeting_time(meeting.getMeeting_time());
			meetingRepository.save(mt);		
			g.get().setMeeting(mt);
			groupRepository.save(g.get());
		} else {
			meetingRepository.save(meeting);
			g.get().setMeeting(meeting);
			groupRepository.save(g.get());
		}
		return "success";
	}

	/**
	 * This method return the meeting for the select group by finding with groupId
	 * @param groupId Unique Identification for the group
	 * @return the details of the meeting for the group
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/meetingTime/{groupId}")
	public Map<String, Object> getUserByUsename(@PathVariable Integer groupId) { 
		Meeting meeting = groupRepository.findById(groupId).get().getMeeting();
		return meeting.toDTO();
	}
}
