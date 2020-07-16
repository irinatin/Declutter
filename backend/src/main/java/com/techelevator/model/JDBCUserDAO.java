//package com.techelevator.model;
//
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import com.techelevator.authentication.PasswordHasher;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//public class JDBCUserDAO implements UserDAO {
//	
//	private JdbcTemplate jdbcTemplate;
//	private PasswordHasher passwordHasher;
//
//    /**
//     * Create a new user dao with the supplied data source and the password hasher
//     * that will salt and hash all the passwords for users.
//     *
//     * @param dataSource an SQL data source
//     * @param passwordHasher an object to salt and hash passwords
//     */
//	
//	
//    @Autowired
//    public JDBCUserDAO(DataSource dataSource, PasswordHasher passwordHasher) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//        this.passwordHasher = passwordHasher;
//    }
//	
//	@Override
//	public User saveUser(String userName, String password, String role) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void changePassword(User user, String newPassword) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public User getValidUserWithPassword(String userName, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> getAllUsers() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User getUserByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteUser(Long userId) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	
//
//}
