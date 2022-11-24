package com.infosys.infytel.friend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.infytel.friend.dto.FriendFamilyDTO;
import com.infosys.infytel.friend.entity.FriendFamily;
import com.infosys.infytel.friend.repository.FriendFamilyRepository;

@Service
public class FriendFamilyService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FriendFamilyRepository friendRepo;

	// Create Friend Family
	
	public void saveFriend(Long phoneNo, FriendFamilyDTO friendDTO) {
		logger.info("Creation request for customer {} with data {}", phoneNo, friendDTO);
		friendDTO.setPhoneNo(phoneNo);
		FriendFamily friend = friendDTO.createFriend();
		friendRepo.save(friend);
	}
	
	// Get friend and family phone number list of a given customer
	public List<Long> getSpecificFriends(Long phoneNo){
		logger.info("Friend and family detailsfor customer {} ", phoneNo);
		List<Long> friendList= new ArrayList<Long>();
		List<FriendFamily> friends=friendRepo.getByPhoneNo(phoneNo);
		for (FriendFamily friendFamily : friends) {
			friendList.add(friendFamily.getFriendAndFamily());
		}
		logger.info("The friend list is for customer{} is {} ",phoneNo,friendList);
		return friendList;
	}

}
