package com.techelevator.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCFamilyDAO implements FamilyDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCFamilyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Family> getAllFamilies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createFamily(String familyName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFamilyNameById(long familyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getFamilyIdByName(String familyName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doesFamilyExist(String familyName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserInfo> getAllFamilyMembers(Long familyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
