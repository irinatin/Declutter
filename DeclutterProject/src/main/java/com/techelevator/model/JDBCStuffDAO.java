package com.techelevator.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCStuffDAO implements StuffDAO{
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCStuffDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean addNewStuff(Stuff stuffItem, long familyId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Stuff> getItemsPerFamily(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stuff> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteItem(Stuff stuffItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String findItemByName(Stuff stuffItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal findItemByPrice(BigDecimal price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stuff> getItemsByCategory(long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
