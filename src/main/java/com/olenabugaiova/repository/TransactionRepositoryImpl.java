package com.olenabugaiova.repository;

import com.olenabugaiova.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by elena on 31.03.17.
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertTransaction(Transaction transaction) {

        int result = jdbcTemplate.update(
                "INSERT INTO Transactions (amount, time) VALUES (?, ?)",
                new Object[]{transaction.getAmount(), transaction.getTimestamp()});

        return result == 0 ? false : true;
    }

    @Override
    public void deleteDataBeforeGivenTime(long time) {
        String sql = "DELETE FROM Transactions WHERE time<?";
        jdbcTemplate.update(sql, time);
    }
}
