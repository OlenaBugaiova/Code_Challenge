package com.olenabugaiova.service;

import com.olenabugaiova.repository.SecondRepository;
import com.olenabugaiova.entity.Second;
import com.olenabugaiova.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by elena on 31.03.17.
 */
@Service
public class SecondDataService {

    public static final long TIME_INTERVAL_FOR_STATISTICS = 60l;
    public static final long MILLISECONDS_IN_SECOND = 1000l;

    @Autowired
    private SecondRepository secondRepository;

    public List<Second> getDataForLastPeriod() {
        Calendar calendar = Calendar.getInstance();
        long startIntervalTime = calendar.getTimeInMillis()/MILLISECONDS_IN_SECOND - TIME_INTERVAL_FOR_STATISTICS;
        long endIntervalTime = calendar.getTimeInMillis()/MILLISECONDS_IN_SECOND;
        return secondRepository.getLastSeconds(startIntervalTime, endIntervalTime);
    }

    public void deleteOldData() {
        Calendar calendar = Calendar.getInstance();
        long timeInSeconds = calendar.getTimeInMillis()/MILLISECONDS_IN_SECOND -
                (TIME_INTERVAL_FOR_STATISTICS + 10);
        secondRepository.deleteDataBeforeGivenTime(timeInSeconds);
    }
}
