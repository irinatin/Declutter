package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcFriendDao implements FriendDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcFriendDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Friend searchForFriend(String username) {
		Friend friend = new Friend();

		String sqlFriendSearch = "SELECT user_info.first_name, user_info.last_name, users.username FROM user_info "
				+ "JOIN users ON users.id = user_info.user_id WHERE users.username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFriendSearch, username);

		while (results.next()) {
			friend.setUsername(results.getString("username"));
			friend.setFirstName(results.getString("first_name"));
			friend.setLastName(results.getString("last_name"));
		}
		return friend;
	}

	@Override
	public List<Friend> getAllFriends(long userId) {
		List<Friend> friends = new ArrayList<Friend>();

		String sqlGetFriends = "SELECT users.username, user_info.first_name, user_info.last_name , SUM(user_book.reading_time) "
				+ "FROM users " + "JOIN user_info ON users.id = user_info.user_id "
				+ "JOIN friends ON users.id = friends.friend_id JOIN user_book ON users.id = user_book.user_id "
				+ "WHERE friends.user_id = ? GROUP BY users.username, user_info.first_name, user_info.last_name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFriends, userId);

		while (results.next()) {
			Friend friend = new Friend();
			friend.setFirstName(results.getString("first_name"));
			friend.setLastName(results.getString("last_name"));
			friend.setUsername(results.getString("username"));
			friend.setTotalReadingTime(results.getInt("sum"));
			friends.add(friend);
		}

		return friends;
	}

	@Override
	public boolean addNewFriend(long userId, String friendUserName) {

		String sqlAddFriend = "INSERT INTO friends VALUES (?, (SELECT id FROM users WHERE username = ? ))";
		jdbcTemplate.update(sqlAddFriend, userId, friendUserName);

		return true;
	}

}
