//package com.techelevator.controller;
//
//import java.awt.print.Book;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sun.jna.platform.win32.Netapi32Util.User;
//import com.sun.jna.platform.win32.Netapi32Util.UserInfo;
//import com.techelevator.authentication.AuthProvider;
//import com.techelevator.authentication.UnauthorizedException;
//import com.techelevator.model.FamilyDAO;
//import com.techelevator.model.UserInfoDAO;
//
//
///**
// * ApiController
// * @param <BuyingEventDAO>
// */
//@RestController
//@CrossOrigin
//@RequestMapping("/api")
//public class ApiController<BuyingEventDAO> {
//
//	//@Autowired
//	//private BookDAO bookDAO;
//
//	@Autowired
//	private BuyingEventDAO buyingEventDAO;
//
//	@Autowired
//	private AuthProvider authDAO;
//
//	@Autowired
//	private UserInfoDAO userInfoDAO;
//	
//	@Autowired
//	private FamilyDAO familyDAO;
//
////	@Autowired
////	private FamilyDAO familyDAO;
//
//	@Autowired
//	private UserDao userDAO;
//
//	@Autowired
//	private PrizeDAO prizeDAO;
//	
//	@Autowired
//	private FriendDAO friendDAO;
//	
//	private long userId;
//	private String userRole;
//
//	@RequestMapping(path = "/", method = RequestMethod.GET)
//	public String authorizedOnly() throws UnauthorizedException {
//		/*
//		 * You can lock down which roles are allowed by checking if the current user has
//		 * a role.
//		 * 
//		 * In this example, if the user does not have the admin role we send back an
//		 * unauthorized error.
//		 */
//		if (!authDAO.userHasRole(new String[] { "admin" })) {
//			throw new UnauthorizedException();
//		}
//		return "Success";
//	}
//	
//	@RequestMapping(path = "/getUser", method = RequestMethod.GET)
//    public List<UserFirst> getAllUsersbyFam() {
//    	User currentUser = authDAO.getCurrentUser();
//    	long currentUserId = currentUser.getId(); 
//    	return userDAO.getAllUsersByFamily(currentUserId);
//    }
//	
//	@RequestMapping(path = "/getCurrentUser", method = RequestMethod.GET)
//    public User getCurrentUser() {
//    	User middleUser = authDAO.getCurrentUser();
//    	User endUser = new User();
//    	endUser.setId(middleUser.getId());
//    	endUser.setRole(middleUser.getRole());
//    	endUser.setUsername(middleUser.getUsername());
//    	return endUser;
//    } 
//	
//	@RequestMapping(path = "/addChild", method = RequestMethod.POST)
//    public boolean addChild(@RequestBody ChildInfo child) {
//    	userDAO.saveUser(child.getUsername(), child.getPassword(), "child");
//    	long childId = userDAO.getUserByUsername(child.getUsername()).getId();
//    	Long familyId = userInfoDAO.getFamilyId(userId);
//    	userInfoDAO.saveUserInfo(child.getFirstName(), child.getLastName(), familyId, childId);
//    	
//    	return true;
//    }
//
//	@RequestMapping(path = "/addBook", method = RequestMethod.POST)
//	public boolean addBook(@RequestBody Book newBook) {
//		return bookDAO.addNewBook(newBook, userInfoDAO.getFamilyId(userId));
//	}
//	
//	@RequestMapping(path = "/getAllBooks", method = RequestMethod.GET)
//    public List<Book> getAllBooks() {
//    	User currentUser = authDAO.getCurrentUser();
//    	long currentUserId = currentUser.getId(); 
//    	return bookDAO.getAllBooksPerFamily(currentUserId);
//    }
//
//	@RequestMapping(path = "/addReadingEvent", method = RequestMethod.POST)
//	public ReadingEvent addReadingEvent(@RequestBody ReadingEvent reads) {
//		ReadingEvent reading = reDAO.addReadingEvent(reads);
//		prizeDAO.awardPrize(userRole, userId, userInfoDAO.getFamilyId(userId));
//		return reading;
//	}
//
//	@RequestMapping(path = "/getFamilyList", method = RequestMethod.GET)
//	public List<UserInfo> getFamilyList() {
//		this.userId = authDAO.getCurrentUser().getId();
//		Long familyId = userInfoDAO.getFamilyId(userId);
//		this.userRole = authDAO.getCurrentUser().getRole();
//		List<UserInfo> familyMembers = familyDAO.getAllFamilyMembers(familyId);
//		for (UserInfo i : familyMembers) {
//			i.setFamilyName(familyDAO.getFamilyNameById(i.getFamilyId()));
//		}
//		return familyMembers;
//	}
//    
//    @RequestMapping(path = "/addPrize", method = RequestMethod.POST)
//    public boolean addPrize(@RequestBody Prize newPrize) {
//    	this.userId = authDAO.getCurrentUser().getId();
//		Long familyId = userInfoDAO.getFamilyId(userId);
//    	prizeDAO.createNewPrize(newPrize, familyId);
//    	prizeDAO.awardPrize(userRole, userId, userInfoDAO.getFamilyId(userId));
//    	return true;
//    }
//    
//    
//    @RequestMapping( path = "/getReadingActivity/{username}", method = RequestMethod.GET)
//    public ReadingActivity getReadingActivityObject(@PathVariable String username) {
//    	User requestedUser =userDAO.getUserByUsername(username); 
//    	long currentUserId = requestedUser.getId();
//    	String currentUserRole = requestedUser.getRole();
//    	
//    	return reDAO.getReadingActivity(currentUserId, currentUserRole);
//    }
//    
//
//	@RequestMapping(path = "/getPrizes", method = RequestMethod.GET)
//	public List<Prize> getPrizeList() {
//		this.userId = authDAO.getCurrentUser().getId();
//		Long familyId = userInfoDAO.getFamilyId(userId);
//		return prizeDAO.getAllPrizes(authDAO.getCurrentUser().getRole(), familyId);
//	}
//
//
//	@RequestMapping(path = "/getLeaderboard", method = RequestMethod.GET)
//	public List<Leaderboard> getLeaderboard() {
//		return familyDAO.getFamilyLeaderboard(userInfoDAO.getFamilyId(authDAO.getCurrentUser().getId()));
//	}
//	
//	@RequestMapping(path = "/getAllFriends", method = RequestMethod.GET)
//	public List<Friend> getAllFriends(){
//		return friendDAO.getAllFriends(authDAO.getCurrentUser().getId());
//	}
//	
//	@RequestMapping(path = "/addFriend", method = RequestMethod.POST)
//	public boolean addFriend(@RequestBody Friend friend) {
//		long userId = authDAO.getCurrentUser().getId();
//		friendDAO.addNewFriend(userId, friend.getUsername());
//		return true;
//	}
//	
//	@RequestMapping(path = "/searchForFriend/{username}", method = RequestMethod.GET)
//	public Friend searchForFriend(@PathVariable String username) {
//		return friendDAO.searchForFriend(username);
//	}
//	
//	@RequestMapping(path = "/getPrize/{prizeIdNum}", method = RequestMethod.GET)
//	public Prize getPrize(@PathVariable long prizeIdNum) {
//		return prizeDAO.getPrize(prizeIdNum);
//	}
//	
//	@RequestMapping(path = "/editPrize", method = RequestMethod.POST)
//	public boolean editPrize(@RequestBody Prize prize) {
//		prizeDAO.editExistingPrize(prize);
//		prizeDAO.awardPrize(userRole, userId, userInfoDAO.getFamilyId(userId));
//		return true;
//	}
//	
//	@RequestMapping( path = "/getPrizesPerUser", method = RequestMethod.GET)
//	public List<AwardedPrize> getPrizesPerUser() {
//		prizeDAO.awardPrize(userRole, userId, userInfoDAO.getFamilyId(userId));
//		User currentUser = authDAO.getCurrentUser();
//		Long currentUserId = currentUser.getId();
//		return prizeDAO.getPrizesPerUser(currentUserId);
//	}
//	
//	@RequestMapping(path = "/deletePrize", method = RequestMethod.POST)
//	public boolean deletePrize(@RequestBody Prize prize) {
//		prizeDAO.deletePrize(prize);
//		return true;
//	}
//	
//
//}