package com.olenabugaiova.entity;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by elena on 31.03.17.
 */
@Entity
@Table(name="DataPerSecond")
@Scope(value="request")
public class Second {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="time_in_seconds", nullable = false, unique=true)
    private long timeInSeconds;

    @Column(name="sum", nullable = false, columnDefinition = "double default 0.0")
    private double sum;

    @Column(name="max_value", nullable = false, columnDefinition = "double default 0.0")
    private double max;

    @Column(name="min_value", nullable = false, columnDefinition = "double default 0.0")
    private double min;

    @Column(name="count", nullable = false, columnDefinition = "bigint(20) default 0")
    private long count;

    public void setInitialValues(double amount) {
        this.sum = amount;
        this.max = amount;
        this.min = amount;
        this.count = 1;
    }

    public void updateValues(double amount) {
        this.sum += amount;
        this.count++;
        if(amount > this.max) {
            this.max = amount;
        }
        if(amount < this.min) {
            this.min = amount;
        }
    }

    //    getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(long timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
