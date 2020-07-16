//package com.techelevator.controller;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.techelevator.authentication.AuthProvider;
//import com.techelevator.authentication.JwtTokenHandler;
//import com.techelevator.authentication.UnauthorizedException;
//import com.techelevator.authentication.UserCreationException;
//import com.techelevator.model.FamilyDAO;
//import com.techelevator.model.User;
//import com.techelevator.model.UserDAO;
//import com.techelevator.model.UserInfo;
//import com.techelevator.model.UserInfoDAO;
//
//
///**
// * AccountController
// */
//
//@CrossOrigin
//@RestController
//public class AccountController {
//
//	@Autowired
//	private AuthProvider auth;
//
//	@Autowired
//	private UserInfoDAO userInfo;
//
//	@Autowired
//	private FamilyDAO family;
//
//	@Autowired
//	private JwtTokenHandler tokenHandler;
//	
//	@Autowired
//	private UserDAO user;
//
//	String userName;

//	@RequestMapping(path = "/login", method = RequestMethod.POST)
//	public String login(@RequestBody User user, RedirectAttributes flash) throws UnauthorizedException {
//		if (auth.signIn(user.getUsername(), user.getPassword())) {
//			User currentUser = auth.getCurrentUser();
//			return tokenHandler.createToken(user.getUsername(), currentUser.getRole());
//		} else {
//			throw new UnauthorizedException();
//		}
//
//	}
//
//	@RequestMapping(path = "/register", method = RequestMethod.POST)
//	public String register(@Valid @RequestBody User user, BindingResult result) throws UserCreationException {
//		this.userName = user.getUsername();
//		if (result.hasErrors()) {
//			String errorMessages = "";
//			for (ObjectError error : result.getAllErrors()) {
//				errorMessages += error.getDefaultMessage() + "\n";
//			}
//			System.out.println(errorMessages);
//			throw new UserCreationException(errorMessages);
//		}
//		auth.register(user.getUsername(), user.getPassword(), user.getRole());
//		return "{\"success\":true}";
//	}
//
//	@RequestMapping(path = "/logoff", method = RequestMethod.POST)
//	public String logoff() {
//		if (auth.isLoggedIn()) {
//			auth.logOff();
//		}
//		return "{\"success\":true}";
//	}
//
//	@RequestMapping(path = "/userinfo", method = RequestMethod.POST)
//	public String userinfo(@RequestBody UserInfo info, BindingResult result) throws UserCreationException {
//		Long userId = userInfo.getUserId(userName);
//		if (info.isNewFamily()) {
//			family.createFamily(info.getFamilyName());
//			long familyId = family.getFamilyIdByName(info.getFamilyName());
//			userInfo.saveUserInfo(info.getFirstName(), info.getLastName(), familyId, userId);
//		} else {
//			if (family.doesFamilyExist(info.getFamilyName())) {
//				long familyId = family.getFamilyIdByName(info.getFamilyName());
//				userInfo.saveUserInfo(info.getFirstName(), info.getLastName(), familyId, userId);
//			}
//			else {
//				throw new UserCreationException("Error");
//			}
//
//		}
//		return "{\"success\":true}";
//	}
//}
