package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserInfoDao implements UserInfoDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcUserInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Long getUserId(String userName) {
		
		String sqlUserId = "SELECT id from users WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlUserId, userName);
	
		results.next();
		Long userId = results.getLong(1);
		return userId;
	}

	@Override
	public boolean saveUserInfo(String firstName, String lastName, Long familyId, Long userId) {
		
		String sqlSaveUserInfo = "INSERT INTO user_info VALUES (DEFAULT, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlSaveUserInfo, userId, firstName, lastName, familyId);
		
		return true;
		
	}

	@Override
	public Long getFamilyId(Long userId) {
		
		String sqlGetFamilyId = "SELECT family_id FROM user_info WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFamilyId, userId);
		
		results.next();
		
		Long familyId = results.getLong("family_id");
		return familyId;
	}
	
	
	

}
