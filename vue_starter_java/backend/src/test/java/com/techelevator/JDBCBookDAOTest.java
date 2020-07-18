//package com.techelevator;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.SingleConnectionDataSource;
//
//import com.techelevator.model.Book;
//import com.techelevator.model.BookDAO;
//import com.techelevator.model.JDBCBookDAO;
//
//public class JDBCBookDAOTest {
//
////	private static SingleConnectionDataSource dataSource;
//	private BookDAO dao;
//	private JdbcTemplate jdbcTemplate;
//	private Book myBook;
//	
//	@Before
//	public void setUp() throws Exception {
//		
//		//jdbcTemplate = new JdbcTemplate(dataSource);
//		
//		//dao = new JDBCBookDAO(dataSource);
//		myBook = new Book();
//		
//	}
//	
//
//	@After
//	public void tearDown() throws Exception {
//		//dataSource.getConnection().rollback();
//	}
//	
//	@Test
//	public void test_get_and_set_family_id() {
//		long familyId = 12;
//		myBook.setFamily_id(familyId);
//		assertEquals(12, myBook.getFamily_id());
//	}
//	
//	@Test
//	public void test_get_and_set_id() {
//		long id = 12;
//		myBook.setId(id);
//		assertEquals(12, myBook.getId());
//	}
//	
//	@Test
//	public void test_get_and_set_title() {
//		String title = "Oil";
//		myBook.setTitle(title);
//		assertEquals("Oil", myBook.getTitle());
//	}
//	
//	@Test
//	public void test_get_and_set_author() {
//		String author = "Upton Sinclair";
//		myBook.setAuthor(author);;
//		assertEquals("Upton Sinclair", myBook.getAuthor());
//	}
//}
