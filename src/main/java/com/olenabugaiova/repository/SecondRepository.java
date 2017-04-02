package com.olenabugaiova.repository;

import com.olenabugaiova.entity.Second;
import com.olenabugaiova.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by elena on 31.03.17.
 */
public interface SecondRepository {

    public List<Second> getLastSeconds(long startIntervalTime, long endIntervalTime);

    public Second getSecondDataByTime(long timeInSeconds);

    public boolean updateSecondData(double amount, long timeInSeconds);

    public void deleteDataBeforeGivenTime(long time);
}
