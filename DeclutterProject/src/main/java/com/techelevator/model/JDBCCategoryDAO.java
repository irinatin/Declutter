package com.techelevator.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCCategoryDAO implements CategoryDAO {
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCCategoryDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCategoryByName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
