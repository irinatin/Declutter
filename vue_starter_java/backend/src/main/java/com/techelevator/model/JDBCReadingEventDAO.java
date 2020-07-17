package com.techelevator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCReadingEventDAO implements ReadingEventDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCReadingEventDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ReadingEvent> getAllReadingEvents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReadingEvent addReadingEvent(ReadingEvent reads) {
        reads.setReadingEventId(getReadingEventId());
        String sqlSaveReadingEvent = "INSERT INTO user_book VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlSaveReadingEvent, reads.getReadingEventId(), reads.getUserId(), reads.getBookId(),
                reads.getReadingTime(), LocalDate.parse(reads.getReadingDate()), reads.getFormat(),
                reads.isCompleted());
        return reads;
    }

    @Override
    public ReadingEvent addChildReadingEvent(ReadingEvent reads, boolean completed) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReadingActivity getReadingActivity(Long userId, String userRole) {
        
        ReadingActivity familyReadingActivity = new ReadingActivity();

        String sqlTotalBooksCompleted = "SELECT COUNT(*) FROM user_book WHERE completed = true AND user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTotalBooksCompleted, userId);
        
        results.next();
        familyReadingActivity.setCompletedBooks(results.getInt(1));
        
        String sqlSumTotalMinutes = "SELECT SUM(reading_time) FROM user_book WHERE user_id = ?";
        
        SqlRowSet resultsMins = jdbcTemplate.queryForRowSet(sqlSumTotalMinutes, userId);
        resultsMins.next();
        familyReadingActivity.setTotalReadingTime(resultsMins.getInt(1));
        
        
        String sqlCurrentBooks = "SELECT book.title, user_book.completed, user_book.reading_date FROM user_book JOIN book ON book.book_id = user_book.book_id WHERE user_book.user_id = ? ORDER BY user_book.reading_date";
        
        SqlRowSet resultsCurrentBooks = jdbcTemplate.queryForRowSet(sqlCurrentBooks, userId);
        Map<String, Boolean> currentBooks = new HashMap<String, Boolean>();
        List<String> currentBooksTitles = new ArrayList<String>();
        
        while(resultsCurrentBooks.next()) {
            currentBooks.put(resultsCurrentBooks.getString(1), resultsCurrentBooks.getBoolean(2));
        }
        
        Set<String> keys = currentBooks.keySet();
        for(String key : keys) {
            if(currentBooks.get(key) == false) {
                currentBooksTitles.add(key);
            }
        }
        
        familyReadingActivity.setCurrentBooks(currentBooksTitles);
        
       String sqlFindUserFamId = "SELECT user_info.family_id FROM user_info JOIN users on users.id = user_info.user_id WHERE user_id = ?";
       SqlRowSet famIdResults = jdbcTemplate.queryForRowSet(sqlFindUserFamId, userId);
        famIdResults.next();
        Long familyId = famIdResults.getLong(1);
        
        String sqlProgressTowardsPrize = "SELECT * FROM prize WHERE user_group = ? AND start_date < ? AND end_date > ? AND family_id = ? AND max_prizes > 0";
        
        SqlRowSet resultsPrizes = jdbcTemplate.queryForRowSet(sqlProgressTowardsPrize, userRole, LocalDate.now(), LocalDate.now(), familyId);
        
        Map<String, Integer> progress = new HashMap<String, Integer>();
        
        List<Prize> availablePrizes = new ArrayList<>();
        
        while(resultsPrizes.next()) {
        	Prize prize = mapRowToPrize(resultsPrizes);
        	availablePrizes.add(prize);
        }
        
        for (Prize p : availablePrizes) {
        	String sqlSumTotalMinutesPerPrize = "SELECT SUM(reading_time) FROM user_book WHERE user_id = ? AND reading_date >= ? AND reading_date <= ?";
			SqlRowSet resultsMinsPerPrize = jdbcTemplate.queryForRowSet(sqlSumTotalMinutesPerPrize, userId, p.getStartDate(),p.getEndDate());
			resultsMinsPerPrize.next();
			int minutesToPrize = resultsMinsPerPrize.getInt(1);
			if (minutesToPrize<p.getMilestone()) {
				double percentage = (double)minutesToPrize/(double)p.getMilestone();
				int percent = (int)(percentage * 100);
				progress.put(p.getPrizeName(), percent);
			}
        }
        
        
        familyReadingActivity.setProgressTowardsPrize(progress);

        return familyReadingActivity;
    }

    private long getReadingEventId() {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('user_book_reading_event_id_seq')");
        if (nextIdResult.next()) {
            return nextIdResult.getLong(1); // changed from int to long
        } else {
            throw new RuntimeException("Something went wrong with book sequence");
        }
    }
    
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

}
