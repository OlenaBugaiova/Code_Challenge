package com.olenabugaiova.repository;

import com.olenabugaiova.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by elena on 31.03.17.
 */
public interface TransactionRepository {

    public boolean insertTransaction(Transaction transaction);

    public void deleteDataBeforeGivenTime(long time);
}
