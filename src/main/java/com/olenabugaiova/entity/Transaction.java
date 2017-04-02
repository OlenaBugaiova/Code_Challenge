package com.olenabugaiova.entity;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by elena on 31.03.17.
 */
@Entity
@Table(name="Transactions")
@Scope(value="request")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="amount", nullable = false, columnDefinition = "double default 0.0")
    private double amount;

    @Column(name="time", nullable = false)
    private long timestamp;

    //    getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
