package com.techelevator.model;

import java.util.List;

public interface PrizeDAO {

	public boolean createNewPrize(Prize blingBling, long familyId);
	public boolean editExistingPrize(Prize blingBlung);
	public boolean deletePrize(Prize blingBling);
	public List<Prize> getAllPrizes(String userGroup, long familyId);
	public Prize getPrize(long prizeId);
//	public List<PrizeListInfo> getPrizeListInfo(String role, Long userId);
	public List<AwardedPrize> getPrizesPerUser(Long userId);
	public boolean awardPrize(String userGroup, long userId, long familyId);
	
	
}
