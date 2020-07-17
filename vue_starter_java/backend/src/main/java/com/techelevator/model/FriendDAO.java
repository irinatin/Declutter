package com.techelevator.model;

import java.util.List;

public interface FriendDAO {
	
	public Friend searchForFriend (String username);
	public List<Friend> getAllFriends (long userId);
	public boolean addNewFriend(long userId, String friendUserName);
	

}
