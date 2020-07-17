package com.techelevator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCPrizeDAO implements PrizeDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCPrizeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean createNewPrize(Prize blingBling, long familyId) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(blingBling.getStartDate()); 
		c.add(Calendar.DATE, 1);
		blingBling.setStartDate(c.getTime());
		Calendar d = Calendar.getInstance(); 
		d.setTime(blingBling.getEndDate()); 
		d.add(Calendar.DATE, 1);
		blingBling.setEndDate(d.getTime());
		long prizeId = getNextPrizeId();
		String insertIntoPrize = "INSERT INTO prize VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(insertIntoPrize, prizeId, blingBling.getPrizeName(), blingBling.getPrizeDescription(),
				blingBling.getMilestone(), blingBling.getUserGroup(), blingBling.getNumOfPrizes(),
				blingBling.getStartDate(), blingBling.getEndDate(), familyId);
		return true;
	}

	@Override
	public boolean editExistingPrize(Prize prize) {
		if(prize.getUserGroup().toLowerCase().equals("parent")) {
			prize.setUserGroup("user");
		}
		String sqlEditPrize = "UPDATE prize SET prize_name = ?, prize_description = ?, milestone = ?, "
				+ "user_group = ?, max_prizes = ?, start_date = ?, end_date = ? WHERE prize_id = ?";
		jdbcTemplate.update(sqlEditPrize, prize.getPrizeName(), prize.getPrizeDescription(), prize.getMilestone(),
				prize.getUserGroup(), prize.getNumOfPrizes(), prize.getStartDate(), prize.getEndDate(),
				prize.getPrizeId());

		return true;
	}

	@Override
	public boolean deletePrize(Prize blingBling) {
		String deleteUserPrize = "DELETE FROM user_prize WHERE prize_id = ?";
		jdbcTemplate.update(deleteUserPrize, blingBling.getPrizeId());

		String deleteFromPrize = "DELETE FROM prize WHERE prize_id = ?";
		jdbcTemplate.update(deleteFromPrize, blingBling.getPrizeId());

		return true;
	}

	@Override
	public List<Prize> getAllPrizes(String userGroup, long familyId) {

		List<Prize> allPrizes = new ArrayList<Prize>();

		String getAllPrizes = "SELECT * FROM prize WHERE user_group = ? AND start_date < ? AND end_date > ? AND family_id = ? AND max_prizes > 0 ORDER BY prize_id";
		Prize blingBling = new Prize();
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllPrizes, userGroup, LocalDate.now(), LocalDate.now(), familyId);

		while (results.next()) {
			blingBling = mapRowToPrize(results);
			allPrizes.add(blingBling);
		}

		return allPrizes;
	}

	public long getNextPrizeId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('prize_prize_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong with prize sequence");
		}
	}

	// Date, LocalDate?? See Prize.java

	private Prize mapRowToPrize(SqlRowSet results) {
		Prize blingBling = new Prize();
		blingBling.setPrizeId(results.getLong("prize_id"));
		blingBling.setPrizeName(results.getString("prize_name"));
		blingBling.setPrizeDescription(results.getString("prize_description"));
		blingBling.setMilestone(results.getInt("milestone"));
		blingBling.setUserGroup(results.getString("user_group"));
		blingBling.setNumOfPrizes(results.getInt("max_prizes"));
		blingBling.setStartDate(results.getDate("start_date"));
		blingBling.setEndDate(results.getDate("end_date"));

		return blingBling;

	}

	@Override
	public Prize getPrize(long prizeId) {
		Prize prize = new Prize();

		String sqlGetPrize = "SELECT * FROM prize WHERE prize_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetPrize, prizeId);

		if (result.next()) {
			prize = mapRowToPrize(result);
		}
		return prize;
	}

//	public List<PrizeListInfo> getPrizeListInfo(String role, Long userId) {
//		List<PrizeListInfo> dummyPrizeInfo = new ArrayList<PrizeListInfo>();
//		String getAllPrizes = "SELECT prize.prize_name, prize.milestone, prize.user_group FROM prize WHERE prize.user_group = ?";
//		SqlRowSet nextPrizeResult = jdbcTemplate.queryForRowSet(getAllPrizes, role);
//		String readingTime = "SELECT SUM(user_book.reading_time) AS totaltime FROM user_book WHERE user_id = ?";
//		SqlRowSet readingTimeResult = jdbcTemplate.queryForRowSet(readingTime, userId);
//		while(nextPrizeResult.next()) {
//			PrizeListInfo prize = new PrizeListInfo();
//			prize.setPrizeName(nextPrizeResult.getString("prize_name"));
//			prize.setMilestone(nextPrizeResult.getInt("milestone"));
//			prize.setUserGroup(nextPrizeResult.getString("user_group"));
//			dummyPrizeInfo.add(prize);
//		}
//		int i = 0;
//		while(readingTimeResult.next()) {
//			int totalReadingTime = readingTimeResult.getInt("totaltime");
//			dummyPrizeInfo.get(i).setReadingTime(totalReadingTime);
//			i++;
//		}
//		return dummyPrizeInfo;
//	}

	@Override
	public List<AwardedPrize> getPrizesPerUser(Long userId) {
		List<AwardedPrize> prizesPerUser = new ArrayList<AwardedPrize>();
		String getAllPrizesPerUser = "SELECT prize.prize_name, prize.prize_description, prize.milestone, user_prize.award_date FROM prize JOIN user_prize ON prize.prize_id = user_prize.prize_id WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllPrizesPerUser, userId);
		
		while (results.next()) {
			AwardedPrize myPrize = new AwardedPrize();
			myPrize.setPrizeName(results.getString(1));
			myPrize.setPrizeDescription(results.getString(2));
			myPrize.setMilestone(results.getInt(3));
			myPrize.setAwardDate(results.getDate(4));
			prizesPerUser.add(myPrize);
		}
		return prizesPerUser;
	}

	@Override
	public boolean awardPrize(String userGroup, long userId, long familyId) {

		List<Prize> prizes = new ArrayList<Prize>();

		String sqlGetPrizes = "SELECT * FROM prize WHERE user_group = ? AND start_date < ? AND end_date > ? AND family_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPrizes, userGroup, LocalDate.now(), LocalDate.now(), familyId);

		while (results.next()) {
			Prize prize = new Prize();
			prize = mapRowToPrize(results);
			prizes.add(prize);
		}

		for (Prize p : prizes) {

			String sqlSumTotalMinutes = "SELECT SUM(reading_time) FROM user_book WHERE user_id = ? AND reading_date >= ? AND reading_date <= ?";
			SqlRowSet resultsMins = jdbcTemplate.queryForRowSet(sqlSumTotalMinutes, userId, p.getStartDate(),
					p.getEndDate());

			resultsMins.next();
			int totalMin = resultsMins.getInt(1);
			if (totalMin >= p.getMilestone()) {
				try {
					if(p.getNumOfPrizes() > 0) {
					String sqlAddPrize = "INSERT INTO user_prize VALUES (?, ?, ?)";
					jdbcTemplate.update(sqlAddPrize, p.getPrizeId(), userId, LocalDate.now());
					
					String sqlUpdatePrizeQty = "UPDATE prize SET max_prizes = ? WHERE prize_id = ?";
					jdbcTemplate.update(sqlUpdatePrizeQty, p.getNumOfPrizes() - 1, p.getPrizeId());
					}
				} catch (Exception e) {
					continue;
				}
			}
		}

		return true;
	}
}
