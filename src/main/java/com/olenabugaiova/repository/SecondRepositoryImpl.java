package com.olenabugaiova.repository;

import com.olenabugaiova.entity.Second;
import com.olenabugaiova.backingbeans.SecondRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by elena on 31.03.17.
 */
@Repository
public class SecondRepositoryImpl implements SecondRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean updateSecondData(double amount, long timeInSeconds) {

        Second second = getSecondDataByTime(timeInSeconds);
        int result;
        if(second != null) {
            second.updateValues(amount);
            String sql = "UPDATE data_per_second SET count=?, max_value=?, min_value=?, "
                    + "sum=? WHERE id=?";
            result = jdbcTemplate.update(sql, second.getCount(), second.getMax(),
                    second.getMin(), second.getSum(), second.getId());
        } else {
            second = new Second();
            second.setInitialValues(amount);
            String sql = "INSERT INTO data_per_second (count, max_value, min_value, sum, time_in_seconds) " +
                    "VALUES (?, ?, ?, ?, ?)";

            result = jdbcTemplate.update(sql, new Object[]{second.getCount(), second.getMax(),
                    second.getMin(), second.getSum(), timeInSeconds});
        }
        return result == 0 ? false : true;
    }

    @Override
    public List<Second> getLastSeconds(long startIntervalTime, long endIntervalTime) {

        String sql = "SELECT * FROM data_per_second WHERE time_in_seconds >= ? AND time_in_seconds < ?";
        List<Second> seconds  = jdbcTemplate.query(sql, new Object[]{startIntervalTime, endIntervalTime}, new SecondRowMapper());

        return seconds;
    }

    @Override
    public Second getSecondDataByTime(long time) {
        String sql = "SELECT * FROM data_per_second WHERE time_in_seconds = ?";

        List<Second> seconds  = jdbcTemplate.query(sql, new Object[]{time}, new SecondRowMapper());

        if ( seconds.isEmpty() ){
            return null;
        }else if ( seconds.size() == 1 ) { // list contains exactly 1 element
            return seconds.get(0);
        }
        return null;
    }

    @Override
    public void deleteDataBeforeGivenTime(long time) {
        String sql = "DELETE FROM data_per_second WHERE time_in_seconds<?";
        jdbcTemplate.update(sql, time);
    }
}
