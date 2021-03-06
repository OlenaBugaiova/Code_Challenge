package com.olenabugaiova.service;

import com.olenabugaiova.repository.SecondRepository;
import com.olenabugaiova.repository.TransactionRepository;
import com.olenabugaiova.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by elena on 31.03.17.
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SecondRepository secondRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
    public boolean insertTransaction(Transaction transaction) {
        long transactionTimeInSeconds = transaction.getTimestamp()/ SecondDataService.MILLISECONDS_IN_SECOND;
        boolean result = transactionRepository.insertTransaction(transaction);
        result = result && secondRepository.updateSecondData(transaction.getAmount() ,transactionTimeInSeconds);
        return result;
    }

    public void deleteOldData() {
        Calendar calendar = Calendar.getInstance();
        long timeInMilliseconds = calendar.getTimeInMillis() -
                (SecondDataService.TIME_INTERVAL_FOR_STATISTICS + 10) * SecondDataService.MILLISECONDS_IN_SECOND;
        transactionRepository.deleteDataBeforeGivenTime(timeInMilliseconds);
    }
}
