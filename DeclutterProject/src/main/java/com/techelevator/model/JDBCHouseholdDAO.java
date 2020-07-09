package com.techelevator.model;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCHouseholdDAO implements HouseholdDAO {
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCHouseholdDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int numberOfItemsPerHousehold(long familyId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal amountSpentPerHousehold(long familyId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
