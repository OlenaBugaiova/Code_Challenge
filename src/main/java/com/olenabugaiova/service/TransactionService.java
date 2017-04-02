package com.olenabugaiova.service;

import com.olenabugaiova.repository.TransactionRepository;
import com.olenabugaiova.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by elena on 31.03.17.
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public boolean insertTransaction(Transaction transaction) {
        boolean result = transactionRepository.insertTransaction(transaction);
        return result;
    }

    public void deleteOldData() {
        Calendar calendar = Calendar.getInstance();
        long timeInMilliseconds = calendar.getTimeInMillis() -
                (SecondDataService.TIME_INTERVAL_FOR_STATISTICS + 10) * SecondDataService.MILLISECONDS_IN_SECOND;
        transactionRepository.deleteDataBeforeGivenTime(timeInMilliseconds);
    }
}
