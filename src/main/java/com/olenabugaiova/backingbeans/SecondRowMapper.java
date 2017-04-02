package com.olenabugaiova.backingbeans;

import com.olenabugaiova.entity.Second;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by elena on 01.04.17.
 */
public class SecondRowMapper  implements RowMapper{

    public Second mapRow(ResultSet rs, int rowNum) throws SQLException {
        Second second = new Second();
        second.setId(rs.getLong("id"));
        second.setCount(rs.getInt("count"));
        second.setMax(rs.getDouble("max_value"));
        second.setMin(rs.getDouble("min_value"));
        second.setSum(rs.getDouble("sum"));
        second.setTimeInSeconds(rs.getLong("time_in_seconds"));
        return second;
    }
}
