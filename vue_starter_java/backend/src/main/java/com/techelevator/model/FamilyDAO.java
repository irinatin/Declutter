package com.techelevator.model;

import java.util.List;

public interface FamilyDAO {
	
	public List<FamilyDAO> getAllFamilies();
	
	public boolean createFamily(String familyName);
	
	public String getFamilyNameById(long familyId);
	
	public long getFamilyIdByName(String familyName);

	public boolean doesFamilyExist(String familyName);
	
	public List<UserInfo> getAllFamilyMembers (Long familyId);
	

}
