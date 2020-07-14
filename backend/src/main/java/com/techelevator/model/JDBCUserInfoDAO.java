package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCUserInfoDAO implements UserInfoDAO {
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCUserInfoDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean saveUserInfo(String firstName, String lastName, Long familyId, Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long getUserId(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getFamilyId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
