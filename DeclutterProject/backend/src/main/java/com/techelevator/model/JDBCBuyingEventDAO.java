package com.techelevator.model

import javax.sql.DataSource;

public class JDBCBuyingEventDAO implements BuyingEventDAO {
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCCategoryDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<BuyingEvent> getAllBuyingEvents() {
		
	}
	
	@Override
	public BuyingEvent addBuyingEvent(BuyingEvent buyingEvent) {
		
	}
	
	
	
}
