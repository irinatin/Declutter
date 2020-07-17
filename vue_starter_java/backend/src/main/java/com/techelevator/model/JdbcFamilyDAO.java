package com.techelevator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcFamilyDAO implements FamilyDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcFamilyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Family> getAllFamilies() {
		List<Family> allFamilies = new ArrayList<Family>();
		String getAllFamilies = "SELECT family_id, family_name FROM family";
		Family newFamily = new Family();
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllFamilies);

		while (results.next()) {
			newFamily = mapRowToFamily(results);
			allFamilies.add(newFamily);

		}
		return allFamilies;
	}

	@Override
	public boolean createFamily(String familyName) {
		String insertIntoFamily = "INSERT INTO family VALUES (DEFAULT, ?)";
		jdbcTemplate.update(insertIntoFamily, familyName);
		return true;
	}

	@Override
	public String getFamilyNameById(long familyId) {

		String getFamilyName = "SELECT family_name FROM family WHERE family_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getFamilyName, familyId);

		results.next();
		String familyName = results.getString(1);
		return familyName;
	}

	@Override
	public long getFamilyIdByName(String familyName) {

		String getFamilyId = "SELECT family_id FROM family WHERE family_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getFamilyId, familyName);

		results.next();
		long familyId = results.getLong(1);

		return familyId;
	}

	public boolean doesFamilyExist(String familyName) {

		String sqlFamilyExist = "SELECT COUNT(family_id) FROM family WHERE family_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFamilyExist, familyName);

		results.next();
		if (results.getLong(1) == 0) {
			return false;
		} else {
			return true;
		}

	}

	private Family mapRowToFamily(SqlRowSet results) {
		Family newFamily = new Family();
		newFamily.setFamilyId(results.getLong("family_id"));
		newFamily.setFamilyName(results.getString("family_name"));

		return newFamily;
	}

	@Override
	public List<UserInfo> getAllFamilyMembers(Long familyId) {
		
		String sqlGetFamily = "SELECT * FROM user_info JOIN users ON users.id = user_info.user_id WHERE family_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFamily, familyId);
		
		List<UserInfo> family = new ArrayList<UserInfo>();
		
		while(results.next()) {
			UserInfo user = new UserInfo();
			user.setUserInfoId(results.getLong("user_info_id"));
			user.setUserId(results.getLong("user_id"));
			user.setFirstName(results.getString("first_name"));
			user.setLastName(results.getString("last_name"));
			user.setFamilyId(results.getLong("family_id"));
			if(results.getString("role").equals("user")) {
				user.setRole("Parent");
			}
			if(results.getString("role").equals("child")) {
				user.setRole("Child");
			}
			
			family.add(user);
		}
		return family;
	}

	@Override
	public List<Leaderboard> getFamilyLeaderboard(Long familyId) {
		List<Leaderboard> familyLeaderboard = new ArrayList<Leaderboard>();
		LocalDate now = LocalDate.now().withDayOfMonth(1);
		
		String sqlGetLeaderboard = "SELECT users.username, user_info.first_name, user_info.last_name, SUM(user_book.reading_time) AS totalmins " + 
				"FROM users " + 
				"JOIN user_book ON user_book.user_id = users.id " + 
				"JOIN user_info ON user_info.user_id = users.id " + 
				"WHERE user_info.family_id = ? AND user_book.reading_date >= ? " + 
				"GROUP BY user_info.first_name, user_info.last_name, users.username " +
				"ORDER BY totalmins DESC " + 
				"LIMIT 5"; 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetLeaderboard, familyId, now);
		
		while(results.next()) {
			Leaderboard leaderboard = new Leaderboard();
			leaderboard.setUserName(results.getString("username"));
			leaderboard.setFirstName(results.getString("first_name"));
			leaderboard.setLastName(results.getString("last_name"));
			leaderboard.setTotalReading(results.getInt("totalmins"));
			familyLeaderboard.add(leaderboard);
		}
		return familyLeaderboard;
	}
	
	
}